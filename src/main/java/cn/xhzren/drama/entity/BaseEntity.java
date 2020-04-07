package cn.xhzren.drama.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private String id;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String status;
}
