package cn.xhzren.drama.entity.result;

import cn.xhzren.drama.entity.BaseEntity;
import lombok.Data;

@Data
public class RoomResult extends BaseEntity {

    private String title;
    private String desc;
    private String classify;
}
