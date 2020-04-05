
DROP TABLE IF EXISTS machinezone;

CREATE TABLE machinezone
(
	id INT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	zone_name VARCHAR(30) NULL DEFAULT NULL COMMENT '求助位置名称',
	machine_number_list VARCHAR(30) NULL DEFAULT NULL COMMENT '对应机器编码list',
	PRIMARY KEY (id)
);

DELETE FROM machinezone;

INSERT INTO machinezone (id, zone_name, machine_number_list) VALUES
(null, '售票厅东南角', 'SPT04,SPT05,SPT06,SPT07,SPT08,SPT09'),
(null, '售票厅西北角', 'SPT01,SPT10,SPT11,SPT12,SPT13,SPT14'),
(null, '售票厅西南角', 'SPT01,SPT02,SPT03,SPT12,SPT13,SPT14')