package org.example.util;

public class TestResult<T> {

    private int code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public TestResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static <T> TestResult<T> handleResult(int code) {
        if (code == 0) {
            return success();
        }
        return setExtraMsg(error(), "extraMsg");
    }

    private static <T> TestResult<T> success() {
        return new TestResult<>();
    }

    private static <T> TestResult<T> error() {
        return new TestResult<>();
    }

    public static <T> TestResult<T> setExtraMsg(TestResult<T> result, String extraMsg) {
        return result.setMsg(result.getMsg() + extraMsg);
    }
}
