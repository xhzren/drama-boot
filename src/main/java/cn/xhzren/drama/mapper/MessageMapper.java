package cn.xhzren.drama.mapper;

import cn.xhzren.drama.entity.MessageEntity;
import cn.xhzren.drama.entity.query.MessageQuery;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MessageMapper {

   int addMessageHis(MessageEntity message);

   List<MessageEntity> getMessageList(MessageQuery query);
}
