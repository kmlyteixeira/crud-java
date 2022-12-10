-- -----------------------------------------------------
-- Schema pagamento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pagamento` DEFAULT CHARACTER SET utf8 ;
USE `pagamento` ;

-- -----------------------------------------------------
-- Table `pagamento`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pagamento`.`fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `pagamento`.`pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pagamento`.`pagamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `data` VARCHAR(10) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `status` TINYINT NOT NULL,
  `fornecedor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pagamento_fornecedor1_idx` (`fornecedor_id` ASC),
  CONSTRAINT `fk_pagamento_fornecedor1`
    FOREIGN KEY (`fornecedor_id`)
    REFERENCES `pagamento`.`fornecedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;