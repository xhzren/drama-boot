package cn.xhzren.drama.api;

import cn.xhzren.drama.entity.Plot;
import cn.xhzren.drama.mapper.PlotMapper;
import cn.xhzren.drama.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plot")
public class PlotApi {

    @Autowired
    PlotMapper plotMapper;

    @RequestMapping(value = "/addPlot", method = RequestMethod.POST)
    public String addPlot(@RequestBody Plot plot) {
        plotMapper.addPlot(plot);
        return Result.ok(null);
    }

    @RequestMapping(value = "/getPlotList", method = RequestMethod.POST)
    public String getPlotList(@RequestBody Plot plot,
                                      @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List data = plotMapper.getPlotList(plot);
        return Result.ok(new PageInfo<Plot>(data));
    }
}
