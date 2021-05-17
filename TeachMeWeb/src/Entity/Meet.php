<?php

namespace App\Entity;

use App\Repository\MeetRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=MeetRepository::class)
 */
class Meet
{
    /**
     * @var int
     * @Groups ("post:read")
     * @ORM\Column(name="meet_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $meetId;

    /**
     * @var string
     * @Groups ("post:read")
     * @ORM\Column(name="meet_link", type="string", length=250, nullable=true)
     */
    private $meetLink;

    /**
     * @var \DateTime|null
     * @Groups ("post:read")
     * @Assert\NotBlank(message="Meet date is required")
     * @ORM\Column(name="meet_date", type="datetime", nullable=true)
     */
    private $meetDate;

    /**
     * @var string
     * @Groups ("post:read")
     * @Assert\NotBlank(message="Meet password is required")
     * @ORM\Column(name="meet_pass", type="string", length=255, nullable=true)
     */
    private $meetPass;

    /**
     * @var string
     * @Groups ("post:read")
     * @Assert\NotBlank(message="Meet name is required")
     * @ORM\Column(name="meet_name", type="string", length=255, nullable=true)
     */
    private $meetName;

    /**
     * @Groups ("post:read")
     * @Assert\NotBlank(message=" Please select a Tutor")
     * @ORM\ManyToOne(targetEntity="App\Entity\Tutor", inversedBy="meets")
     * @ORM\JoinColumn(nullable=true)
     * })
     */
    private $tutorid;

    /**
     * @Groups ("post:read")
     * @Assert\NotBlank(message=" Please select a Teacher")
     * @ORM\ManyToOne(targetEntity="App\Entity\Student", inversedBy="meets")
     * @ORM\JoinColumn(nullable=true)
     *
     */
    private $studentid;



    /**
     * @return int
     */
    public function getMeetId(): ?int
    {
        return $this->meetId;
    }

    /**
     * @param int $meetId
     */
    public function setMeetId(int $meetId): self
    {
        $this->meetId = $meetId;
        return $this;
    }

    /**
     * @return string
     */
    public function getMeetLink(): ?string
    {
        return $this->meetLink;
    }

    /**
     * @param string $meetLink
     */
    public function setMeetLink(string $meetLink): self
    {
        $this->meetLink = $meetLink;
        return $this;
    }

    /**
     * @return \DateTime|null
     */
    public function getMeetDate(): ?\DateTime
    {
        return $this->meetDate;
    }

    /**
     * @param \DateTime|null $meetDate
     */
    public function setMeetDate(?\DateTime $meetDate): self
    {
        $this->meetDate = $meetDate;
        return $this;
    }

    /**
     * @return string
     */
    public function getMeetPass(): ?string
    {
        return $this->meetPass;
    }

    /**
     * @param string $meetPass
     */
    public function setMeetPass(string $meetPass): self
    {
        $this->meetPass = $meetPass;
        return $this;
    }

    /**
     * @return string
     */
    public function getMeetName(): ?string
    {
        return $this->meetName;
    }

    /**
     * @param string $meetName
     */
    public function setMeetName(string $meetName): self
    {
        $this->meetName = $meetName;
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

