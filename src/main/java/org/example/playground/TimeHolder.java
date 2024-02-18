package org.example.playground;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;

@Data
public class TimeHolder {

    private Date date;

    private YearMonth yearMonth;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

}
