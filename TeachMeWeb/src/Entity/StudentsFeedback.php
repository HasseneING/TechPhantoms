<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints\DateTime ;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * StudentsFeedback
 *
 * @ORM\Table(name="students_feedback")
 * @ORM\Entity(repositoryClass="App\Repository\StudentsFeedbackRepository")
 */
class StudentsFeedback
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
     * @Assert\NotBlank(message="Comment is required")
     * @ORM\Column(name="comment", type="string", length=600, nullable=false)
     */
    private $comment;

    /**
     * @var int
     * @Assert\NotBlank(message="Note is required")
     * @ORM\Column(name="note", type="integer", nullable=false)
     */
    private $note;

    /**
     * @var bool
     *
     * @ORM\Column(name="recommandation", type="boolean", nullable=false)
     */
    private $recommandation;

    /**
     * @var int
     * @Assert\NotBlank(message="Rating is required")
     * @ORM\Column(name="rating", type="integer", nullable=false)
     */
    private $rating;

    /**
     * @var int
     *@Assert\NotBlank(message="Tutor is required")
     * @ORM\Column(name="id_tutor", type="integer", nullable=false)
     */
    private $idTutor;

    /**
     * @var int
     *@Assert\NotBlank(message="Student is required")
     * @ORM\Column(name="id_sutdent", type="integer", nullable=false)
     */
    private $idSutdent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date", type="date")
     *
     */

    private $date ;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getComment(): ?string
    {
        return $this->comment;
    }

    public function setComment(string $comment): self
    {
        $this->comment = $comment;

        return $this;
    }

    public function getNote(): ?int
    {
        return $this->note;
    }

    public function setNote(int $note): self
    {
        $this->note = $note;

        return $this;
    }

    public function getRecommandation(): ?bool
    {
        return $this->recommandation;
    }

    public function setRecommandation(bool $recommandation): self
    {
        $this->recommandation = $recommandation;

        return $this;
    }

    public function getRating(): ?int
    {
        return $this->rating;
    }

    public function setRating(int $rating): self
    {
        $this->rating = $rating;

        return $this;
    }

    public function getIdTutor(): ?int
    {
        return $this->idTutor;
    }

    public function setIdTutor(int $idTutor): self
    {
        $this->idTutor = $idTutor;

        return $this;
    }

    public function getIdSutdent(): ?int
    {
        return $this->idSutdent;
    }

    public function setIdSutdent(int $idSutdent): self
    {
        $this->idSutdent = $idSutdent;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {


        return  ($this->date);
            //$this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }


}
