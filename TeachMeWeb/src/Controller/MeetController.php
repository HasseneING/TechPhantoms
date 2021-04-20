<?php

namespace App\Controller;

use App\Entity\Meet;
use App\Entity\Teacher;
use App\Entity\Token;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Guzzle\Http\Client;
class MeetController extends AbstractController
{
//Guzzle is a PHP HTTP client that makes it easy to send HTTP requests and trivial to integrate with web services.
    /**
     * @Route("/meet", name="meet")
     */
    public function index(): Response
    {

        define('CLIENT_ID', 'cFramEQ7T7S0J1yi6U2jhw');
        define('CLIENT_SECRET', 'Fea78ECkdqklTP5sojuLQ77nWUk72HJH');
        define('REDIRECT_URI', 'http://3e63ded27be0.ngrok.io/callback');
      /*  $url = "https://zoom.us/oauth/authorize?response_type=code&client_id=".CLIENT_ID."&redirect_uri=".REDIRECT_URI;
        echo "<a href=".$url.">Login with Zoom</a>";*/
        return $this->render('/meet/index.html.twig', [
            'controller_name' => 'MeetController',
        ]);
    }


    /**
     * @Route("/create", name="create_meet")
     */
    public function CreateMeet(): Response
    {
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);
        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(9);
        $arr_token = json_decode($token->getAccessToken());
        $tok = $arr_token -> access_token;
        $response = $client->request('POST', '/v2/users/me/meetings', [
                "headers" => [
                    "Authorization" => "Bearer $tok"
                ],
                'json' => [
                    "topic" => "Success",
                    "type" => 2,
                    "start_time" => "2021-03-05T20:30:00",
                    "duration" => "60",
                    "password" => "123456"
                ],
            ]);
        $data = json_decode($response->getBody());
        echo "Topoic: ". $data->topic;
        echo "<br>";
        echo "Join URL: ". $data->join_url;
        echo "<br>";
        echo "Meeting Password: ". $data->password;
        echo "<br>";
        echo "Meet id: ". $data->id;
        echo "Start meeting: ". $data->start_url;
        echo "<br>";
        /*$convert_date = strtotime($data->start_time);
        $date=date('d/M/Y T h:i:s', $convert_date);
        $repository = $this->getDoctrine()->getManager();
        $meet=new Meet();
        $meet->setMeetLink($data->id);
        $meet->setMeetPass($data->password);
        $meet->setMeetName($data->topic);
        $repository ->persist($meet);
        $repository ->flush();*/
        return new Response('Saved new product with id '.$tok);
}

    /**
     * @Route("/read", name="read_meet")
     */
    public function ReadMeet(): Response
    {
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(9);
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
    public function DeleteMeet(): Response
    {
        $client = new \GuzzleHttp\Client(['base_uri' => 'https://api.zoom.us']);

        $repository = $this->getDoctrine()->getRepository(Token::class);
        $token = $repository->find(9);
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



}
