#用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `role` varchar(32) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#用户登录ticket关联表
DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `ticket` VARCHAR(45) NOT NULL,
  `expired` DATETIME NOT NULL,
  `status` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#文章表
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `describes` VARCHAR(255) NOT NULL,
  `content` TEXT NULL,
  `created_date` DATETIME NOT NULL,
  `comment_count` INT NOT NULL,
  `category` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `date_index` (`created_date` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#分类表
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `image` VARCHAR(255) NOT NULL,
  `describes` VARCHAR(255) NOT NULL,
  `article_num` INT  NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#标签表
DROP TABLE IF EXISTS 'tag';
CREATE TABLE `tag` (
`id` INT NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(50) NOT NULL,
`count` INT NOT NULL DEFAULT 0,
PRIMARY KEY (`id`)
)

#标签文章表

DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`(
`id` INT NOT NULL AUTO_INCREMENT,
`article_id` INT NOT NULL,
`tag_id` INT NOT NULL,
PRIMARY KEY(`id`)
)






