package cn.xhzren.drama.mapper;

import cn.xhzren.drama.entity.account.AccountInfo;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

//    AccountInfo verityAccount(@Param("account") String account, @Param("passWorld") String passWorld);

    void registered(AccountInfo info);

    AccountInfo getAccountInfo(AccountInfo account);
}
