<?php

namespace App\Controller;

use App\Entity\NewsletterSubs;
use App\Repository\NewsletterSubsRepository;
use Exception;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Address;
use Symfony\Component\Mime\Exception\RfcComplianceException;
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
     * @Route("/email", name="email")
     */
    public function sendEmail(MailerInterface $mailer,NewsletterSubsRepository $newsletterSubsRepository): Response
    {

        $repository = $this->getDoctrine()->getRepository(NewsletterSubs::class);
        $addrs=$repository->findAll();

        $emails=[];

        foreach ($addrs as $addr)
        {
            $email = (new TemplatedEmail())
                ->from('teachme242@gmail.com')
                ->to($addr->getEmail())
                ->cc('teachme242@example.com')
                ->subject('Hello! Check out our website!')
                ->htmlTemplate('admin/welcomeletter.html.twig');
            $mailer->send($email);
        }
//        $this->redirectToRoute('newsletter_subs_index');

        return $this->render('newsletter_subs/index.html.twig', [
            'controller_name' => 'MailerController',
            'newsletter_subs' => $newsletterSubsRepository->findAll(),
        ]);

    }
}
