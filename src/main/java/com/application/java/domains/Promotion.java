package com.application.java.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@NoArgsConstructor
public final class Promotion implements Serializable {
    private String description;
    private BigDecimal promotionalPrice;
    private Collection<DayAndHour> dayAndHours;

    private Promotion(final String description, final BigDecimal promotionalPrice, final Collection<DayAndHour> dayAndHours) {
        this.description = description;
        this.promotionalPrice = promotionalPrice;
        this.dayAndHours = dayAndHours;
    }

    public static Promotion of(final String description, final BigDecimal promotionalPrice, Collection<DayAndHour> dayAndHours) {
        return new Promotion(description, promotionalPrice, dayAndHours);
    }

}
