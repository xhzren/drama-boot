package cn.xhzren.drama.conf;

import cn.xhzren.drama.utils.Result;
import cn.xhzren.drama.utils.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public String exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return Result.error(StatusCodeEnum.INTERNAL_ERROR);
    }

    @ExceptionHandler(value =Exception.class)
    public String exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("发生未知异常！原因是:",e);
        return Result.error(StatusCodeEnum.INTERNAL_ERROR);
    }
}
