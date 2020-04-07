package cn.xhzren.drama.entity.query;

import cn.xhzren.drama.entity.BaseEntity;
import cn.xhzren.drama.entity.query.enums.ContentType;
import cn.xhzren.drama.entity.query.enums.MessageType;
import lombok.Data;

@Data
public class MessageQuery extends BaseEntity {

    private MessageType messageType;
    private ContentType contentType;

    private String sourceId;
    private String sendUser;
    private String receiveUser;

    private String sendStartTime;
    private String sendEndTime;
    private String receiveStartTime;
    private String receiveEndTime;

    private String sendStatus;
    private String receiveStatus;
}
