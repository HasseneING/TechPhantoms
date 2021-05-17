<?php

namespace App\Repository;

use App\Entity\Meet;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Meet|null find($id, $lockMode = null, $lockVersion = null)
 * @method Meet|null findOneBy(array $criteria, array $orderBy = null)
 * @method Meet[]    findAll()
 * @method Meet[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class MeetRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Meet::class);
    }

    // /**
    //  * @return Meet[] Returns an array of Meet objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Meet
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function getAllMeetsSortedByDescDate()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetDate', 'DESC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsSortedByAscDate()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetDate', 'ASC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsSortedByDescName()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetName', 'DESC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsSortedByAscName()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetName', 'ASC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsSortedByAscPass()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetPass', 'ASC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsSortedByDESCPass()
    {
        $qb = $this->createQueryBuilder('m')
            ->orderBy('m.meetPass', 'DESC');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsPreviousMeetings()
    {
        $qb = $this->createQueryBuilder('m')
            ->setParameter('now', new \DateTime('now'))
            ->where('m.meetDate < :now');
        return $qb->getQuery()->getResult();
    }

    public function getAllMeetsUpcomingMeetings()
    {
        $qb = $this->createQueryBuilder('m')
            ->setParameter('now', new \DateTime('now'))
            ->where('m.meetDate > :now');
        return $qb->getQuery()->getResult();
    }

    public function searchUpcomingMeetings(string $name)
    {
        $qb = $this->createQueryBuilder('m')
            ->setParameter('name', "%$name%")
            ->setParameter('now', new \DateTime('now'))
            ->where('m.meetDate > :now')
        ->andWhere('m.meetName LIKE :name');

        return $qb->getQuery()->getResult();
    }

    public function searchPreviousMeetings(string $name)
    {
        $qb = $this->createQueryBuilder('m')
            ->setParameter('name', "%$name%")
            ->setParameter('now', new \DateTime('now'))
            ->where('m.meetDate < :now')
            ->andWhere('m.meetName LIKE :name');

        return $qb->getQuery()->getResult();
    }

    public function findByNameAutocomplete(string $name): array
    {
        $queryBuilder = $this->createQueryBuilder('m')
            ->where("m. LIKE :meetName")
            ->setParameter('name', "%$name%")
            ->orderBy('c.name', 'ASC')
            ->setMaxResults(10);
        return $queryBuilder->getQuery()
            ->getResult();
    }

    public function countUpcomingMeeting()
    {
        $qb = $this->createQueryBuilder('m')
            ->setParameter('now', new \DateTime('now'))
            ->select('count(m.meetDate)')
            ->where('m.meetDate < :now');
        return $qb->getQuery()->getResult();
    }

}
