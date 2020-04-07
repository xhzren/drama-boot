package cn.xhzren.drama.entity;

import lombok.Data;

@Data
public class Room extends BaseEntity {

    private String plotId;
    private String host;
    private int capacity;
}
