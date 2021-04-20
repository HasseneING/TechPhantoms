<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Meet
 *
 * @ORM\Table(name="meet", indexes={@ORM\Index(name="FK_6117D13B6C755", columns={"teacherID"})})
 * @ORM\Entity
 */
class Meet
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
     * @ORM\Column(name="meet_link", type="string", length=250, nullable=false)
     */
    private $meetLink;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="meet_date", type="datetime", nullable=true)
     */
    private $meetDate ;

    /**
     * @var string
     *
     * @ORM\Column(name="meet_pass", type="string", length=255, nullable=false)
     */
    private $meetPass;

    /**
     * @var string
     *
     * @ORM\Column(name="meet_name", type="string", length=255, nullable=false)
     */
    private $meetName;

    /**
     * @var \Teacher
     *
     * @ORM\ManyToOne(targetEntity="Teacher")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="teacherID", referencedColumnName="teacher_id")
     * })
     */
    private $teacherid;

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
    public function getMeetLink(): string
    {
        return $this->meetLink;
    }

    /**
     * @param string $meetLink
     */
    public function setMeetLink(string $meetLink): void
    {
        $this->meetLink = $meetLink;
    }

    /**
     * @return \DateTime
     */
    public function getMeetDate()
    {
        return $this->meetDate;
    }

    /**
     * @param \DateTime $meetDate
     */
    public function setMeetDate($meetDate): void
    {
        $this->meetDate = $meetDate;
    }

    /**
     * @return string
     */
    public function getMeetPass(): string
    {
        return $this->meetPass;
    }

    /**
     * @param string $meetPass
     */
    public function setMeetPass(string $meetPass): void
    {
        $this->meetPass = $meetPass;
    }

    /**
     * @return string
     */
    public function getMeetName(): string
    {
        return $this->meetName;
    }

    /**
     * @param string $meetName
     */
    public function setMeetName(string $meetName): void
    {
        $this->meetName = $meetName;
    }

    /**
     * @return \Teacher
     */
    public function getTeacherid(): \Teacher
    {
        return $this->teacherid;
    }

    /**
     * @param \Teacher $teacherid
     */
    public function setTeacherid(\Teacher $teacherid): void
    {
        $this->teacherid = $teacherid;
    }


}
