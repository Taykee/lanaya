package cn.lanaya.common.web;

import cn.lanaya.common.format.MessageEnum;
import cn.lanaya.common.format.R;

public abstract class AbstractController {

    protected <T> R<T> success(MessageEnum m, T data){
        return new R<T>(m, data);
    }

    protected <T> R<T> fail(MessageEnum m, T data){
        return new R<T>(m, data);
    }

    protected <T> R<T> success(int code, String msg, T data){
        return new R<T>(code, msg, data);
    }

    protected R<Object> doProcess(Action act){
        return success(MessageEnum.SUCCESS, act.doAction());
    }

    public interface Action{
        int doAction();
    }
}
