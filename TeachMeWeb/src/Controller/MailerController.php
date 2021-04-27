<?php

namespace App\Controller;

use App\Entity\NewsletterSubs;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;

class MailerController extends AbstractController
{
    /**
     * @Route("/mailer", name="mailer")
     */
    public function index(): Response
    {
        return $this->render('mailer/index.html.twig', [
            'controller_name' => 'MailerController',
        ]);
    }


    /**
     * @Route("/email")
     */
    public function sendEmail(MailerInterface $mailer): Response
    {

        $repository = $this->getDoctrine()->getRepository(NewsletterSubs::class);
        $addrs=$repository->findAll();



        $email = (new TemplatedEmail())
            ->from('teachme242@gmail.com')
            ->to('hamedhassenekun@gmail.com')
            ->cc('teachme242@example.com')
            ->subject('Time for Symfony Mailer!')
            ->htmlTemplate('admin/datatable.html.twig');

        $mailer->send($email);
        return $this->render('mailer/index.html.twig', [
            'controller_name' => 'MailerController',
        ]);

    }
}
