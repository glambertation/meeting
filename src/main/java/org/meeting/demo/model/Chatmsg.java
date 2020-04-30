package org.meeting.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Chatmsg {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发信人
     */
    private String fromid;

    /**
     * 收信人
     */
    private String toid;

    /**
     * 内容
     */
    private String content;

    /**
     * 日期
     */
    private String date;

    /**
     * 类型
     */
    private String type;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发信人
     *
     * @return fromid - 发信人
     */
    public String getFromid() {
        return fromid;
    }

    /**
     * 设置发信人
     *
     * @param fromid 发信人
     */
    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    /**
     * 获取收信人
     *
     * @return toid - 收信人
     */
    public String getToid() {
        return toid;
    }

    /**
     * 设置收信人
     *
     * @param toid 收信人
     */
    public void setToid(String toid) {
        this.toid = toid;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取日期
     *
     * @return date - 日期
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置日期
     *
     * @param date 日期
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }
}