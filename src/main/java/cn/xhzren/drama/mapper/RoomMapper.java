package cn.xhzren.drama.mapper;

import cn.xhzren.drama.entity.Plot;
import cn.xhzren.drama.entity.Room;
import cn.xhzren.drama.entity.query.RoomQuery;
import cn.xhzren.drama.entity.result.RoomResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper {

    int addRoom(Room room);

    List<RoomResult> getRooms(RoomQuery query);

    List<Room> getOnlineRooms(@Param("creator") String creator);

    Room detailRoom(@Param("id")String id);
}
