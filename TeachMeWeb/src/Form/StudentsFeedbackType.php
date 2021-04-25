<?php

namespace App\Form;

use App\Entity\StudentsFeedback;
use Brokoskokoli\StarRatingBundle\Form\StarRatingType;
use brokoskokoli\StarRatingBundle\StarRatingBundle;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Date;


class StudentsFeedbackType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {

        $builder
            ->add('comment')
            ->add('note', IntegerType::class,['attr'=> ['placeholder'=>'ok', 'value'=>'9','label'=>'oki', 'readonly' => true]])
            ->add('recommandation')
            ->add('rating')
            ->add('idTutor')
            ->add('idSutdent')
          ->add('date',DateType::class ,[
              'data' => new \DateTime(),
              'attr'=> [ 'readonly' => true ]
          ])
            ->add('rating', StarRatingType::class, [
                'label' => 'Rating' ,


            ]);
      // $builder->get('date')->

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => StudentsFeedback::class,
           'required' => false,
        ]);
    }
}
