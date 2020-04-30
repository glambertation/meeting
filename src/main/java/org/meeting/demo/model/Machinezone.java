package org.meeting.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Machinezone {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 求助位置名称
     */
    @Column(name = "zone_name")
    private String zoneName;

    /**
     * 对应机器编码list
     */
    @Column(name = "machine_number_list")
    private String machineNumberList;

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
     * 获取求助位置名称
     *
     * @return zone_name - 求助位置名称
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * 设置求助位置名称
     *
     * @param zoneName 求助位置名称
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * 获取对应机器编码list
     *
     * @return machine_number_list - 对应机器编码list
     */
    public String getMachineNumberList() {
        return machineNumberList;
    }

    /**
     * 设置对应机器编码list
     *
     * @param machineNumberList 对应机器编码list
     */
    public void setMachineNumberList(String machineNumberList) {
        this.machineNumberList = machineNumberList;
    }
}