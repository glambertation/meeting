
DROP TABLE IF EXISTS eventhandle;

CREATE TABLE eventhandle
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	ask_id VARCHAR(300) NULL DEFAULT NULL COMMENT '求助id',
	event_hadle_id VARCHAR(300) NULL DEFAULT NULL COMMENT '事件处理id',
	handle VARCHAR(30) NULL DEFAULT NULL COMMENT '是否处理 1处理 0未处理',
	handle_result VARCHAR(300) NULL DEFAULT NULL COMMENT '处理结果',
	handle_person VARCHAR(300) NULL DEFAULT NULL COMMENT '处理人员list',
	report VARCHAR(50) NULL DEFAULT NULL COMMENT '是否上报',
	report_result VARCHAR(500) NULL DEFAULT NULL COMMENT '上报结果',
	report_person VARCHAR(500) NULL DEFAULT NULL COMMENT '上报人员list',
	PRIMARY KEY (id)
);

DELETE FROM eventhandle;

INSERT INTO eventhandle (id, ask_id, event_hadle_id, handle, handle_result, handle_person, report, report_result, report_person) VALUES
(null, '1', '1', '1', '您好：张三、李四，在售票厅东南角，设备编号：SPT03紧急求助。求助人：王五，男，身份证号：130731199205055555
事件描述：此处有乘客吸烟导致行李失火。请速去现场解决，并与中控室保持联系，感谢配合！', '张三,李四','1','立刻处理','张三,李四,王五'),
(null, '2', '2', '0', null, null,'1','立刻处理','张三,李四,王五'),
(null, '3', '3', '0', null, null, null, null, null)