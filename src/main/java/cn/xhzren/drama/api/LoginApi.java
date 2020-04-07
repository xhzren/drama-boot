package cn.xhzren.drama.api;

import cn.xhzren.drama.entity.account.AccountInfo;
import cn.xhzren.drama.mapper.AccountMapper;
import cn.xhzren.drama.utils.Result;
import cn.xhzren.drama.utils.StatusCodeEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class LoginApi {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    AccountMapper accountMapper;

    public void login(@RequestBody AccountInfo accountInfo) {

    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String registered(@RequestBody AccountInfo accountInfo,
                             @RequestHeader String verifyCode) {
        String redisCode = ((String)redisTemplate.opsForValue().
                get(accountInfo.getAccount()+"-"+verifyCode));
        if(redisCode != null && redisCode.equals(verifyCode)) {
            //add
            String uuid = UUID.randomUUID().toString();
            accountInfo.setId(uuid);
            accountMapper.registered(accountInfo);
            return Result.ok(uuid, "注册成功");
        }else {
            return Result.error(null, "验证码已失效, 请重新获取");
        }
    }

    @RequestMapping(value = "/accountLogin", method = RequestMethod.POST)
    public String accountLogin(@RequestBody AccountInfo accountInfo,
                               @RequestHeader String deviceType,
                               @RequestHeader String verifyCode,
                               @RequestHeader String codeType) {
        String code = ((String)redisTemplate.opsForValue()
        .get(accountInfo.getAccount()+"-"+ codeType));
        if(code != null) {
            if(code.equals(verifyCode)) {
                //查询info
                AccountInfo account = accountMapper.getAccountInfo(accountInfo);
                if(account != null) {
                    //TODO check device
                    //TODO is online
//                    if(account.getDevice().equals(deviceType)) {
                        //old unOnline
                        //update online
//                    }
                    String token = UUID.randomUUID().toString();
                    JSONObject info = new JSONObject();
                    info.put("accountInfo", account);
                    info.put("token", token);
                    redisTemplate.opsForValue().set(account.getAccount(), JSONObject.toJSONString(info));
                    return Result.ok(info);
                }else {
                    return Result.res(StatusCodeEnum.ACCOUNT_NOT_FOUND);
                }
            }else {
                return Result.res(StatusCodeEnum.VERIFY_CODE_ERROR);
            }
        }
        return Result.res(StatusCodeEnum.VERIFY_CODE_NOT_FOUND);
    }

    /**
     *
     * @param verity account：账号
     *              code_type：验证码类型
     * @return
     */
    @RequestMapping(value = "/tmpVerifyCode", method = RequestMethod.POST)
    public String tmpVerifyCode(@RequestBody JSONObject verity) {
        String res = verity.getString("account");
        redisTemplate.opsForValue().set(res+"-"+verity.getString("codeType"), res.substring(0, 4), Duration.ofSeconds(60));
        return Result.ok(res, "验证码发送成功");
    }
}
