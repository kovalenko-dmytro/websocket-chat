CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `users` (`name`) VALUES
 ('Аркадий'),
 ('Борис'),
 ('Валерий'),
 ('Григорий'),
 ('Дмитрий');

 CREATE TABLE `chat_rooms` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(45) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
);

 INSERT INTO `chat_rooms`(`room_name`, `user_id`) VALUES
('all', 0);

CREATE TABLE `messages` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_type` varchar(20) NOT NULL,
  `message_content` longtext NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  `receiver_id` bigint(20) NOT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`message_id`),
  FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`room_id`) REFERENCES `chat_rooms` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `chat_room_user` (
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  FOREIGN KEY (`room_id`) REFERENCES `chat_rooms` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
