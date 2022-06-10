package fun.hyman4hu.hmchat.common;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 请求响应结构体
 * 
 * @author Hyman
 * @date 2022/03/03
 */
@Data
@ToString
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    // ----------------------- 正常返回（成功失败） ------------------------------

    public static <T> R<T> ok() {
        return restResult(null, CommonConsts.SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, CommonConsts.SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, CommonConsts.SUCCESS, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, CommonConsts.FAIL, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, CommonConsts.FAIL, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, CommonConsts.FAIL, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, CommonConsts.FAIL, msg);
    }

    public ResponseEntity<R<T>> res() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

}
