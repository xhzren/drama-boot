package cn.xhzren.drama.utils;

/**
 * 返回的状态码与默认消息枚举类
 *
 * @author CasYang
 * @version 1.0.0
 * @since 1.0.0
 * <br> <b>MODIFICATION HISTORY</b> <br>
 * (Rev.) ---- (Date) ---- (Name) -------- (Comment) <br>
 * 1.0.0 - 2017/11/7 - CasYang - New making <br>
 */
public enum StatusCodeEnum {
  /**
   * 请求处理失败
   */
  FAILED(0,"请求处理失败","Request Failed."),
  /**
   * 请求处理成功
   */
  SUCCESS(200, "请求处理成功", "Request Success."),
  /**
   * 请求出错
   */
  BUSINESS_ERROR(400, "请求出错，请重试", "Request failed, retry please."),
  /**
   * 没有更多数据
   */
  DATA_NOT_FOUND(404, "没有更多数据", "No Data Found."),
  /**
   * 服务器出问题
   */
  INTERNAL_ERROR(500, "服务器出问题了，请稍后重试", "Something on server went wrong, please retry later."),
  /**
   * 请求的资源不存在
   */
  RES_NOT_FOUND(5004, "您请求的资源不存在", "Sorry, resource you need is not exist."),
  /**
   * 当前用户未认证
   */
  NOT_AUTHENTICATED(110, "当前用户未认证", "subject has not yet authenticated !"),

  VERIFY_CODE_ERROR(120, "验证码错误， 请重试", "subject has not yet authenticated !"),
  VERIFY_CODE_NOT_FOUND(121, "验证码无效，请重新获取", "subject has not yet authenticated !"),

  ACCOUNT_NOT_FOUND(130, "账号不存在，请先注册", "subject has not yet authenticated !");

  private int code;
  private String msgCN;
  private String msgEN;

  private StatusCodeEnum(int code, String msgCN, String msgEN) {
    this.code = code;
    this.msgCN = msgCN;
    this.msgEN = msgEN;
  }

  public int getCode() {
    return code;
  }

  public String getMsgCN() {
    return msgCN;
  }

  public String getMsgEN() {
    return msgEN;
  }

  public StatusCodeEnum getByKey(int code) {
    for (StatusCodeEnum c : StatusCodeEnum.values()) {
      if (c.code == code) {
        return c;
      }
    }
    return null;
  }


}
