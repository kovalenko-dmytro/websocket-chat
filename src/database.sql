CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `messages` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_type` varchar(20) NOT NULL,
  `message_content` longtext NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  `receiver_id` bigint(20) NOT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_messages_1_idx` (`sender_id`),
  KEY `fk_messages_2_idx` (`room_id`),
  KEY `fk_messages_3_idx` (`receiver_id`),
  CONSTRAINT `fk_messages_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_2` FOREIGN KEY (`room_id`) REFERENCES `chat_rooms` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_3` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8;

CREATE TABLE `chat_rooms` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(45) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

CREATE TABLE `chat_room_user` (
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `fk_chat_room_user_1_idx` (`room_id`),
  KEY `fk_chat_room_user_2_idx` (`user_id`),
  CONSTRAINT `fk_chat_room_user_1` FOREIGN KEY (`room_id`) REFERENCES `chat_rooms` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_room_user_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `demo_chat`.`users` (`name`) VALUES
 ('Аркадий'),
 ('Борис'),
 ('Валерий'),
 ('Григорий'),
 ('Дмитрий');

 INSERT INTO `demo_chat`.`chat_rooms`(`room_name`, `user_id`) VALUES
('all', 0);
