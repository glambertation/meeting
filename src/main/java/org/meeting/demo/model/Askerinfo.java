package org.meeting.demo.model;

import javax.persistence.*;

public class Askerinfo {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ask_ID
     */
    @Column(name = "ask_id")
    private String askId;

    /**
     * 求助位置名称
     */
    private String location;

    /**
     * 求助设备编号
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 求助者url
     */
    @Column(name = "video_url")
    private String videoUrl;

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
     * 获取ask_ID
     *
     * @return ask_id - ask_ID
     */
    public String getAskId() {
        return askId;
    }

    /**
     * 设置ask_ID
     *
     * @param askId ask_ID
     */
    public void setAskId(String askId) {
        this.askId = askId;
    }

    /**
     * 获取求助位置名称
     *
     * @return location - 求助位置名称
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置求助位置名称
     *
     * @param location 求助位置名称
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取求助设备编号
     *
     * @return machine_id - 求助设备编号
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置求助设备编号
     *
     * @param machineId 求助设备编号
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取身份证号
     *
     * @return identity - 身份证号
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置身份证号
     *
     * @param identity 身份证号
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取求助者url
     *
     * @return video_url - 求助者url
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置求助者url
     *
     * @param videoUrl 求助者url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}