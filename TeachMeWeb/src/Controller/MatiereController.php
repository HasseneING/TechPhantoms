<?php

namespace App\Controller;

use App\Entity\Matiere;
use App\Form\MatiereType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\MatiereRepository ;
use Dompdf\Dompdf;
use Dompdf\Options;
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;


/**
 * @Route("/matiere")
 */
class MatiereController extends AbstractController
{
    /**
     * @Route("/", name="matiere_index", methods={"GET"})
     */
    public function index(): Response
    {
        $matieres = $this->getDoctrine()
            ->getRepository(Matiere::class)
            ->findAll();

        return $this->render('matiere/index.html.twig', [
            'matieres' => $matieres,
        ]);
    }


    /**
     * @Route("/MatiereFront", name="MatiereFront", methods={"GET"})
     */
    public function indexFront(): Response
    {
        $matieres = $this->getDoctrine()
            ->getRepository(Matiere::class)
            ->findAll();

        return $this->render('matiere/front.html.twig', [
            'matieres' => $matieres,
        ]);
    }


    /**
     * @Route("/new", name="matiere_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $matiere = new Matiere();
        $form = $this->createForm(MatiereType::class, $matiere);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($matiere);
            $entityManager->flush();
            $mail = new PHPMailer(true);

            try {

                $nom = $form->get('nom')->getData();


                /*$email = $form->get('emailadresse')->getData();*/

                //Server settings
                $mail->SMTPDebug = SMTP::DEBUG_SERVER;
                $mail->isSMTP();
                $mail->Host       = 'smtp.gmail.com';
                $mail->SMTPAuth   = true;
                $mail->Username   = 'souissieya2018@gmail.com';             // SMTP username
                $mail->Password   = 'Youyou2020';                               // SMTP password
                $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
                $mail->Port       = 587;

                //Recipients
                $mail->setFrom('souissieya2018@gmail.com', 'teach me');
                $mail->addAddress('mohamedamine.arbaoui@esprit.tn', ' Nouvelle matiere');     // Add a recipient

                // Content
                $corps="Bonjour Monsieur/Madame notre nouvelle matiere ".$nom . " soyez les bienvenues" ;
                $mail->Subject = 'Nouvelle matiere!';
                $mail->Body    = $corps;

                $mail->send();

            } catch (Exception $e) {
                echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
            }

            return $this->redirectToRoute('matiere_index');
        }

        return $this->render('matiere/new.html.twig', [
            'matiere' => $matiere,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{id}", name="matiere_show", methods={"GET"})
     */
    public function show(Matiere $matiere): Response
    {
        return $this->render('matiere/show.html.twig', [
            'matiere' => $matiere,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="matiere_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Matiere $matiere): Response
    {
        $form = $this->createForm(MatiereType::class, $matiere);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('matiere_index');
        }

        return $this->render('matiere/edit.html.twig', [
            'matiere' => $matiere,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="matiere_delete", methods={"POST"})
     */
    public function delete(Request $request, Matiere $matiere): Response
    {
        if ($this->isCsrfTokenValid('delete'.$matiere->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($matiere);
            $entityManager->flush();
        }

        return $this->redirectToRoute('matiere_index');
    }

    /**
     *@Route("/pdf/ok",name="pdf_index", methods={"GET"})
     */
    public function listu1(MatiereRepository $matiereRepository): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('matiere/pdf.html.twig', [
            'matieres' =>$matiereRepository->findAll(),


        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A2', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();
        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }

   /* /**
     * @Route("/{id}"/, name="matiere_tri", methods={"GET"})
     */
   /* function OrderType(MatiereRepository  $repository  ){
        $Matiere=$repository->orderType();
        return $this->render('Matiere/index.html.twig',['Matiere'=>$Matiere]);
    }*/

    /**
     * @Route("/trie/nom", name="sortbytitleasc")
     */
    public function sortByTitleASC(MatiereRepository $matiereRepository): Response
    {

        $matieres=$matiereRepository->sortByTitleASC();
        // $events=$eventRepository->findAll();

        return $this->render('matiere/index.html.twig', [
            'matieres' => $matieres,
        ]);
    }

}
