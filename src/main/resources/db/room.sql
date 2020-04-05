
DROP TABLE IF EXISTS room;

CREATE TABLE room
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	title VARCHAR(30) NULL DEFAULT NULL COMMENT '房间主题',
	starttime VARCHAR(30) NULL DEFAULT NULL COMMENT '开始时间',
	endtime VARCHAR(30) NULL DEFAULT NULL COMMENT '结束时间',
	roomnum VARCHAR(100) NULL DEFAULT NULL COMMENT '房间号',
	userlist VARCHAR(500) NULL DEFAULT NULL COMMENT '参会人员列表',
	owner VARCHAR(500) NULL DEFAULT NULL COMMENT '创建人',
	PRIMARY KEY (id),
	UNIQUE (roomnum)
);

DELETE FROM user;

INSERT INTO room (id, title, starttime, endtime, roomnum, userlist, owner) VALUES
(null, '铁路干线讨论会', '202003121220', '202003121220', '1333', 'zhongkongshi,jingwushi', 'admin'),
(null, '应急是屁讨论会', '202003121220', '202003121220', '133', 'zhongkongshi,jingwushi', 'admin'),
(null, '新干线讨论会', '202003121220', '202003121220', '123', 'zhongkongshi,jingwushi', 'admin')