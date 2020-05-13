package org.meeting.demo.model;

import javax.persistence.*;

public class Room {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发信人
     */
    private String sender;

    /**
     * 邀请列表
     */
    private String invite;

    /**
     * 日期
     */
    private String date;

    /**
     * 会议室id
     */
    private String roomtoken;

    /**
     * 会议室id
     */
    private String joiner;

    /**
     * 发信
     */
    private String fromid;

    /**
     * 职级
     */
    private String role;

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
     * @return sender - 发信人
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置发信人
     *
     * @param sender 发信人
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取邀请列表
     *
     * @return invite - 邀请列表
     */
    public String getInvite() {
        return invite;
    }

    /**
     * 设置邀请列表
     *
     * @param invite 邀请列表
     */
    public void setInvite(String invite) {
        this.invite = invite;
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
     * 获取会议室id
     *
     * @return roomtoken - 会议室id
     */
    public String getRoomtoken() {
        return roomtoken;
    }

    /**
     * 设置会议室id
     *
     * @param roomtoken 会议室id
     */
    public void setRoomtoken(String roomtoken) {
        this.roomtoken = roomtoken;
    }

    /**
     * 获取会议室id
     *
     * @return joiner - 会议室id
     */
    public String getJoiner() {
        return joiner;
    }

    /**
     * 设置会议室id
     *
     * @param joiner 会议室id
     */
    public void setJoiner(String joiner) {
        this.joiner = joiner;
    }

    /**
     * 获取发信
     *
     * @return fromid - 发信
     */
    public String getFromid() {
        return fromid;
    }

    /**
     * 设置发信
     *
     * @param fromid 发信
     */
    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    /**
     * 获取职级
     *
     * @return role - 职级
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置职级
     *
     * @param role 职级
     */
    public void setRole(String role) {
        this.role = role;
    }
}