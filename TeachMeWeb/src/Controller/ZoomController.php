<?php

namespace App\Controller;

use App\Entity\Meet;
use App\Entity\Student;
use App\Entity\Teacher;
use App\Entity\Token;
use App\Entity\Tutor;
use App\Form\MeetCreateType;
use App\Form\MeetType;
use App\Repository\EventRepository;
use App\Repository\MeetRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\PieChart\PieSlice;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Guzzle\Http\Client;


class ZoomController extends AbstractController{
//Guzzle is a PHP HTTP client that makes it easy to send HTTP requests and trivial to integrate with web services.

    /**
     * @Route("/zoom", name="meet_ind", methods={"GET"})
     */
    public function index(MeetRepository $meetRepository): Response
    {
        return $this->render('zoom/index.html.twig', [
            'upmeets' => $meetRepository->getAllMeetsUpcomingMeetings(),
            'prevmeets' => $meetRepository->getAllMeetsPreviousMeetings(),
        ]);
    }


    /**
     * @Route("/createzoom", name="create_zoom", methods={"GET","POST"})
     */
    public function CreateZoomMeet(Request $request): Response
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
        $token = $repositorytoken->find(17);
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

        //to be delete
        echo "Topoic: " . $data->topic;
        echo "<br>";
        echo "Join URL: " . $data->join_url;
        echo "<br>";
        echo "Meeting Password: " . $data->password;
        echo "<br>";
        echo "Meet id: " . $data->id;
        echo "Start time " . $data->start_time;
        echo "<br>";


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
        return $this->redirectToRoute('meet_index');
    }
    return $this->render('meet/new.html.twig', [
        'meet' => $meet,
        'form' => $form->createView(),
    ]);
}

    /**
     * @Route("/read", name="read_zoom")
     */
    public function ReadZoomMeet(): Response
{
    $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

    $repository = $this->getDoctrine()->getRepository(Token::class);
    $token = $repository->find(13);
    $arr_token = json_decode($token->getAccessToken());
    $tok = $arr_token->access_token;

    $response = $client->request('GET', '/v2/users/me/meetings', [
        "headers" => [
            "Authorization" => "Bearer $tok"
        ]
    ]);

    $data = json_decode($response->getBody());

    if (!empty($data)) {
        foreach ($data->meetings as $d) {
            $topic = $d->topic;
            $join_url = $d->join_url;
            echo "<h3>Topic: $topic</h3>";
            echo "Join URL: $join_url";
        }
    }
    echo "<br>";
    return new Response('Done ');
}

    /**
     * @Route("/delete", name="delete_meet")
     */
    public function DeleteZoomMeet(): Response
{
    $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

    $repository = $this->getDoctrine()->getRepository(Token::class);
    $token = $repository->find(13);
    $arr_token = json_decode($token->getAccessToken());
    $tok = $arr_token->access_token;

    $response = $client->request('DELETE', '/v2/meetings/94308870798', [
        "headers" => [
            "Authorization" => "Bearer $tok"
        ]
    ]);
    $data = json_decode($response->getBody());

    if (204 == $response->getStatusCode()) {
        echo "Meeting is deleted.";
    }
    return new Response('Meeting deleted');

}

    /**
     * @Route("/statistic", methods = {"GET"}, name = "refereeStatistic")
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

    /**
     * @Route("/bookng", name="bookng", methods={"GET"})
     */
    public function book(EventRepository $reservationRepository): Response
    {
        return $this->render('reservation/book.html.twig', [
            'events' => $reservationRepository->findAll(),
        ]);
    }


}
