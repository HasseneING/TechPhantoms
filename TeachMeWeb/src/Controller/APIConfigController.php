<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Token;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class APIConfigController extends AbstractController
{

    /**
     * @Route("/callback", name="callback_meet")
     */
        public function Callback():Response
    {
        define('CLIENT_ID', 'cFramEQ7T7S0J1yi6U2jhw');
        define('CLIENT_SECRET', 'Fea78ECkdqklTP5sojuLQ77nWUk72HJH');
        define('REDIRECT_URI', 'http://3e63ded27be0.ngrok.io/callback');
        try {
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
            $repository = $this->getDoctrine()->getRepository(Token::class);
            $token = $repository->find(9);
            if ($token == null) {
                $result = json_decode($response->getBody()->getContents(), true);
                $repository = $this->getDoctrine()->getManager();
                $token = new Token();
                $token->setAccessToken(json_encode($result));
                $repository->persist($token);
                $repository->flush();
                return new Response('You tokens has been added successfully');
            }
        }catch (Exception $e){
            echo $e->getMessage();
        }
        return $this->render('base.html.twig');
    }

    /**
     * @Route("/make", name="reservation_make", methods={"GET"})
     */
    public function makeReservation(Reservation $reservation): Response
    {

        return $this->render('reservation/front.html.twig', [
            'reservation' => $reservation,
        ]);
    }
}
