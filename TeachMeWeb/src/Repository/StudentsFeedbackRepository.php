<?php

namespace App\Repository;

use App\Entity\StudentsFeedback;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method StudentsFeedback|null find($id, $lockMode = null, $lockVersion = null)
 * @method StudentsFeedback|null findOneBy(array $criteria, array $orderBy = null)
 * @method StudentsFeedback[]    findAll()
 * @method StudentsFeedback[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class StudentsFeedbackRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, StudentsFeedback::class);
    }

    // /**
    //  * @return StudentsFeedback[] Returns an array of StudentsFeedback objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?StudentsFeedback
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
