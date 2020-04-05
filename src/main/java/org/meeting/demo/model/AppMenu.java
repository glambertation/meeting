package org.meeting.demo.model;

import javax.persistence.*;

@Table(name = "app_menu")
public class AppMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    @Column(name = "iconCls")
    private String iconcls;

    @Column(name = "keepAlive")
    private Boolean keepalive;

    @Column(name = "requireAuth")
    private Boolean requireauth;

    @Column(name = "parentId")
    private Integer parentid;

    private Boolean enabled;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return iconCls
     */
    public String getIconcls() {
        return iconcls;
    }

    /**
     * @param iconcls
     */
    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    /**
     * @return keepAlive
     */
    public Boolean getKeepalive() {
        return keepalive;
    }

    /**
     * @param keepalive
     */
    public void setKeepalive(Boolean keepalive) {
        this.keepalive = keepalive;
    }

    /**
     * @return requireAuth
     */
    public Boolean getRequireauth() {
        return requireauth;
    }

    /**
     * @param requireauth
     */
    public void setRequireauth(Boolean requireauth) {
        this.requireauth = requireauth;
    }

    /**
     * @return parentId
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}