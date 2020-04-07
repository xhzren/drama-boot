package cn.xhzren.drama.entity.query.enums;

import cn.xhzren.drama.entity.Room;

public enum MessageType {

    UNDEFINDER(0),ROOM(1), CHAT(2);

    private int value;

    MessageType(int value) {
        this.value = value;
    }


    public static MessageType valueOf(int value) {
        if(value == ROOM.value) {
            return ROOM;
        }else if(value == CHAT.value) {
            return CHAT;
        }
        return UNDEFINDER;
    }
}
