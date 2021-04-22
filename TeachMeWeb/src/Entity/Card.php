<?php

namespace App\Entity;

use App\Repository\CardRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=CardRepository::class)
 */
class Card
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="First name should not be blank")
     */
    private $firstName;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Last name should not be blank")
     */
    private $lastName;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Email should not be blank")
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email.")
     */
    private $Email;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\CardScheme(
     *     schemes={"VISA"},
     *     message="Your credit card number is invalid."
     * )
     */
    private $cardNumber;

    /**
     * @ORM\Column(type="string", length=2)
     */
    private $MM;

    /**
     * @ORM\Column(type="string", length=2)
     */
    private $YY;

    /**
     * @ORM\Column(type="string", length=3)
     */
    private $CVV;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getFirstName(): ?string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): self
    {
        $this->firstName = $firstName;

        return $this;
    }

    public function getLastName(): ?string
    {
        return $this->lastName;
    }

    public function setLastName(string $lastName): self
    {
        $this->lastName = $lastName;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->Email;
    }

    public function setEmail(string $Email): self
    {
        $this->Email = $Email;

        return $this;
    }

    public function getCardNumber(): ?string
    {
        return $this->cardNumber;
    }

    public function setCardNumber(string $cardNumber): self
    {
        $this->cardNumber = $cardNumber;

        return $this;
    }

    public function getMM(): ?string
    {
        return $this->MM;
    }

    public function setMM(string $MM): self
    {
        $this->MM = $MM;

        return $this;
    }

    public function getYY(): ?string
    {
        return $this->YY;
    }

    public function setYY(string $YY): self
    {
        $this->YY = $YY;

        return $this;
    }

    public function getCVV(): ?string
    {
        return $this->CVV;
    }

    public function setCVV(string $CVV): self
    {
        $this->CVV = $CVV;

        return $this;
    }
}
