
DROP TABLE IF EXISTS `test_utilities_db`.`user_roles`;
DROP TABLE IF EXISTS `test_utilities_db`.`work_planes`;
DROP TABLE IF EXISTS `test_utilities_db`.`orders`;
DROP TABLE IF EXISTS `test_utilities_db`.`employee_brigades`;
DROP TABLE IF EXISTS `test_utilities_db`.`users`;
DROP TABLE IF EXISTS `test_utilities_db`.`positions`;
DROP TABLE IF EXISTS `test_utilities_db`.`roles`;
DROP TABLE IF EXISTS `test_utilities_db`.`work_types`;
DROP TABLE IF EXISTS `test_utilities_db`.`units`;
DROP TABLE IF EXISTS `test_utilities_db`.`brigades`;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`positions`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `position` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `position_UNIQUE` (`position` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`users`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `house` VARCHAR(45) NULL,
  `flat` INT NULL,
  `building` VARCHAR(45) NULL,
  `position_id` INT NULL,
  `hiring_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  INDEX `position_id_idx` (`position_id` ASC) VISIBLE,
  CONSTRAINT `position_id`
    FOREIGN KEY (`position_id`)
    REFERENCES `test_utilities_db`.`positions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
ALTER TABLE `test_utilities_db`.`users` 
ADD UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`work_types`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `work_type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `work_types_UNIQUE` (`work_type` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`units`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`orders`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `work_type_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `scale_units` INT NOT NULL,
  `scale_value` DECIMAL(10,2) NOT NULL,
  `date_start` DATETIME NULL,
  `date_finish` DATETIME NULL,
  `severity` TINYINT NULL,
  `description` VARCHAR(255) NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC) VISIBLE,
  INDEX `work_type_idx` (`work_type_id` ASC) VISIBLE,
  INDEX `scale_units_idx` (`scale_units` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_utilities_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `work_type`
    FOREIGN KEY (`work_type_id`)
    REFERENCES `test_utilities_db`.`work_types` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `scale_units`
    FOREIGN KEY (`scale_units`)
    REFERENCES `test_utilities_db`.`units` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
ALTER TABLE `test_utilities_db`.`orders` 
ADD COLUMN `dispatcher_comment` VARCHAR(255) NULL AFTER `status`;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`brigades`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`work_planes`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `work_type` INT NOT NULL,
  `order_id` INT NOT NULL,
  `brigade_id` INT NOT NULL,
  `dispatcher_id` INT NOT NULL,
  `date_start` DATETIME NOT NULL,
  `date_finish` DATETIME NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `work_type_id_idx` (`work_type` ASC) VISIBLE,
  INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
  INDEX `brigade_id_idx` (`brigade_id` ASC) VISIBLE,
  INDEX `dispatcher_id_idx` (`dispatcher_id` ASC) VISIBLE,
  CONSTRAINT `work_type_id`
    FOREIGN KEY (`work_type`)
    REFERENCES `test_utilities_db`.`work_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `test_utilities_db`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `brigade`
    FOREIGN KEY (`brigade_id`)
    REFERENCES `test_utilities_db`.`brigades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dispatcher_id`
    FOREIGN KEY (`dispatcher_id`)
    REFERENCES `test_utilities_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`roles`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`employee_brigades`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `brigade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `employee_id_idx` (`employee_id` ASC) VISIBLE,
  INDEX `brigade_id_idx` (`brigade_id` ASC) VISIBLE,
  CONSTRAINT `employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `test_utilities_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `brigade_id`
    FOREIGN KEY (`brigade_id`)
    REFERENCES `test_utilities_db`.`brigades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test_utilities_db`.`user_roles`
(
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_utilities_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `test_utilities_db`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `test_utilities_db`.`roles` (`role`) VALUES ('admin');
INSERT INTO `test_utilities_db`.`roles` (`role`) VALUES ('dispatcher');
INSERT INTO `test_utilities_db`.`roles` (`role`) VALUES ('user');
INSERT INTO `test_utilities_db`.`roles` (`role`) VALUES ('employee');


INSERT INTO `test_utilities_db`.`work_types` (`work_type`) VALUES ('Уборка помещений');
INSERT INTO `test_utilities_db`.`work_types` (`work_type`) VALUES ('Электроснабжение');
INSERT INTO `test_utilities_db`.`work_types` (`work_type`) VALUES ('Водоснабжение');
INSERT INTO `test_utilities_db`.`work_types` (`work_type`) VALUES ('Отопление');
INSERT INTO `test_utilities_db`.`work_types` (`work_type`) VALUES ('Газоснабжение');


INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Уборщик');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Электрик');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Сантехник');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Бригадир');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Бухгалтер');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Системный администратор');
INSERT INTO `test_utilities_db`.`positions` (`position`) VALUES ('Диспетчер');


INSERT INTO `test_utilities_db`.`users` (`name`, `surname`, `login`, `password`, `phone_number`, `street`, `house`, `flat`, `building`, `position_id`, `hiring_date`) VALUES ('Василий', 'Волшебник', 'vasya_magic', '12345679', '+375291847896', 'Ленина', '123', '3', '2', '6', '2015-08-11');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '4');
INSERT INTO `test_utilities_db`.`users` (`name`, `surname`, `login`, `password`, `phone_number`, `street`, `house`, `flat`, `building`, `position_id`, `hiring_date`) VALUES ('Петр', 'Десятый', 'petya_ten', '999777', '+375291847897', 'Комунистов', '4', '55', '1', '7', '2020-05-17');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('2', '4');
INSERT INTO `test_utilities_db`.`users` (`name`, `surname`, `login`, `password`, `phone_number`, `street`, `house`, `flat`, `position_id`, `hiring_date`) VALUES ('Светлана', 'Светлана', 'zulu', 'zulu', '+375291847898', 'Василькова', '4234', '213', '5', '2008-11-04');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('3', '4');
INSERT INTO `test_utilities_db`.`users` (`name`, `surname`, `login`, `password`, `phone_number`, `street`, `house`, `flat`, `building`) VALUES ('Илья', 'Зычин', 'OOOoo', '23455', '+375291847899', 'Фомина', '57', '1', '3');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('4', '3');
INSERT INTO `test_utilities_db`.`users` (`name`, `surname`, `login`, `password`, `phone_number`, `street`, `house`, `flat`, `position_id`, `hiring_date`) VALUES ('Галина', 'Электра', 'gagalina', '999123', '+375291847890', 'Партизанский', '33', '89', '2', '1994-01-01');
INSERT INTO `test_utilities_db`.`user_roles` (`user_id`, `role_id`) VALUES ('5', '4');

INSERT INTO `test_utilities_db`.`units` (`title`) VALUES ('метры');
INSERT INTO `test_utilities_db`.`units` (`title`) VALUES ('часы');
INSERT INTO `test_utilities_db`.`units` (`title`) VALUES ('квадратные метры');
INSERT INTO `test_utilities_db`.`units` (`title`) VALUES ('кубические метры');
INSERT INTO `test_utilities_db`.`units` (`title`) VALUES ('штуки');

INSERT INTO `test_utilities_db`.`orders` (`work_type_id`, `user_id`, `scale_units`, `scale_value`, `date_start`, `date_finish`, `severity`, `description`, `status`) VALUES ('2', '4', '5', '10', '2020-09-16 13:00:00', '2020-09-16 15:00:00', '0', 'Замена автоматов', 'Новая');

INSERT INTO `test_utilities_db`.`brigades` (`description`) VALUES ('Бригада на 2020-09-16 13:00:00 на замену автоматов');

INSERT INTO `test_utilities_db`.`employee_brigades` (`employee_id`, `brigade_id`) VALUES ('5', '1');

INSERT INTO `test_utilities_db`.`work_planes` (`work_type`, `order_id`, `brigade_id`, `dispatcher_id`, `date_start`, `date_finish`, `comment`) VALUES ('2', '1', '1', '2', '2020-09-16 13:00:00', '2020-09-16 16:00:00', 'Электик приедет к назначенному времени. Работа займет 3, а не 2 часа');







