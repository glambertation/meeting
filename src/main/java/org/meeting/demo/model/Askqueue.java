package org.meeting.demo.model;

import javax.persistence.*;

public class Askqueue {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 求助id
     */
    @Column(name = "ask_id")
    private String askId;

    /**
     * 对应url视频
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 求助时间
     */
    private String time;

    /**
     * 是否接通  1接通 0未接通
     */
    private String success;

    /**
     * 是否暂停 1暂停 0未暂停
     */
    private String pause;

    /**
     * 停止时间
     */
    @Column(name = "pause_time")
    private String pauseTime;

    /**
     * 求助位置机器编号
     */
    @Column(name = "machine_number")
    private String machineNumber;

    /**
     * 求助位置名称区域
     */
    @Column(name = "machine_zone")
    private String machineZone;

    /**
     * 是否处理
     */
    private String handle;

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
     * 获取求助id
     *
     * @return ask_id - 求助id
     */
    public String getAskId() {
        return askId;
    }

    /**
     * 设置求助id
     *
     * @param askId 求助id
     */
    public void setAskId(String askId) {
        this.askId = askId;
    }

    /**
     * 获取对应url视频
     *
     * @return video_url - 对应url视频
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置对应url视频
     *
     * @param videoUrl 对应url视频
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取求助时间
     *
     * @return time - 求助时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置求助时间
     *
     * @param time 求助时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取是否接通  1接通 0未接通
     *
     * @return success - 是否接通  1接通 0未接通
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 设置是否接通  1接通 0未接通
     *
     * @param success 是否接通  1接通 0未接通
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 获取是否暂停 1暂停 0未暂停
     *
     * @return pause - 是否暂停 1暂停 0未暂停
     */
    public String getPause() {
        return pause;
    }

    /**
     * 设置是否暂停 1暂停 0未暂停
     *
     * @param pause 是否暂停 1暂停 0未暂停
     */
    public void setPause(String pause) {
        this.pause = pause;
    }

    /**
     * 获取停止时间
     *
     * @return pause_time - 停止时间
     */
    public String getPauseTime() {
        return pauseTime;
    }

    /**
     * 设置停止时间
     *
     * @param pauseTime 停止时间
     */
    public void setPauseTime(String pauseTime) {
        this.pauseTime = pauseTime;
    }

    /**
     * 获取求助位置机器编号
     *
     * @return machine_number - 求助位置机器编号
     */
    public String getMachineNumber() {
        return machineNumber;
    }

    /**
     * 设置求助位置机器编号
     *
     * @param machineNumber 求助位置机器编号
     */
    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    /**
     * 获取求助位置名称区域
     *
     * @return machine_zone - 求助位置名称区域
     */
    public String getMachineZone() {
        return machineZone;
    }

    /**
     * 设置求助位置名称区域
     *
     * @param machineZone 求助位置名称区域
     */
    public void setMachineZone(String machineZone) {
        this.machineZone = machineZone;
    }

    /**
     * 获取是否处理
     *
     * @return handle - 是否处理
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 设置是否处理
     *
     * @param handle 是否处理
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }
}