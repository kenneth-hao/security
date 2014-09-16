package org.study.security.entity;

import org.study.security.base.BaseEntity;

import java.util.Date;

/**
 * Created by haoyuewen on 9/16/14.
 */
public class Role extends BaseEntity {

    /**
     * 状态 - 启用
     */
    public static final Short C_STATE_ON = 1;

    /**
     * 状态 - 停用
     */
    public static final Short C_STATE_OFF = 0;

    /**
     * 状态 - 逻辑删除
     */
    public static final Short C_STATE_DEL = 9;

    // <<< Field START >>>

    private Integer id;

    private String name;

    private String key;

    private String description;
    // 角色状态
    private Short state;

    private Date createTime;

    private Date updateTime;

    // <<< Field END >>>


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
