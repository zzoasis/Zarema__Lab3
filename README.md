# Zarema__Lab3
## Работа с базами данных в Java через SPRING BEANS в NetBeans
## Вариант 5 - Человек
![Снимок](https://github.com/zzoasis/Zarema__Lab3/blob/master/Снимок.PNG)

![Снимок1](https://github.com/zzoasis/Zarema__Lab3/blob/master/Снимок1.PNG)
 
 ## Скрипт для создания таблицы:
 CREATE TABLE IF NOT EXISTS `human`.`human` (
    id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `country` VARCHAR(45) NOT NULL,
    `age` INT NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8;
SET GLOBAL sql_mode='';
INSERT INTO human (`name`, age, `country`) VALUES ("Мануэль", "34","Германия");
INSERT INTO human (`name`, age, `country`) VALUES ("Роберт", "32","Польша");
INSERT INTO human (`name`, age, `country`) VALUES ("Томас", "31","Германия");
INSERT INTO human (`name`, age, `country`) VALUES ("Серж", "25","Германия");
INSERT INTO human (`name`, age, `country`) VALUES ("Йозуа", "25","Германия");
INSERT INTO human (`name`, age, `country`) VALUES ("Альфонсо", "19","Канада");
INSERT INTO human (`name`, age, `country`) VALUES ("Марк", "23","Испания");
INSERT INTO human (`name`, age, `country`) VALUES ("Бенжамен", "24","Франция");
