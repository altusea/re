package org.example.playground;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataHolder {

    private String field;

    private InnerClazz innerClazz;

    @Setter
    @Getter
    @ToString
    public static class InnerClazz {

        private String fieldA;

        private String fieldB;

    }

}
