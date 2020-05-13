DROP TABLE IF EXISTS sendtext;

CREATE TABLE sendtext
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	sender VARCHAR(30) NULL DEFAULT NULL COMMENT '发信人',
	text VARCHAR(300) NULL DEFAULT NULL COMMENT '内容',
	date varchar(32) DEFAULT NULL COMMENT '日期',
	roomtoken VARCHAR(30) NULL DEFAULT NULL COMMENT '会议室id',
	fromid VARCHAR(30) NULL DEFAULT NULL COMMENT '发信',
	role VARCHAR(30) NULL DEFAULT NULL COMMENT '职级',
	PRIMARY KEY (id)
);


DELETE FROM sendtext;

INSERT INTO sendtext (id, sender, text, date, roomtoken, fromid, role) VALUES
('1','zhangsan', '啦啦啦测试哦', '2020-05-12 15:37:15"',
'853f1914-b6b9-5a7f-bcc7-74387efbf61','zhangsan', '客运处')