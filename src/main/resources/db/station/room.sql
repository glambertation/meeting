DROP TABLE IF EXISTS room;

CREATE TABLE room
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	sender VARCHAR(30) NULL DEFAULT NULL COMMENT '发信人',
	invite VARCHAR(300) NULL DEFAULT NULL COMMENT '邀请列表',
	date varchar(32) DEFAULT NULL COMMENT '日期',
	roomtoken VARCHAR(300) NULL DEFAULT NULL COMMENT '会议室id',
	joiner VARCHAR(300) NULL DEFAULT NULL COMMENT '会议室id',
	fromid VARCHAR(30) NULL DEFAULT NULL COMMENT '发信',
	role VARCHAR(30) NULL DEFAULT NULL COMMENT '职级',
	PRIMARY KEY (id)
);


DELETE FROM room;

INSERT INTO room (id, sender, invite, date, roomtoken, joiner, fromid, role) VALUES
('1','zhangsan', 'zhangsan,lisi,wangwu', '2020-05-12 15:37:15',
'853f1914-b6b9-5a7f-bcc7-74387efbf61', 'zhangsan,lisi', 'zhangsan', '客运处')