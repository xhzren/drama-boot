package cn.xhzren.drama.entity;

import lombok.Data;

@Data
public class Classify extends BaseEntity {
    private String content;
    private String parentId;
}
