<?php

namespace App\Entity;

use App\Repository\MatiereRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=MatiereRepository::class)
 */
class Matiere
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
     *@Assert\NotBlank(message="Nom is required")
     * @ORM\Column(name="nom", type="string", length=255, nullable=true)
     */
    private $nom;

    /** *  @Assert\Length(
     *
     *      min = 4,)
     * @Assert\NotBlank(message="Type is required")
     *
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     *  @Assert\Length(
     *
     *      max = 3,)
     * @Assert\NotBlank(message="Diponibility is required")
     * @var string
     *
     * @ORM\Column(name="disponibility", type="string", length=30, nullable=true)
     */
    private $disponibility;

    /**
     * @var int
     *
     * @ORM\Column(name="id_teacher", type="integer", nullable=true)
     */
    private $idTeacher;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }



    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom

     */

    public function setNom(string $nom): self
    {
        $this->nom = $nom;
        return $this;
    }

    /**
     * @return string
     */
    public function getType(): ?string
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): ?self
    {
        $this->type = $type;
        return $this;
    }

    /**
     *   ** @Assert\Length(
     *
     *      max = 3,)
     * @return string
     */
    public function getDisponibility(): ?string
    {
        return $this->disponibility;
    }

    /**
     * @param string $disponibility
     */
    public function setDisponibility(string $disponibility): self
    {
        $this->disponibility = $disponibility;
        return $this;
    }

    /**
     * @return int
     */
    public function getIdTeacher(): ?int
    {
        return $this->idTeacher;
    }

    /**
     * @param int $idTeacher
     */
    public function setIdTeacher(int $idTeacher): self
    {
        $this->idTeacher = $idTeacher;
        return $this;
    }
}
