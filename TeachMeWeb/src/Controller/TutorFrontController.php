<?php


namespace App\Controller;


use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;

class TutorFrontController extends AbstractController
{
    /**
     * @Route("/tutorfront", name="tutorfront_page")
     */
    public function default():Response
    {
        return $this->render("front/teacher/dashboard.html.twig");
    }

    /**
     * @Route("tutorfront/{pageName}", name="tutorfront_default")
     * @param string $pageName Page name
     * @return Response
     */
    public function index(string $pageName)
    {
        return $this->render(
            sprintf(
                "front/teacher/%s.html.twig",
                $pageName
            )
        );
    }
}