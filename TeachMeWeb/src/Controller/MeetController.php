<?php

namespace App\Controller;

use App\Entity\Meet;
use App\Entity\Token;
use App\Form\MeetCreateType;
use App\Form\MeetType;
use App\Repository\MeetRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Guzzle\Http\Client;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\PieChart\PieSlice;
use App\Entity\PropSearch;
use App\Form\PropSearchType;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
/**
 * @Route("/meet")
 */
class MeetController extends AbstractController
{
    /**
     * @Route("/", name="meet_index", methods={"GET","POST"})
     */
    public function index(MeetRepository $meetRepository,Request $request): Response
    {
        $upmeets= $meetRepository->getAllMeetsUpcomingMeetings();
        $prevmeets=$meetRepository->getAllMeetsPreviousMeetings();
        if($request->request->get("search")!='') {
            $upmeets = $meetRepository->getAllMeetsUpcomingMeetings();
            $upmeets = $meetRepository->searchUpcomingMeetings($request->request->get("search"));
        }
        if($request->request->get("searchprev")!='') {
            $prevmeets = $meetRepository->getAllMeetsPreviousMeetings();
            $prevmeets = $meetRepository->searchPreviousMeetings($request->request->get("searchprev"));
        }

        return  $this->render('meet/index.html.twig',[
            'upmeets' => $upmeets,
            'prevmeets' => $prevmeets,
        ]);
    }


    /**
     * @Route("/new", name="meet_new", methods={"GET","POST"})
     */
    public function new(Request $request,MailerInterface $mailer): Response
    {
        $meet = new Meet();
        $form = $this->createForm(MeetCreateType::class, $meet);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            //Get values from MeetCreateType.php
            $datemeet = $form["meetDate"]->getData();
            $passmeet = $form["meetPass"]->getData();
            $namemeet = $form["meetName"]->getData();
            //convert datezoom to string car zoom api n'accecpte que les string

            $datezoom=$datemeet->format('Y-m-d H:i:s');

            //connect to zoom api de type rest
            $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);
            //verify token stored already in database
            $repositorytoken = $this->getDoctrine()->getRepository(Token::class);
            $token = $repositorytoken->find(1);
            $arr_token = json_decode($token->getAccessToken());
            $tok = $arr_token->access_token;

            //Send client request
            $response = $client->request('POST', '/v2/users/me/meetings', [
                "headers" => [
                    "Authorization" => "Bearer $tok"
                ],
                'json' => [
                    "topic" => $namemeet,
                    "type" => 2,
                    "start_time" => $datezoom,
                    "duration" => "60",
                    "password" => $passmeet
                ],
            ]);
            $data = json_decode($response->getBody());

            //time conversion to be stored in db as date time
            $src_tz = new \DateTimeZone('Asia/Manila');
            $dest_tz = new \DateTimeZone('America/Vancouver');
            $dt = new \DateTime($data->start_time, $src_tz);
            $dt->setTimeZone($dest_tz);
            $dest_dt = $dt->format('Y-m-d H:i:s');
            $dateConv = new \DateTime($dest_dt);


// storing data in db
            $repository = $this->getDoctrine()->getManager();
            $meets = new Meet();
            $meets->setStudentid($form["studentid"]->getData());
            $meets->setTutorid($form["tutorid"]->getData());
            $meets->setMeetDate($datemeet);
            $meets->setMeetName($data->topic);
            $meets->setMeetPass($data->password);
            $meets->setMeetLink($data->id);
            $repository->persist($meets);
            $repository->flush();


            //send mail

            $email = (new Email())
                ->from('laajili.ahlempidev@gmail.com')
                ->to('ahlem.laajili@esprit.tn')
                ->subject('New TeachMee App meet with '.$form["tutorid"]->getData().'')
                ->text('Sending emails is fun again!')
                ->html('<p> A new meeting has been created 
                             <br> MeetID ='.$data->id.'
                             <br> Meet Password='.$data->password.'
                             <br> Meet Date='.$data->start_time.'</p>');
            $mailer->send($email);
            return $this->redirectToRoute('meet_index');
        }
        return $this->render('meet/new.html.twig', [
            'meet' => $meet,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{meetId}", name="meet_show", methods={"GET"})
     */
    public function show(Meet $meet): Response
    {
        return $this->render('meet/show.html.twig', [
            'meet' => $meet,
        ]);
    }

    /**
     * @Route("/{meetId}/edit", name="meet_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Meet $meet): Response
    {
        $form = $this->createForm(MeetType::class, $meet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('meet_index');
        }

        return $this->render('meet/edit.html.twig', [
            'meet' => $meet,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{meetId}", name="meet_delete", methods={"POST"})
     */
    public function delete(Request $request, Meet $meet): Response
    {
        echo $meet->getMeetLink();
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(1);
        $arr_token = json_decode($token->getAccessToken());
        $tok = $arr_token->access_token;

        $response = $client->request('DELETE', '/v2/meetings/'.$meet->getMeetLink().'', [
            "headers" => [
                "Authorization" => "Bearer $tok"
            ]
        ]);
        $data = json_decode($response->getBody());

        if (204 == $response->getStatusCode()) {
            echo "Meeting is deleted.";
        }
        if ($this->isCsrfTokenValid('delete'.$meet->getMeetId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($meet);
            $entityManager->flush();
        }

        return $this->redirectToRoute('meet_index');
    }

    /**
     * @Route("defau", name="pdfindex")
     */
    public function pdfindex(MeetRepository $meetRepository):Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();// Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('meet/pdf.html.twig', [
            'upmeets' => $meetRepository->getAllMeetsUpcomingMeetings(),
            'prevmeets' => $meetRepository->getAllMeetsPreviousMeetings(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
        return $this->render('meet/pdf.html.twig', [
            'upmeets' => $meetRepository->getAllMeetsUpcomingMeetings(),
            'prevmeets' => $meetRepository->getAllMeetsPreviousMeetings(),
        ]);
    }


    /**
     * @Route("defau/{meetId}", name="pdfshow", methods={"GET"})
     */
    public function pdfshow(MeetRepository $meetRepository,Meet $meet):Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('meet/showpdf.html.twig', [
            'meet' => $meet,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
        return $this->render('meet/pdf.html.twig', [
            'meets' => $meetRepository->getAllMeetsSortedByDescName(),
        ]);
    }


    /**
     * @Route("defaus", name="defaus")
     */
    public function MeetStatistic(Request $request):Response
    {

        $meets = $this->getDoctrine()->getRepository(Meet::class);
        $totalpreviousdate = $meets->createQueryBuilder('a')
            ->setParameter('now', new \DateTime('now'))
            ->select('count(a.meetDate)')
            ->where('a.meetDate < :now')
            ->getQuery()
            ->getSingleScalarResult();

        $totalupcomingdate = $meets->createQueryBuilder('a')
            ->setParameter('now', new \DateTime('now'))
            ->select('count(a.meetDate)')
            ->where('a.meetDate > :now')
            ->getQuery()
            ->getSingleScalarResult();

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable( array(
            ['Upcoming', 'Current'],
            ['Upcoming',     $totalpreviousdate/100],
            ['Current',      $totalupcomingdate/100],
        ));

        $pieChart->getOptions()->setTitle('Meetings Statistic');
        $pieChart->getOptions()->setHeight(400);
        $pieChart->getOptions()->setWidth(400);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
        return $this->render('meet/statistic.html.twig', array(
            'piechart' => $pieChart ));

    }
}
