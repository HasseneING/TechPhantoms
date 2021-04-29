<?php

namespace App\Controller;

use App\Entity\NewsletterSubs;
use App\Form\NewsletterSubsType;
use App\Repository\NewsletterSubsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/newsletter/subs")
 */
class NewsletterSubsController extends AbstractController
{
    /**
     * @Route("/", name="newsletter_subs_index", methods={"GET"})
     */
    public function index(NewsletterSubsRepository $newsletterSubsRepository): Response
    {
        return $this->render('newsletter_subs/index.html.twig', [
            'newsletter_subs' => $newsletterSubsRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="newsletter_subs_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $newsletterSub = new NewsletterSubs();
        $form = $this->createForm(NewsletterSubsType::class, $newsletterSub);
        $form->handleRequest($request);
        $done=false;


        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($newsletterSub);
            $entityManager->flush();
            $done=true;
        }


        return $this->render('base.html.twig', [
            'newsletter_sub' => $newsletterSub,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="newsletter_subs_show", methods={"GET"})
     */
    public function show(NewsletterSubs $newsletterSub): Response
    {
        return $this->render('newsletter_subs/show.html.twig', [
            'newsletter_sub' => $newsletterSub,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="newsletter_subs_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, NewsletterSubs $newsletterSub): Response
    {
        $form = $this->createForm(NewsletterSubsType::class, $newsletterSub);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('newsletter_subs_index');
        }

        return $this->render('newsletter_subs/edit.html.twig', [
            'newsletter_sub' => $newsletterSub,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="newsletter_subs_delete", methods={"POST"})
     */
    public function delete(Request $request, NewsletterSubs $newsletterSub): Response
    {
        if ($this->isCsrfTokenValid('delete'.$newsletterSub->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($newsletterSub);
            $entityManager->flush();
        }

        return $this->redirectToRoute('newsletter_subs_index');
    }
}
