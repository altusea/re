package org.example.playground.a;

import java.io.Serial;
import java.io.Serializable;

public class BaseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
