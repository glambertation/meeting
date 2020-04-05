
DROP TABLE IF EXISTS askqueue;

CREATE TABLE askqueue
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	ask_id VARCHAR(300) NULL DEFAULT NULL COMMENT '求助id',
	video_url VARCHAR(300) NULL DEFAULT NULL COMMENT '对应url视频',
	time VARCHAR(300) NULL DEFAULT NULL COMMENT '求助时间',
	success VARCHAR(30) NULL DEFAULT NULL COMMENT '是否接通  1接通 0未接通',
	pause VARCHAR(30) NULL DEFAULT NULL COMMENT '是否暂停 1暂停 0未暂停',
	pause_time VARCHAR(50) NULL DEFAULT NULL COMMENT '停止时间',
	machine_number VARCHAR(500) NULL DEFAULT NULL COMMENT '求助位置机器编号',
	machine_zone VARCHAR(50) NULL DEFAULT NULL COMMENT '求助位置名称区域',
	handle VARCHAR(50) NULL DEFAULT NULL COMMENT '是否处理',
	PRIMARY KEY (id)
);

DELETE FROM askqueue;

INSERT INTO askqueue (id, ask_id, video_url, time, success, pause, pause_time, machine_number, machine_zone, handle) VALUES
(null, '1', 'www.baidu.com', '202003261708', '1', '0',null,'SPT03','售票厅东南角', '0'),
(null, '2', 'www.baidu.com', '202003261708', '0', '1','20200318','SPT04','售票厅东南角', '0'),
(null, '3', 'www.baidu.com', '202003261708', '0', '0',null,'SPT05','售票厅东南角', '0'),
(null, '4', 'www.baidu.com', '202003261708', '0', '0',null,'SPT06','售票厅东南角', '0'),
(null, '5', 'www.baidu.com', '202003261708', '0', '0',null,'SPT07','售票厅东南角', '0')