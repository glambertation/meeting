
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	username VARCHAR(30) NULL DEFAULT NULL COMMENT '登录名',
	password VARCHAR(30) NULL DEFAULT NULL COMMENT '密码',
	department VARCHAR(30) NULL DEFAULT NULL COMMENT '工作组',
	phone VARCHAR(30) NULL DEFAULT NULL COMMENT '工作组电话',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO user (id, username, password, department, phone, email) VALUES
(null, 'zhongkongshi', '123', '中控室', '13311298603', 'smile1@ityouknow.com'),
(null, 'jingwushi', '123', '警务室', '13311298603', 'smile1@ityouknow.com'),
(null, 'chejianshi', '123', '车间室', '13311298603', 'smile1@ityouknow.com')