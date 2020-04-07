package cn.xhzren.drama.mapper;

import cn.xhzren.drama.entity.Plot;

import java.util.List;

public interface PlotMapper {

    void addPlot(Plot plot);

    List<Plot> getPlotList(Plot plot);
}
