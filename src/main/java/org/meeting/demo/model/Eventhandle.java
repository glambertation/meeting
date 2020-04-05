package org.meeting.demo.model;

import javax.persistence.*;

public class Eventhandle {
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
     * 事件处理id
     */
    @Column(name = "event_handle_id")
    private String eventHandleId;

    /**
     * 是否处理 1处理 0未处理
     */
    private String handle;

    /**
     * 处理结果
     */
    @Column(name = "handle_result")
    private String handleResult;

    /**
     * 处理人员list
     */
    @Column(name = "handle_person")
    private String handlePerson;

    /**
     * 是否上报
     */
    private String report;

    /**
     * 上报结果
     */
    @Column(name = "report_result")
    private String reportResult;

    /**
     * 上报人员list
     */
    @Column(name = "report_person")
    private String reportPerson;

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
     * 获取事件处理id
     *
     * @return event_handle_id - 事件处理id
     */
    public String getEventHandleId() {
        return eventHandleId;
    }

    /**
     * 设置事件处理id
     *
     * @param eventHandleId 事件处理id
     */
    public void setEventHandleId(String eventHandleId) {
        this.eventHandleId = eventHandleId;
    }

    /**
     * 获取是否处理 1处理 0未处理
     *
     * @return handle - 是否处理 1处理 0未处理
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 设置是否处理 1处理 0未处理
     *
     * @param handle 是否处理 1处理 0未处理
     */
    public void setHandle(String handle) {
        this.handle = handle;
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
     * 获取处理人员list
     *
     * @return handle_person - 处理人员list
     */
    public String getHandlePerson() {
        return handlePerson;
    }

    /**
     * 设置处理人员list
     *
     * @param handlePerson 处理人员list
     */
    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
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
     * 获取上报结果
     *
     * @return report_result - 上报结果
     */
    public String getReportResult() {
        return reportResult;
    }

    /**
     * 设置上报结果
     *
     * @param reportResult 上报结果
     */
    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    /**
     * 获取上报人员list
     *
     * @return report_person - 上报人员list
     */
    public String getReportPerson() {
        return reportPerson;
    }

    /**
     * 设置上报人员list
     *
     * @param reportPerson 上报人员list
     */
    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson;
    }
}