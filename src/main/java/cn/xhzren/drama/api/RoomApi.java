package cn.xhzren.drama.api;

import cn.xhzren.drama.entity.Plot;
import cn.xhzren.drama.entity.Room;
import cn.xhzren.drama.entity.query.RoomQuery;
import cn.xhzren.drama.entity.result.RoomResult;
import cn.xhzren.drama.mapper.RoomMapper;
import cn.xhzren.drama.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomApi {

    @Autowired
    RoomMapper roomMapper;

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String addRoom(@RequestBody Room room) {
        return Result.ok(roomMapper.addRoom(room));
    }

    @RequestMapping(value = "/getOnlineRooms", method = RequestMethod.GET)
    public String getOnlineRooms(@RequestParam String creator,
                                 @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List data = roomMapper.getOnlineRooms(creator);
        return Result.ok(new PageInfo<Room>(data));
    }
    @RequestMapping(value = "/getRooms", method = RequestMethod.POST)
    public String getRooms(@RequestBody RoomQuery query,
                                 @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List data = roomMapper.getRooms(query);
        return Result.ok(new PageInfo<RoomResult>(data));
    }
    @RequestMapping(value = "/detailRoom", method = RequestMethod.GET)
    public String detailRoom(@RequestParam String id) {
        Room data = roomMapper.detailRoom(id);
        return Result.ok(data);
    }
}
