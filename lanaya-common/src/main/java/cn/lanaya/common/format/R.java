package cn.lanaya.common.format;

public class R<T> {

    private int code;

    private String msg;

    private T data;

    public R(MessageEnum m, T data){
        this.data = data;
        if(m != null){
            this.code = m.getCode();
            this.msg = m.getMsg();
        }
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
