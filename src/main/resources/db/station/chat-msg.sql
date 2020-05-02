DROP TABLE IF EXISTS chatmsg;

CREATE TABLE chatmsg
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	fromid VARCHAR(30) NULL DEFAULT NULL COMMENT '发信人',
	toid VARCHAR(300) NULL DEFAULT NULL COMMENT '收信人',
	content varchar(32) DEFAULT NULL COMMENT '内容',
	date VARCHAR(30) NULL DEFAULT NULL COMMENT '日期',
	type VARCHAR(10) NULL DEFAULT NULL COMMENT '类型',
	status VARCHAR(10) DEFAULT '0' COMMENT '状态',
	PRIMARY KEY (id)
);

DELETE FROM chatmsg;

INSERT INTO chatmsg (id, fromid, toid, content, date, type, status) VALUES
('1','事件描述：此处有乘客吸烟导致行李失火。请速去现场解决，并与中控室保持联系，感谢配合', 'zhangsan', 'lisi',
'1588139124027','task', '0')