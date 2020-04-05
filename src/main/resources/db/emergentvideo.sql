
DROP TABLE IF EXISTS video;

CREATE TABLE video
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
	time VARCHAR(30) NULL DEFAULT NULL COMMENT '求助时间',
	done VARCHAR(30) NULL DEFAULT NULL COMMENT '是否处理',
	word VARCHAR(100) NULL DEFAULT NULL COMMENT '文字介绍',
	result VARCHAR(500) NULL DEFAULT NULL COMMENT '处理结果',
	url VARCHAR(500) NULL DEFAULT NULL COMMENT '视频地址',
	type VARCHAR(500) NULL DEFAULT NULL COMMENT '求助类型',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO video (id, time, done, word, result, url, type) VALUES
(null, '202003121835', '1', '售票机不好使', '已更换新机器', 'www.baidu.com', '日常'),
(null, '202003121835', '1', '售票机不好使', '已更换新机器', 'www.baidu.com', '日常'),
(null, '202003121835', '1', '售票机不好使', '已更换新机器', 'www.baidu.com', '日常')