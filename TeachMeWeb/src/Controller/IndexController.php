<?php

namespace App\Controller;

use App\Entity\NewsletterSubs;
use App\Form\NewsletterSubsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class IndexController extends AbstractController
{
    /**
     * @Route("/index", name="index")
     */
    public function index(Request $request): Response
    {
        $newsletterSub = new NewsletterSubs();
        $form = $this->createForm(NewsletterSubsType::class, $newsletterSub);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($newsletterSub);
            $entityManager->flush();
        }

        return $this->render('/base.html.twig', [
            'controller_name' => 'IndexController',
            'newsletter_sub' => $newsletterSub,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin", name="admin")
     */
    public function admin(): Response
    {
        return $this->render('/baseBack.html.twig', [
            'controller_name' => 'IndexController',
        ]);
    }
}
