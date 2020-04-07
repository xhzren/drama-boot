package cn.xhzren.drama.entity;

import lombok.Data;

@Data
public class Plot extends BaseEntity {

    private String title;
    private String desc;
    private String cover;
    private String tag;
    private String classify;
}
