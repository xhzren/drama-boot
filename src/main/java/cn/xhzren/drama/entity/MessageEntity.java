package cn.xhzren.drama.entity;

import cn.xhzren.drama.entity.query.enums.ContentType;
import cn.xhzren.drama.entity.query.enums.MessageType;
import lombok.Data;

@Data
public class MessageEntity extends BaseEntity {

    private Integer messageType;
    private Integer contentType;

    private String sourceId;
    private String sendUser;
    private String receiveUser;

    private String sendTime;
    private String receiveTime;

    private String sendStatus;
    private String receiveStatus;

    private String content;

}
