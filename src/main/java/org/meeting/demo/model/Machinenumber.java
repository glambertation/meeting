package org.meeting.demo.model;

import javax.persistence.*;

public class Machinenumber {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 求助机器编码
     */
    @Column(name = "machine_number")
    private String machineNumber;

    /**
     * 对应机器编码的url
     */
    @Column(name = "machine_number_url")
    private String machineNumberUrl;

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
     * 获取对应机器编码的url
     *
     * @return machine_number_url - 对应机器编码的url
     */
    public String getMachineNumberUrl() {
        return machineNumberUrl;
    }

    /**
     * 设置对应机器编码的url
     *
     * @param machineNumberUrl 对应机器编码的url
     */
    public void setMachineNumberUrl(String machineNumberUrl) {
        this.machineNumberUrl = machineNumberUrl;
    }
}