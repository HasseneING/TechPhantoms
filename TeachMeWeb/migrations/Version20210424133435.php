<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210424133435 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE meet (meet_id INT AUTO_INCREMENT NOT NULL, tutorid_id INT DEFAULT NULL, studentid_id INT DEFAULT NULL, meet_link VARCHAR(250) DEFAULT NULL, meet_date DATETIME DEFAULT NULL, meet_pass VARCHAR(255) DEFAULT NULL, meet_name VARCHAR(255) DEFAULT NULL, INDEX IDX_E9F6D3CEC74ADE8 (tutorid_id), INDEX IDX_E9F6D3CE1D429594 (studentid_id), PRIMARY KEY(meet_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (reservation_id INT AUTO_INCREMENT NOT NULL, tutorid_id INT NOT NULL, studentid_id INT NOT NULL, reservation_date DATETIME DEFAULT NULL, INDEX IDX_42C84955C74ADE8 (tutorid_id), INDEX IDX_42C849551D429594 (studentid_id), PRIMARY KEY(reservation_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE student (id INT AUTO_INCREMENT NOT NULL, username VARCHAR(30) NOT NULL, email VARCHAR(30) NOT NULL, password VARCHAR(30) NOT NULL, first_name VARCHAR(30) NOT NULL, last_name VARCHAR(30) NOT NULL, cin INT NOT NULL, status VARCHAR(30) NOT NULL, date_birth DATE NOT NULL, date_add DATETIME NOT NULL, profile_pic VARCHAR(50) NOT NULL, state VARCHAR(20) NOT NULL, phone_number INT NOT NULL, nb_formation_enrg VARCHAR(30) NOT NULL, active_formation VARCHAR(30) NOT NULL, finished_formation VARCHAR(30) NOT NULL, schedule INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE token (id INT AUTO_INCREMENT NOT NULL, access_token TEXT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE tutor (id INT AUTO_INCREMENT NOT NULL, username VARCHAR(30) NOT NULL, email VARCHAR(30) NOT NULL, password VARCHAR(30) NOT NULL, first_name VARCHAR(30) NOT NULL, last_name VARCHAR(30) NOT NULL, cin INT NOT NULL, status INT NOT NULL, date_birth DATE NOT NULL, date_add DATETIME NOT NULL, profile_picture VARCHAR(50) NOT NULL, state VARCHAR(30) NOT NULL, phone_number INT NOT NULL, nb_formation INT NOT NULL, certificats VARCHAR(30) NOT NULL, nb_student INT NOT NULL, price_hour INT NOT NULL, schedule INT NOT NULL, cv VARCHAR(30) NOT NULL, video VARCHAR(50) NOT NULL, subject VARCHAR(30) NOT NULL, language VARCHAR(30) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE meet ADD CONSTRAINT FK_E9F6D3CEC74ADE8 FOREIGN KEY (tutorid_id) REFERENCES tutor (id)');
        $this->addSql('ALTER TABLE meet ADD CONSTRAINT FK_E9F6D3CE1D429594 FOREIGN KEY (studentid_id) REFERENCES student (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955C74ADE8 FOREIGN KEY (tutorid_id) REFERENCES tutor (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849551D429594 FOREIGN KEY (studentid_id) REFERENCES student (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE meet DROP FOREIGN KEY FK_E9F6D3CE1D429594');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849551D429594');
        $this->addSql('ALTER TABLE meet DROP FOREIGN KEY FK_E9F6D3CEC74ADE8');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955C74ADE8');
        $this->addSql('DROP TABLE meet');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE student');
        $this->addSql('DROP TABLE token');
        $this->addSql('DROP TABLE tutor');
    }
}
