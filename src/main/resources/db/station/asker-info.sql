
DROP TABLE IF EXISTS askerinfo;

CREATE TABLE askerinfo
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	ask_id VARCHAR(100) NULL DEFAULT NULL  COMMENT 'ask_ID' ,
	location VARCHAR(30) NULL DEFAULT NULL COMMENT '求助位置名称',
	machine_id VARCHAR(30) NULL DEFAULT NULL COMMENT '求助设备编号',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	sex VARCHAR(30) NULL DEFAULT NULL COMMENT '性别',
	identity VARCHAR(30) NULL DEFAULT NULL COMMENT '身份证号',
	video_url VARCHAR(500) NULL DEFAULT NULL COMMENT '求助者url',
	PRIMARY KEY (id)
);

DELETE FROM askerinfo;

INSERT INTO askerinfo (id, ask_id, location, machine_id, name, sex, identity, video_url) VALUES
(null, '1', '售票厅东南角', 'SPT03', '张三', '男', '1101010100100110011','www.baidu.com'),
(null, '2', '售票厅西北角', 'SPT04', '李四', '男', '1101010100100110011','www.baidu.com'),
(null, '3', '售票厅中心', 'SPT05', '王五', '男', '1101010100100110011','www.baidu.com')