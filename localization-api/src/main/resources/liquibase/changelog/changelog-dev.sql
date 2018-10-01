--liquibase formatted sql

--changeset MHASSAN:initian-test-data-1
INSERT INTO `ROLE` (`id`,`name`) VALUES
(1,'ROLE_SUPER_ADMIN'),
(2,'ROLE_ADMIN'),
(3,'ROLE_MANAGER'),
(4,'ROLE_USER');

INSERT INTO `TENANT` (`id`,`name`,`url`) VALUES
(1,'super admin',NULL),
(2,'admin one',NULL),
(3,'admin two',NULL);

INSERT INTO `USER` (`id`,`tenant`,`email`,`ENABLED`,`firstName`,`lastName`,`lastPasswordResetDate`,`password`) VALUES
(1,1,'superadmin@tailor.com',NULL,'super','admin',NULL,'$2a$10$T/iRpIkyOkjnUX2dmx6v2.XQGe5WvyhpjiZTjHoTWbRcP57ctP02q'),
(2,2,'admin1@tailor.com',NULL,'admin',NULL,NULL,'$2a$10$TtkRs2XU3aAf0d8kMKO4COXlOqFzNM5vpvWeqdAkuzmxwswVuJHaC'),
(3,3,'admin2@gmail.com',NULL,'admin','two',NULL,'$2a$10$Yf4mkZQjTTeY3ixmkIWP9.8fQqJpKtyy7qblBNN/MJt3dDeYVnl6i');

INSERT INTO `USER_ROLE` (`USER_ID`,`ROLE_ID`) VALUES
(1,1),
(2,2),
(3,2);

--INSERT INTO `PROJECT` (`id`,`name`,`url`) VALUES
--(1,'sendai','https://sendaimonitortra,ining.unisdr.org'),
--(2,'chumbok','https://chumbok.com/'),
--(3,'businessrunner','http://business.chumbok.com'),
--(4,'businessrunnerdev','http://dev.chumbok.com:7070/#!/');
--
--INSERT INTO `LANGUAGE` (`id`,`code`,`isActive`,`isRtl`,`name`,`project_id`) VALUES
--(1,'en',NULL,NULL,'English',1),
--(2,'fr',NULL,NULL,'Français',1),
--(3,'es',NULL,NULL,'Español',1),
--(4,'ar',NULL,NULL,'العربية',1),
--(5,'ru',NULL,NULL,'Русский',1),
--(6,'zh',NULL,NULL,'中文',1),
--(7,'bn',NULL,NULL,'বাংলা',1);
--
--INSERT INTO `LOCALIZATION_KEY` (`id`,`langKey`,`project_id`) VALUES
--(1,'action.addMore',1),
--(2,'action.back',1),
--(3,'action.backToLogin',1),
--(4,'action.calculate.compound',1),
--(5,'action.cancel',1),
--(6,'action.close',1),
--(7,'action.confirm',1),
--(8,'action.done',1),
--(9,'action.download',1),
--(10,'action.exit',1);
--
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (1,'ADD MORE',1,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (2,'BACK',1,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (3,'GO BACK TO LOGIN PAGE',1,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (4,'Calculate compound indicator',1,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (5,'CANCEL',1,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (6,'CLOSE',1,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (7,'CONFIRM',1,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (8,'DONE',1,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (9,'Download',1,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (19,'Télécharger',2,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (10,'EXIT',1,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (11,'Ajouter',2,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (12,'Retour',2,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (13,'Retourner à la page d\'accueil',2,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (14,'Calcul l\'indicateur composé',2,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (15,'Annuler',2,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (16,'Fermer',2,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (17,'Confirmer',2,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (18,'Effectué',2,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (20,'Quitter',2,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (21,'AÑADIR MÁS',3,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (22,'VOLVER',3,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (23,'VOLVER A LA PÁGINA INICIO SESIÓN',3,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (24,'Calcular el indicador compuesto',3,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (25,'BORRAR',3,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (26,'CERRAR',3,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (27,'CONFIRMAR',3,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (28,'HECHO',3,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (29,'Descargar',3,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (30,'SALIR',3,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (31,'أضف المزيد',4,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (32,'رجوع',4,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (33,'عد إلى صفحة الدخول',4,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (34,'احسب المؤشر المركب',4,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (35,'الغاء',4,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (36,'إغلاق',4,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (37,'تأكيد',4,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (38,'تم',4,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (39,'تنزيل',4,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (40,'خروج',4,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (41,'ДОБАВИТЬ ЕЩЁ',5,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (42,'НАЗАД',5,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (43,'ВЕРНУТЬСЯ на страницу авторизации',5,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (44,'Рассчитать составной индикатор {{indicatorName}}',5,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (45,'ОТМЕНИТЬ',5,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (46,'ЗАКРЫТЬ',5,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (47,'ПОДТВЕРДИТЬ',5,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (48,'ГОТОВО',5,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (49,'Скачать',5,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (50,'ВЫХОД',5,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (51,'添加更多',6,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (52,'返回',6,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (53,'返回至登录页面',6,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (54,'计算复合指标',6,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (55,'取消',6,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (56,'关闭',6,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (57,'确认',6,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (58,'完成',6,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (59,'下载',6,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (60,'退出',6,10);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (61,'আরো যোগ করুন',7,1);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (62,'পিছনে যান',7,2);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (63,'লগইন পেজে যান',7,3);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (64,'ক্যালকুলেট জটিল',7,4);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (65,'বাতিল',7,5);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (66,'বন্ধ',7,6);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (67,'কনফার্ম',7,7);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (68,'শেষ',7,8);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (69,'ডাউনলোড',7,9);
--INSERT INTO `LOCALIZATION_VALUE` (`id`,`value`,`language_id`,`localizationKey_id`) VALUES (70,'বাহির',7,10);