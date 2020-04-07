package cn.xhzren.drama.mapper;

import cn.xhzren.drama.entity.Classify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassifyMapper {

    void addClassify(Classify classify);

    List<Classify> getClassifyList(@Param("id") String id);
}
