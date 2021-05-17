<?php

namespace App\Controller;

use App\Entity\Event;
use App\Form\EventType;
use App\Repository\EventRepository;
use App\Repository\MeetRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/event")
 */
class EventController extends AbstractController
{
    /**
     * @Route("/", name="event_index", methods={"GET"})
     */
    public function index(EventRepository $eventRepository): Response
    {

        return $this->render("event/event.html.twig", [
            'events' => $eventRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="event_new", methods={"GET","POST"})
     */
    public function new(Request $request,EventRepository $eventRepository): Response
    {
        // Use the same format used by Moment.js in the view
        $em = $this->getDoctrine()->getManager();
        $events=new Event();
        // Create appointment entity and set fields values
        $events = new Event();
        $events->setTitle($request->request->get("title"));
        $events->setColor($request->request->get("color"));
        $events->setStart(  new \DateTime ($request->request->get("start")));
        $events->setEnd( new \DateTime ($request->request->get("end")));

        // Create appointment
        $em->persist($events);
        $em->flush();


            return $this->redirectToRoute('event_index');

    }

    /**
     * @Route("/{id}", name="event_show", methods={"GET"})
     */
    public function show(Event $event): Response
    {
        return $this->render('event/show.html.twig', [
            'event' => $event,
        ]);
    }

    /**
     * @Route("/edit", name="event_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Event $event,EventRepository $eventRepository): Response
    {
        $em = $this->getDoctrine()->getManager();
        $eventId = $request->request->get("id");
        $event= $eventRepository->find($eventId);
        $event->setTitle($request->request->get("title"));
        $event->setColor($request->request->get("color"));
        $em->persist($event);
        $em->flush();

        return $this->redirectToRoute('event_index');
    }

    /**
     * @Route("/{id}", name="event_delete", methods={"POST"})
     */
    public function delete(Request $request, Event $event): Response
    {
        if ($this->isCsrfTokenValid('delete'.$event->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($event);
            $entityManager->flush();
        }

        return $this->redirectToRoute('event_index');
    }


}
