package cn.xhzren.drama.api;

import cn.xhzren.drama.entity.MessageEntity;
import cn.xhzren.drama.entity.query.MessageQuery;
import cn.xhzren.drama.mapper.MessageMapper;
import cn.xhzren.drama.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageApi {

    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping(value = "/getMessageList", method = RequestMethod.POST)
    public String getMessageList(
            @RequestBody MessageQuery message,
            @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List data = messageMapper.getMessageList(message);
        return Result.ok(new PageInfo<MessageEntity>(data));
    }

    @RequestMapping(value = "addMessageHis", method = RequestMethod.POST)
    public String addMessageHis(@RequestBody MessageEntity message) {
        return Result.ok(messageMapper.addMessageHis(message));
    }
}
