
DROP TABLE IF EXISTS `app_role`;

CREATE TABLE `app_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `app_role` */

insert  into `app_role`(`id`,`name`,`nameZh`) values
(1,'ROLE_manager','部门经理'),
(2,'ROLE_personnel','人事专员'),
(3,'ROLE_recruiter','招聘主管'),
(4,'ROLE_train','培训主管'),(5,'ROLE_performance','薪酬绩效主管'),
(6,'ROLE_admin','系统管理员'),(13,'ROLE_test2','测试角色2'),
(14,'ROLE_test1','测试角色1'),(17,'ROLE_test3','测试角色3'),
(18,'ROLE_test4','测试角色4'),(19,'ROLE_test4','测试角色4'),
(20,'ROLE_test5','测试角色5'),(21,'ROLE_test6','测试角色6');

/*Table structure for table `salary` */