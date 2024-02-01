package org.example.playground;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
public class TimeHolder {

    private YearMonth yearMonth;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

}
