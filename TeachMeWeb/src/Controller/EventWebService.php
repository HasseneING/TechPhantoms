<?php


namespace App\Controller;


use App\Repository\MeetRepository;
use App\Repository\ReservationRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Routing\Annotation\Route;
class EventWebService
{
    /**
     * @Route("/getreservations", name="getreservations", methods={"GET","POST"})
     */
    public function getreservations(ReservationRepository $repository,Request $request,NormalizerInterface $Normalizer): Response
    {
        $upmeets= $repository->findAll();
        $jsonContent=$Normalizer->normalize($upmeets,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }
}