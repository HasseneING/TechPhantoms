<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
class AdminController extends AbstractController
{
    /**
     * @Route("/admin", name="admin_page")
     * @return Response
     */
    public function default()
    {
        return $this->render("admin/dashboard.html.twig");
    }

    /**
     * @Route("admin/{pageName}", name="admin_default")
     * @param string $pageName Page name
     * @return Response
     */
    public function index(string $pageName)
    {
        return $this->render(
            sprintf(
                "admin/%s.html.twig",
                $pageName
            )
        );
    }


}
