--liquibase formatted sql

--changeset MHASSAN:initian-test-data-1
INSERT INTO `ROLE` (`id`,`name`) VALUES
('68877ff031cb4b5d9e0909bf5b61a2ec','ROLE_SUPER_ADMIN'),
('ce5c58ce8f574a23bfca67674ea3827e','ROLE_ADMIN'),
('85b625e02a824eccbd54a6fa833df314','ROLE_TRANSLATOR'),
('d4823fa455224acfacd4ddf337805104','ROLE_USER');

INSERT INTO `TENANT` (`id`,`name`,`url`) VALUES
('41c342a0d68442edb01dd0b16a4c6245','Tenant Super Admin',NULL),
('ff8081816642db91016642debd690000','Project Admin',NULL);

INSERT INTO `USER` (`id`,`tenant`,`email`,`ENABLED`,`firstName`,`lastName`,`lastPasswordResetDate`,`password`) VALUES
('5463a897ba23420cb0100378d11e8631','41c342a0d68442edb01dd0b16a4c6245','superadmin@gmail.com',NULL,'super','admin',NULL,'$2a$10$T/iRpIkyOkjnUX2dmx6v2.XQGe5WvyhpjiZTjHoTWbRcP57ctP02q'),
('ff80818166430f7401664315d70e0001','ff8081816642db91016642debd690000','admin@localization.com',NULL,'Project','Admin',NULL,'$2a$10$rYaDdLJn2/7vOo6V8cfLW.mIWzkGEk5sz/1dUW54QbyNgha2poetm');

INSERT INTO `USER_ROLE` (`USER_ID`,`ROLE_ID`) VALUES
('5463a897ba23420cb0100378d11e8631','68877ff031cb4b5d9e0909bf5b61a2ec'),
('ff80818166430f7401664315d70e0001','ce5c58ce8f574a23bfca67674ea3827e');
