package cn.xhzren.drama.entity.query.enums;

public enum ContentType {

    UNDEFINDER(0), TEXT(1), PIC(2), MUSIC(3), VIDEO(4);

    private int value;

        ContentType(int value) {
        this.value = value;
    }

    public static ContentType valueOf(int value) {
        if(value == TEXT.value) {
            return TEXT;
        }else if(value == PIC.value) {
            return PIC;
        }
        return UNDEFINDER;
    }
}
