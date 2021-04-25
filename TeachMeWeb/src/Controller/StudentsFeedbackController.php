<?php

namespace App\Controller;

use App\Entity\StudentsFeedback;
use App\Form\StudentsFeedbackType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ResetType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\StudentsFeedbackRepository ;

class StudentsFeedbackController extends AbstractController
{
    /**
     * @Route("/students/feedback", name="students_feedback")
     */
    public function index(): Response
    {
        return $this->render('students_feedback/index.html.twig', [
            'controller_name' => 'StudentsFeedbackController',
        ]);
    }

    /**
     * @param StudentsFeedbackRepository $repository
     * @return Response
     * @Route("/AfficheReviews", name="AfficheReviews")
     */
    public function affiche (StudentsFeedbackRepository $repository){
        $reviews= $repository->findAll();

        return $this->render('students_feedback/affiche.html.twig', ['reviews'=>$reviews  ] );

}

    /**
     * @param Request $request
     * @return Response
     * @Route ("/addReview")
     */
    public function add (Request $request) {
        $review= new StudentsFeedback();
        $review->setRating(3);
        $form=$this->createForm(StudentsFeedbackType::class,$review) ;
        //$form->add('Clear',ResetType::class);
       // $form->add('Add',SubmitType::class);

        $form->handleRequest($request);

        if($form->isSubmitted()&& $form->isValid()) {
            $em=$this->getDoctrine()->getManager();

            $em->persist($review);
            $em->flush();
            return $this->redirectToRoute('AfficheReviews');
        }
        return $this->render('students_feedback/addreview.html.twig',[
            'form'=>$form->createView()
        ]) ;

    }

    /**
     * @Route ("/delete/{id}", name="delete") ;
     */
    public function delete($id, StudentsFeedbackRepository $repository) {
        $review=$repository->find($id) ;
        $em=$this->getDoctrine()->getManager() ;
        $em->remove($review);
        $em->flush();
        return $this->redirectToRoute('AfficheReviews') ;
}

    /**
     * @Route ("/update{id}", name="update") ;
     */

public function  update ($id, StudentsFeedbackRepository $repository , Request $request) {
    $review=$repository->find($id) ;
    $form=$this->createForm(StudentsFeedbackType::class,$review);
  //  $form->add('Add',SubmitType::class);
    $form->handleRequest($request);
    if($form->isSubmitted()&& $form->isValid()) {
        $em=$this->getDoctrine()->getManager();

        $em->flush();
        return $this->redirectToRoute('AfficheReviews') ;

    }
    return  $this->render('students_feedback/update.html.twig',[
        'form'=>$form->createView()
    ]) ;

}

    /**
     * @Route ("/RecordVideo") ;
     */
public function RecordVideo () {
    return $this->render('students_feedback/RecordVideo.html.twig') ;


}




}
