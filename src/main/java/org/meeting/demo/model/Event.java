package org.meeting.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Event {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 求助ID
     */
    private String askid;

    /**
     * 求助者姓名
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
     * 求助地理方位
     */
    private String location;

    /**
     * 求助机器编码
     */
    @Column(name = "machine_number")
    private String machineNumber;

    /**
     * 是否接通
     */
    private String success;

    /**
     * 是否暂停
     */
    private String pause;

    /**
     * 是否处理
     */
    private String handle;

    /**
     * 处理人员
     */
    private String username;

    /**
     * 处理结果
     */
    @Column(name = "handle_result")
    private String handleResult;

    /**
     * 任务派发人员
     */
    @Column(name = "handle_people")
    private String handlePeople;

    /**
     * 是否上报
     */
    private String report;

    /**
     * 上报人员
     */
    @Column(name = "report_people")
    private String reportPeople;

    /**
     * 备注
     */
    private String remark;

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
     * 获取求助ID
     *
     * @return askid - 求助ID
     */
    public String getAskid() {
        return askid;
    }

    /**
     * 设置求助ID
     *
     * @param askid 求助ID
     */
    public void setAskid(String askid) {
        this.askid = askid;
    }

    /**
     * 获取求助者姓名
     *
     * @return name - 求助者姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置求助者姓名
     *
     * @param name 求助者姓名
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
     * 获取求助地理方位
     *
     * @return location - 求助地理方位
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置求助地理方位
     *
     * @param location 求助地理方位
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取求助机器编码
     *
     * @return machine_number - 求助机器编码
     */
    public String getMachineNumber() {
        return machineNumber;
    }

    /**
     * 设置求助机器编码
     *
     * @param machineNumber 求助机器编码
     */
    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    /**
     * 获取是否接通
     *
     * @return success - 是否接通
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 设置是否接通
     *
     * @param success 是否接通
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 获取是否暂停
     *
     * @return pause - 是否暂停
     */
    public String getPause() {
        return pause;
    }

    /**
     * 设置是否暂停
     *
     * @param pause 是否暂停
     */
    public void setPause(String pause) {
        this.pause = pause;
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

    /**
     * 获取处理人员
     *
     * @return username - 处理人员
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置处理人员
     *
     * @param username 处理人员
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取处理结果
     *
     * @return handle_result - 处理结果
     */
    public String getHandleResult() {
        return handleResult;
    }

    /**
     * 设置处理结果
     *
     * @param handleResult 处理结果
     */
    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    /**
     * 获取任务派发人员
     *
     * @return handle_people - 任务派发人员
     */
    public String getHandlePeople() {
        return handlePeople;
    }

    /**
     * 设置任务派发人员
     *
     * @param handlePeople 任务派发人员
     */
    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    /**
     * 获取是否上报
     *
     * @return report - 是否上报
     */
    public String getReport() {
        return report;
    }

    /**
     * 设置是否上报
     *
     * @param report 是否上报
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * 获取上报人员
     *
     * @return report_people - 上报人员
     */
    public String getReportPeople() {
        return reportPeople;
    }

    /**
     * 设置上报人员
     *
     * @param reportPeople 上报人员
     */
    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}