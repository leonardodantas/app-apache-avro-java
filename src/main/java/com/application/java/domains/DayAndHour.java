package com.application.java.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public final class DayAndHour implements Serializable {

    private Days day;
    private LocalTime startTime;
    private LocalTime endTime;

    private DayAndHour(final Days day, final LocalTime startTime, final LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static DayAndHour of(final Days day, final LocalTime startTime, final LocalTime endTime) {
        return new DayAndHour(day, startTime, endTime);
    }

}
