package cn.xhzren.drama.entity.account;

import cn.xhzren.drama.entity.BaseEntity;
import lombok.Data;

@Data
public class AccountInfo extends BaseEntity {

    private String nickName;
    private String account;
    private String passWorld;
    private String avatar;
    private String phone;
    private String accountQQ;
    private String accountWeiXin;
    private String sex;
    private String invitation;
    private String device;
}
