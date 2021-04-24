<?php

namespace App\Entity;

use App\Repository\TutorRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
/**
 * @ORM\Entity(repositoryClass=TutorRepository::class)
 */
class Tutor
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=30, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=30, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=30, nullable=false)
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="first_name", type="string", length=30, nullable=false)
     */
    private $firstName;

    /**
     * @var string
     *
     * @ORM\Column(name="last_name", type="string", length=30, nullable=false)
     */
    private $lastName;

    /**
     * @var int
     *
     * @ORM\Column(name="cin", type="integer", nullable=false)
     */
    private $cin;

    /**
     * @var int
     *
     * @ORM\Column(name="status", type="integer", nullable=false)
     */
    private $status;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_birth", type="date", nullable=false)
     */
    private $dateBirth;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_add", type="datetime", nullable=false)
     */
    private $dateAdd;

    /**
     * @var string
     *
     * @ORM\Column(name="profile_picture", type="string", length=50, nullable=false)
     */
    private $profilePicture;

    /**
     * @var string
     *
     * @ORM\Column(name="state", type="string", length=30, nullable=false)
     */
    private $state;

    /**
     * @var int
     *
     * @ORM\Column(name="phone_number", type="integer", nullable=false)
     */
    private $phoneNumber;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_formation", type="integer", nullable=false)
     */
    private $nbFormation;

    /**
     * @var string
     *
     * @ORM\Column(name="certificats", type="string", length=30, nullable=false)
     */
    private $certificats;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_student", type="integer", nullable=false)
     */
    private $nbStudent;

    /**
     * @var int
     *
     * @ORM\Column(name="price_hour", type="integer", nullable=false)
     */
    private $priceHour;

    /**
     * @var int
     *
     * @ORM\Column(name="schedule", type="integer", nullable=false)
     */
    private $schedule;

    /**
     * @var string
     *
     * @ORM\Column(name="cv", type="string", length=30, nullable=false)
     */
    private $cv;

    /**
     * @var string
     *
     * @ORM\Column(name="video", type="string", length=50, nullable=false)
     */
    private $video;

    /**
     * @var string
     *
     * @ORM\Column(name="subject", type="string", length=30, nullable=false)
     */
    private $subject;

    /**
     * @var string
     *
     * @ORM\Column(name="language", type="string", length=30, nullable=false)
     */
    private $language;

    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Meet", mappedBy="tutorid")
     */
    private $meets;

    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Reservation", mappedBy="tutorid")
     */
    private $reservations;


    public function __construct()
    {
        $this->meets = new ArrayCollection();
    }
    public function __toString(){

        return $this->username;

    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getUsername(): string
    {
        return $this->username;
    }

    /**
     * @param string $username
     */
    public function setUsername(string $username): void
    {
        $this->username = $username;
    }

    /**
     * @return string
     */
    public function getEmail(): string
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    /**
     * @return string
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    /**
     * @param string $password
     */
    public function setPassword(string $password): void
    {
        $this->password = $password;
    }

    /**
     * @return string
     */
    public function getFirstName(): string
    {
        return $this->firstName;
    }

    /**
     * @param string $firstName
     */
    public function setFirstName(string $firstName): void
    {
        $this->firstName = $firstName;
    }

    /**
     * @return string
     */
    public function getLastName(): string
    {
        return $this->lastName;
    }

    /**
     * @param string $lastName
     */
    public function setLastName(string $lastName): void
    {
        $this->lastName = $lastName;
    }

    /**
     * @return int
     */
    public function getCin(): int
    {
        return $this->cin;
    }

    /**
     * @param int $cin
     */
    public function setCin(int $cin): void
    {
        $this->cin = $cin;
    }

    /**
     * @return int
     */
    public function getStatus(): int
    {
        return $this->status;
    }

    /**
     * @param int $status
     */
    public function setStatus(int $status): void
    {
        $this->status = $status;
    }

    /**
     * @return \DateTime
     */
    public function getDateBirth(): \DateTime
    {
        return $this->dateBirth;
    }

    /**
     * @param \DateTime $dateBirth
     */
    public function setDateBirth(\DateTime $dateBirth): void
    {
        $this->dateBirth = $dateBirth;
    }

    /**
     * @return \DateTime
     */
    public function getDateAdd(): \DateTime
    {
        return $this->dateAdd;
    }

    /**
     * @param \DateTime $dateAdd
     */
    public function setDateAdd(\DateTime $dateAdd): void
    {
        $this->dateAdd = $dateAdd;
    }

    /**
     * @return string
     */
    public function getProfilePicture(): string
    {
        return $this->profilePicture;
    }

    /**
     * @param string $profilePicture
     */
    public function setProfilePicture(string $profilePicture): void
    {
        $this->profilePicture = $profilePicture;
    }

    /**
     * @return string
     */
    public function getState(): string
    {
        return $this->state;
    }

    /**
     * @param string $state
     */
    public function setState(string $state): void
    {
        $this->state = $state;
    }

    /**
     * @return int
     */
    public function getPhoneNumber(): int
    {
        return $this->phoneNumber;
    }

    /**
     * @param int $phoneNumber
     */
    public function setPhoneNumber(int $phoneNumber): void
    {
        $this->phoneNumber = $phoneNumber;
    }

    /**
     * @return int
     */
    public function getNbFormation(): int
    {
        return $this->nbFormation;
    }

    /**
     * @param int $nbFormation
     */
    public function setNbFormation(int $nbFormation): void
    {
        $this->nbFormation = $nbFormation;
    }

    /**
     * @return string
     */
    public function getCertificats(): string
    {
        return $this->certificats;
    }

    /**
     * @param string $certificats
     */
    public function setCertificats(string $certificats): void
    {
        $this->certificats = $certificats;
    }

    /**
     * @return int
     */
    public function getNbStudent(): int
    {
        return $this->nbStudent;
    }

    /**
     * @param int $nbStudent
     */
    public function setNbStudent(int $nbStudent): void
    {
        $this->nbStudent = $nbStudent;
    }

    /**
     * @return int
     */
    public function getPriceHour(): int
    {
        return $this->priceHour;
    }

    /**
     * @param int $priceHour
     */
    public function setPriceHour(int $priceHour): void
    {
        $this->priceHour = $priceHour;
    }

    /**
     * @return int
     */
    public function getSchedule(): int
    {
        return $this->schedule;
    }

    /**
     * @param int $schedule
     */
    public function setSchedule(int $schedule): void
    {
        $this->schedule = $schedule;
    }

    /**
     * @return string
     */
    public function getCv(): string
    {
        return $this->cv;
    }

    /**
     * @param string $cv
     */
    public function setCv(string $cv): void
    {
        $this->cv = $cv;
    }

    /**
     * @return string
     */
    public function getVideo(): string
    {
        return $this->video;
    }

    /**
     * @param string $video
     */
    public function setVideo(string $video): void
    {
        $this->video = $video;
    }

    /**
     * @return string
     */
    public function getSubject(): string
    {
        return $this->subject;
    }

    /**
     * @param string $subject
     */
    public function setSubject(string $subject): void
    {
        $this->subject = $subject;
    }

    /**
     * @return string
     */
    public function getLanguage(): string
    {
        return $this->language;
    }

    /**
     * @param string $language
     */
    public function setLanguage(string $language): void
    {
        $this->language = $language;
    }

    /**
     * @return Collection|Meet[]
     */
    public function getMeets(): Collection
    {
        return $this->meets;
    }

    public function addMeet(Meet $meet): self
    {
        if (!$this->meets->contains($meet)) {
            $this->meets[] = $meet;
            $meet->setIdTutor($this);
        }

        return $this;
    }

    public function removeMeet(Meet $meet): self
    {
        if ($this->meets->contains($meet)) {
            $this->meets->removeElement($meet);
            // set the owning side to null (unless already changed)
            if ($meet->getIdTutor() === $this) {
                $meet->setIdTutor(null);
            }
        }

        return $this;
    }


    /**
     * @return Collection|Reservation[]
     */
    public function getReservation(): Collection
    {
        return $this->reservations;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->reservations->contains($reservation)) {
            $this->reservations[] = $reservation;
            $reservation->setIdTutor($this);
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->reservations->contains($reservation)) {
            $this->reservations->removeElement($reservation);
            // set the owning side to null (unless already changed)
            if ($reservation->getIdTutor() === $this) {
                $reservation->setIdTutor(null);
            }
        }
        return $this;
    }
}
