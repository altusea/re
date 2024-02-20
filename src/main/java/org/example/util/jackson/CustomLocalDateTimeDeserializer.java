package org.example.util.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CustomLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

    public CustomLocalDateTimeDeserializer() {
        super(LocalDateTimeDeserializer.INSTANCE, null);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String text = parser.getText();
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        if (text.length() == 13) {
            // 时间戳
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(text)), ZoneId.systemDefault());
        } else if (text.length() == DatePattern.NORM_DATE_PATTERN.length()) {
            // "yyyy-MM-dd"
            return LocalDate.parse(text, DatePattern.NORM_DATE_FORMATTER).atStartOfDay();
        } else if (text.indexOf('T') >= 0) {
            return super.deserialize(parser, context);
        } else if (text.length() == DatePattern.NORM_DATETIME_PATTERN.length()) {
            return LocalDateTime.parse(text, DatePattern.NORM_DATETIME_FORMATTER);
        }
        return super.deserialize(parser, context);
    }
}
