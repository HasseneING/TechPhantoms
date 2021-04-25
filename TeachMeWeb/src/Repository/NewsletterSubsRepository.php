<?php

namespace App\Repository;

use App\Entity\NewsletterSubs;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method NewsletterSubs|null find($id, $lockMode = null, $lockVersion = null)
 * @method NewsletterSubs|null findOneBy(array $criteria, array $orderBy = null)
 * @method NewsletterSubs[]    findAll()
 * @method NewsletterSubs[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class NewsletterSubsRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, NewsletterSubs::class);
    }

    // /**
    //  * @return NewsletterSubs[] Returns an array of NewsletterSubs objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('n.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?NewsletterSubs
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
