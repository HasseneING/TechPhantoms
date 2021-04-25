<?php

namespace App\Controller;

use Twig\Extension\AbstractExtension;
use Twig\TwigFilter;

class ExtController extends AbstractExtension
{
    public function getFilters(): array
    {
        return [
            new TwigFilter('price', [$this, 'formatCard']),
        ];
    }
    public function formatCard($mcard_Number): string
    {

        return str_pad(substr($mcard_Number, -4), strlen($mcard_Number), '*', STR_PAD_LEFT);
    }
}
?>