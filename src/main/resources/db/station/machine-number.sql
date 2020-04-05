
DROP TABLE IF EXISTS machinenumber;

CREATE TABLE machinenumber
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	machine_number VARCHAR(30) NULL DEFAULT NULL COMMENT '求助机器编码',
	machine_number_url VARCHAR(30) NULL DEFAULT NULL COMMENT '对应机器编码的url',
	PRIMARY KEY (id)
);

DELETE FROM machinenumber;

INSERT INTO machinenumber (id, machine_number, machine_number_url) VALUES
(null, 'SPT01', 'www.baidu.com'),
(null, 'SPT02', 'www.baidu.com'),
(null, 'SPT03', 'www.baidu.com'),
(null, 'SPT04', 'www.baidu.com'),
(null, 'SPT05', 'www.baidu.com'),
(null, 'SPT06', 'www.baidu.com')
