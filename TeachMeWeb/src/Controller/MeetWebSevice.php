<?php


namespace App\Controller;

use Guzzle\Http\Client;
use App\Entity\Meet;
use App\Entity\Token;
use App\Repository\MeetRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\TokenRepository;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


use App\Form\MeetCreateType;
use App\Form\MeetType;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\PieChart\PieSlice;
use App\Entity\PropSearch;
use App\Form\PropSearchType;

class MeetWebSevice extends AbstractController
{
    /**
     * @Route("/newmobile", name="meet_new_mobile", methods={"GET","POST"})
     */
    public function newCodename(Request $request,MailerInterface $mailer): Response
    {
        $meet = new Meet();
        //Get values from mobile application
     //   $datemeet = $_POST["meetDate"];
        $passmeet = $_POST["meetPass"];
        $namemeet = $_POST["meetName"];

       // $passmeet = "ahlem";
       // $namemeet = "ahlem";
        //convert datezoom to string car zoom api n'accecpte que les string
       // $datezoom=$datemeet->format('Y-m-d H:i:s');

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
                "start_time" => "",
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
        $meets->setMeetName($data->topic);
        $meets->setMeetPass($data->password);
        $meets->setMeetLink($data->id);
        $repository->persist($meets);
        $repository->flush();


        //send mail

        $email = (new Email())
            ->from('laajili.ahlempidev@gmail.com')
            ->to('ahlem.laajili@esprit.tn')
            ->subject('New TeachMee App meet with ')
            ->text('Sending emails is fun again!')
            ->html('<p> A new meeting has been created 
                             <br> MeetID ='.$data->id.'
                             <br> Meet Password='.$data->password.'
                             <br> Meet Date='.$data->start_time.'</p>');
        $mailer->send($email);
        return new Response("Success");
    }


    /**
     * @Route("/getupcomming", name="getup", methods={"GET","POST"})
     */
    public function getupcomingmeets(MeetRepository $meetRepository,Request $request,NormalizerInterface $Normalizer): Response
    {
        $upmeets= $meetRepository->getAllMeetsUpcomingMeetings();
      $jsonContent=$Normalizer->normalize($upmeets,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/getprevmeets", name="getprev", methods={"GET","POST"})
     */
    public function getpreviousmeets(MeetRepository $meetRepository,Request $request,NormalizerInterface $Normalizer): Response
    {
        $prevmeets=$meetRepository->getAllMeetsPreviousMeetings();
        $jsonContent=$Normalizer->normalize($prevmeets,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/mobiledelete", name="mobiledelete", methods={"GET","POST"})
     */
    public function delete(): Response
    {
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(1);
        $arr_token = json_decode($token->getAccessToken());
        $tok = $arr_token->access_token;

     $meetID = $_POST["meetId"];
     $meetLink=$_POST["meetLink"];
        //$meetLink=85970530291;
        //$meetID =80;
       $response = $client->request('DELETE', '/v2/meetings/'.$meetLink.'', [
            "headers" => [
                "Authorization" => "Bearer $tok"
            ]
        ]);
        $data = json_decode($response->getBody());

            $entityManager = $this->getDoctrine()->getManager();
            $meet = $entityManager->getReference(Meet::class, $meetID);
            $entityManager->remove($meet);
            $entityManager->flush();


        return new Response("Success");
    }

    /**
     * @Route("/updatemobile", name="updatemobile", methods={"GET","POST"})
     */
    public function updatemobile(): Response
    {

     /*   $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(33);
        $arr_token = json_decode($token->getAccessToken());
        $tok = $arr_token->access_token;
*/
       // $meetID = $_POST["meetId"];
       // $meetLink=$_POST["meetLink"];
        //$meetLink=85970530291;
        //  $meetID =80;
       /* $response = $client->request('DELETE', '/v2/meetings/'.$meetLink.'', [
            "headers" => [
                "Authorization" => "Bearer $tok"
            ]
        ]);
        $data = json_decode($response->getBody());
*/
        $meetName = $_POST["meetName"];
        $meetPass=$_POST["meetPass"];
        $meetID = $_POST["meetId"];
        $entityManager = $this->getDoctrine()->getManager();
        $meet = $entityManager->getReference(Meet::class,$meetID);
        $meet->setMeetName($meetName);
     //   $meet->setMeetDate("ok");
        $meet->setMeetPass($meetPass);
        $entityManager->persist($meet);
        $entityManager->flush();
        return new Response("Success");

    }

    /**
     * @Route("/mobtok", name="mobtok", methods={"GET","POST"})
     */
    public function tok(): Response
    {
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);
        $repositorytoken = $this->getDoctrine()->getRepository(Token::class);
        $token = $repositorytoken->find(1);
        $arr_token= json_decode($token->getAccessToken());
        $tok = $arr_token->access_token;



        define('CLIENT_ID', 'AL8oaqg_Rwa_F2Vsrvc9w');
        define('CLIENT_SECRET', 'Bep3RYqqYMIq8mRme9fV2Vh46mdJ6KQD');
        define('REDIRECT_URI', 'http://c7acb48012b2.ngrok.io/mobtok');
        try{
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://zoom.us']);


                $response = $client->request('POST', '/oauth/token', [
                    "headers" => [
                        "Authorization" => "Basic " . base64_encode(CLIENT_ID . ':' . CLIENT_SECRET)
                    ],
                    'form_params' => [
                        "grant_type" => "authorization_code",
                        "code" => $_GET['code'],
                        "redirect_uri" => REDIRECT_URI
                    ],
                ]);
                $result = json_decode($response->getBody()->getContents(), true);
                $repository = $this->getDoctrine()->getManager();
                $token= $repository->getReference(Token::class,1);
                $token->setAccessToken(json_encode($result));
                $repository->persist($token);
                $repository->flush();
                return new Response('You tokens has been added successfully');

        }catch (Exception $e){
            echo $e->getMessage();
        }


        return  new Response("You have already logged in");
    }

    /**
     * @Route("/auth", name="auth", methods={"GET","POST"})
     */
    public function auth(): Response
    {

        return  $this->render('meet/connect.html.twig');
    }
}