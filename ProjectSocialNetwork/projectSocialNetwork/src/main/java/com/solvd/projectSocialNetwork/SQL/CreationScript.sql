-- MySQL Script generated by MySQL Workbench
-- Thu Apr 22 20:50:04 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `lastname` VARCHAR(25) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `registerAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `profile` TEXT NULL,
  `mobile` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`userFriends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`userFriends` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sourceId` BIGINT NOT NULL,
  `targetId` BIGINT NULL,
  `friendType` SMALLINT(6) NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` SMALLINT(6) NOT NULL,
  `notes` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_userFriends_users_idx` (`sourceId` ASC) VISIBLE,
  INDEX `fk_userFriends_users1_idx` (`targetId` ASC) VISIBLE,
  CONSTRAINT `fk_userFriends_users`
    FOREIGN KEY (`sourceId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_userFriends_users1`
    FOREIGN KEY (`targetId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`posts` (
  `id` BIGINT NOT NULL,
  `message` VARCHAR(100) NOT NULL,
  `referenceLink` VARCHAR(45) NULL,
  `usersId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posts_users1_idx` (`usersId` ASC) VISIBLE,
  CONSTRAINT `fk_posts_users1`
    FOREIGN KEY (`usersId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`multimedia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`multimedia` (
  `id` BIGINT NOT NULL,
  `type` CHAR NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `posts_id` BIGINT NULL,
  `link` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_multimedia_posts1_idx` (`posts_id` ASC) VISIBLE,
  CONSTRAINT `fk_multimedia_posts1`
    FOREIGN KEY (`posts_id`)
    REFERENCES `mydb`.`posts` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`messages` (
  `id` BIGINT NOT NULL,
  `message` TEXT NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sourceId` BIGINT  NULL,
  `targetId` BIGINT  NULL,
  `multimediaId` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_messages_users1_idx` (`sourceId` ASC) VISIBLE,
  INDEX `fk_messages_users2_idx` (`targetId` ASC) VISIBLE,
  INDEX `fk_messages_multimedia1_idx` (`multimediaId` ASC) VISIBLE,
  CONSTRAINT `fk_messages_users1`
    FOREIGN KEY (`sourceId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO CASCADE,
  CONSTRAINT `fk_messages_users2`
    FOREIGN KEY (`targetId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_messages_multimedia1`
    FOREIGN KEY (`multimediaId`)
    REFERENCES `mydb`.`multimedia` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`logIns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`logIns` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `validationNumber` SMALLINT NOT NULL,
  `usersId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_logIns_users1_idx` (`usersId` ASC) VISIBLE,
  CONSTRAINT `fk_logIns_users1`
    FOREIGN KEY (`usersId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usergroups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usergroups` (
  `id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `themes` VARCHAR(60) NOT NULL,
  `createdBy` BIGINT NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_groups_users1_idx` (`createdBy` ASC) VISIBLE,
  CONSTRAINT `fk_groups_users1`
    FOREIGN KEY (`createdBy`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`groupMessages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`groupMessages` (
  `id` BIGINT NOT NULL,
  `message` TEXT NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `groupsId` BIGINT NOT NULL,
  `usersId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_groupMessages_groups1_idx` (`groupsId` ASC) VISIBLE,
  INDEX `fk_groupMessages_users1_idx` (`usersId` ASC) VISIBLE,
  CONSTRAINT `fk_groupMessages_groups1`
    FOREIGN KEY (`groupsId`)
    REFERENCES `mydb`.`usergroups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_groupMessages_users1`
    FOREIGN KEY (`usersId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`groupMembers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`groupMembers` (
  `id` BIGINT NOT NULL,
  `role` SMALLINT NOT NULL,
  `status` SMALLINT NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `groupsId` BIGINT NOT NULL,
  `usersId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_groupMembers_groups1_idx` (`groupsId` ASC) VISIBLE,
  INDEX `fk_groupMembers_users1_idx` (`usersId` ASC) VISIBLE,
  CONSTRAINT `fk_groupMembers_groups1`
    FOREIGN KEY (`groupsId`)
    REFERENCES `mydb`.`usergroups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_groupMembers_users1`
    FOREIGN KEY (`usersId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`videos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`videos` (
  `id` BIGINT NOT NULL,
  `duration` DOUBLE NOT NULL,
  `contents` VARCHAR(45) NULL,
  `multimediaId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_videos_multimedia1_idx` (`multimediaId` ASC) VISIBLE,
  CONSTRAINT `fk_videos_multimedia1`
    FOREIGN KEY (`multimediaId`)
    REFERENCES `mydb`.`multimedia` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`audios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`audios` (
  `id` BIGINT NOT NULL,
  `duration` DOUBLE NOT NULL,
  `multimediaId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_audios_multimedia2_idx` (`multimediaId` ASC) VISIBLE,
  CONSTRAINT `fk_audios_multimedia2`
    FOREIGN KEY (`multimediaId`)
    REFERENCES `mydb`.`multimedia` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`photos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`photos` (
  `id` BIGINT NOT NULL,
  `multimediaId` BIGINT NOT NULL,
  `resolution` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_photos_multimedia2_idx` (`multimediaId` ASC) VISIBLE,
  CONSTRAINT `fk_photos_multimedia2`
    FOREIGN KEY (`multimediaId`)
    REFERENCES `mydb`.`multimedia` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
