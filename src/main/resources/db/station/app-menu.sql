DROP TABLE IF EXISTS `app_menu`;

CREATE TABLE `app_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `app_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `app_menu`(`id`,`url`,`path`,`component`,`name`,`iconCls`,`keepAlive`,`requireAuth`,`parentId`,`enabled`) values
(1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),
(2,'/','/home','Home','员工资料','fa fa-user-circle-o',NULL,1,1,1),
(3,'/','/home','Home','人事管理','fa fa-address-card-o',NULL,1,1,1),
(4,'/','/home','Home','薪资管理','fa fa-money',NULL,1,1,1),
(5,'/','/home','Home','统计管理','fa fa-bar-chart',NULL,1,1,1),
(6,'/','/home','Home','系统管理','fa fa-windows',NULL,1,1,1),
(7,'/employee/basic/**','/emp/basic','EmpBasic','基本资料',NULL,NULL,1,2,1),
(8,'/employee/advanced/**','/emp/adv','EmpAdv','高级资料',NULL,NULL,1,2,1),
(9,'/personnel/emp/**','/per/emp','PerEmp','员工资料',NULL,NULL,1,3,1),
(10,'/personnel/ec/**','/per/ec','PerEc','员工奖惩',NULL,NULL,1,3,1),
(11,'/personnel/train/**','/per/train','PerTrain','员工培训',NULL,NULL,1,3,1),
(12,'/personnel/salary/**','/per/salary','PerSalary','员工调薪',NULL,NULL,1,3,1),
(13,'/personnel/remove/**','/per/mv','PerMv','员工调动',NULL,NULL,1,3,1),
(14,'/salary/sob/**','/sal/sob','SalSob','工资账套管理',NULL,NULL,1,4,1),
(15,'/salary/sobcfg/**','/sal/sobcfg','SalSobCfg','员工账套设置',NULL,NULL,1,4,1),
(16,'/salary/table/**','/sal/table','SalTable','工资表管理',NULL,NULL,1,4,1),
(17,'/salary/month/**','/sal/month','SalMonth','月末处理',NULL,NULL,1,4,1),
(18,'/salary/search/**','/sal/search','SalSearch','工资表查询',NULL,NULL,1,4,1),
(19,'/statistics/all/**','/sta/all','StaAll','综合信息统计',NULL,NULL,1,5,1),
(20,'/statistics/score/**','/sta/score','StaScore','员工积分统计',NULL,NULL,1,5,1),
(21,'/statistics/personnel/**','/sta/pers','StaPers','人事信息统计',NULL,NULL,1,5,1),
(22,'/statistics/recored/**','/sta/record','StaRecord','人事记录统计',NULL,NULL,1,5,1),
(23,'/system/basic/**','/sys/basic','SysBasic','基础信息设置',NULL,NULL,1,6,1),
(24,'/system/cfg/**','/sys/cfg','SysCfg','系统管理',NULL,NULL,1,6,1),
(25,'/system/log/**','/sys/log','SysLog','操作日志管理',NULL,NULL,1,6,1),
(26,'/system/hr/**','/sys/hr','SysHr','操作员管理',NULL,NULL,1,6,1),
(27,'/system/data/**','/sys/data','SysData','备份恢复数据库',NULL,NULL,1,6,1),
(28,'/system/init/**','/sys/init','SysInit','初始化数据库',NULL,NULL,1,6,1);