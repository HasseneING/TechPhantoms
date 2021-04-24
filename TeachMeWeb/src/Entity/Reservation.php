<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ReservationRepository::class)
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="reservation_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reservationId;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="reservation_date", type="datetime", nullable=true)
     */
    private $reservationDate;

    /**
     *
     * @ORM\ManyToOne(targetEntity="App\Entity\Tutor", inversedBy="reservations")
     * @ORM\JoinColumn(nullable=false)
     * })
     */
    private $tutorid;

    /**
     *
     * @ORM\ManyToOne(targetEntity="App\Entity\Student", inversedBy="reservations")
     * @ORM\JoinColumn(nullable=false)
     *
     */
    private $studentid;


    /**
     * @return int
     */
    public function getReservationId(): ?int
    {
        return $this->reservationId;
    }

    /**
     * @param int $reservationId
     */
    public function setReservationId(int $reservationId): self
    {
        $this->reservationId = $reservationId;
        return $this;
    }

    /**
     * @return \DateTime|null
     */
    public function getReservationDate(): ?\DateTime
    {
        return $this->reservationDate;
    }

    /**
     * @param \DateTime|null $reservationDate
     */
    public function setReservationDate(?\DateTime $reservationDate): self
    {
        $this->reservationDate = $reservationDate;
        return $this;
    }

    public function getTutorid(): ?Tutor
    {
        return $this->tutorid;
    }

    public function setTutorid(?Tutor $tutorid): self
    {
        $this->tutorid = $tutorid;
        return $this;
    }

    public function getStudentid(): ?Student
    {
        return $this->studentid;
    }

    public function setStudentid(?Student $studentid): self
    {
        $this->studentid = $studentid;
        return $this;
    }
}