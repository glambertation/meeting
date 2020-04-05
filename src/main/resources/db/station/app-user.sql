
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	username VARCHAR(30) NULL DEFAULT NULL COMMENT '用户名',
	password VARCHAR(300) NULL DEFAULT NULL COMMENT '密码',
	name varchar(32) DEFAULT NULL COMMENT '姓名',
	nickname VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	sex VARCHAR(10) NULL DEFAULT NULL COMMENT '性别',
	phone VARCHAR(50) NULL DEFAULT NULL COMMENT '手机号码',
	address varchar(64) DEFAULT NULL COMMENT '联系地址',
	headImgUrl VARCHAR(30) NULL DEFAULT NULL COMMENT '头像',
	enabled VARCHAR(10) DEFAULT '1',
	type VARCHAR(50) NULL DEFAULT NULL COMMENT '类型',
	createTime VARCHAR(50)  NULL DEFAULT NULL COMMENT '修改时间',
	updateTime VARCHAR(50)  NULL DEFAULT NULL COMMENT '创建时间',
	remark varchar(255) NULL DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (id)
);

DELETE FROM app_user;

INSERT INTO app_user (id, username, password, name, nickname, sex, phone, address, headImgUrl, enabled, type, createTime, updateTime, remark) VALUES
(null, 'zhangsan', '$2a$10$2O4EwLrrFPEboTfDOtC0F.RpUMk.3q3KvBHRx7XXKUMLBGjOOBs8q', '张三', '小张', '男', '13311298603', '东北角', 'www.baidu.com', '1','外包','202021012', '20201111', null)