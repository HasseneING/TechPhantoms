<?php

namespace App\Entity;

use App\Repository\NiveauRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=NiveauRepository::class)
 */
class Niveau
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_n", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idN;

    /**
     *   ** @Assert\Length(
     *
     *      max = 4,)

     * @var string
     *
     * @ORM\Column(name="nom_niv", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="NomNiv is required")
     */
    private $nomNiv;

    /**
     * @var int
     *
     * @ORM\Column(name="id_teacher", type="integer", nullable=false)
     */
    private $idTeacher;

    /**
     * @return int
     */
    public function getIdN(): ?int
    {
        return $this->idN;
    }

    /**
     * @param int $idN
     */
    public function setIdN(int $idN): self
    {
        $this->idN = $idN;
    }

    /**
     * @return string
     */
    public function getNomNiv(): ?string
    {
        return $this->nomNiv;
    }

    /**
     * @param string $nomNiv

     */
    public function setNomNiv(string $nomNiv): self
    {
        $this->nomNiv = $nomNiv;
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
