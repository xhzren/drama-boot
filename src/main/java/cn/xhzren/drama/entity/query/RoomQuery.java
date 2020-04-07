package cn.xhzren.drama.entity.query;

import cn.xhzren.drama.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class RoomQuery extends BaseEntity {

    private String title;
    private List<ItemEntity> tags;
    private String classify;
}
