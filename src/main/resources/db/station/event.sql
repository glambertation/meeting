
DROP TABLE IF EXISTS event;

CREATE TABLE event
(
	id INT(10) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	askid VARCHAR(255) NULL DEFAULT NULL COMMENT '求助ID',
	name VARCHAR(255) NULL DEFAULT NULL COMMENT '求助者姓名',
	sex varchar(255) DEFAULT NULL COMMENT '性别',
	identity VARCHAR(255) NULL DEFAULT NULL COMMENT '身份证号',
	location VARCHAR(255) NULL DEFAULT NULL COMMENT '求助地理方位',
	machine_number VARCHAR(255) NULL DEFAULT NULL COMMENT '求助机器编码',
	success varchar(10) DEFAULT '0'COMMENT '是否接通',
	pause VARCHAR(10) DEFAULT '0' COMMENT '是否暂停',
	handle VARCHAR(10) DEFAULT '0' COMMENT '是否处理',
	username VARCHAR(255) NULL DEFAULT NULL COMMENT '处理人员',
	handle_result VARCHAR(1500) NULL DEFAULT NULL COMMENT '处理结果',
	handle_people VARCHAR(255)  NULL DEFAULT NULL COMMENT '任务派发人员',
	report VARCHAR(255)  NULL DEFAULT NULL COMMENT '是否上报',
	report_people varchar(255) NULL DEFAULT NULL COMMENT '上报人员',
	remark varchar(255) NULL DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (id)
);

DELETE FROM event;

INSERT INTO event (id, askid, name, sex, identity, location, machine_number, success, pause, handle, username,
handle_result, handle_people, report, report_people, remark) VALUES
('1', '1', '求助者', '男', '110111111111111', '售票厅东南角', 'SPT50', '1', '0', '1','zhangsan',
'此处有乘客吸烟导致行李失火。请速去现场解决，并与中控室保持联系，感谢配合', 'zhangsan,lisi', '1', 'zhangsan,lisi', null)