package com.application.java.utils;

import com.application.java.domains.Categories;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorRandom {

    public static String string() {
        return RandomStringUtils.random(20, true, false);
    }

    public static Categories categories() {
        final var values = new ArrayList<String>();
        for (var i = 0; i < 5; i++) {
            values.add(RandomStringUtils.random(5, true, false));
        }
        return Categories.from(values);
    }

    public static String code(){
        return UUID.randomUUID().toString();
    }

    public static BigDecimal bigDecimal(){
        final var random = new Random();
        int numberRandom = random.nextInt();
        return BigDecimal.valueOf(numberRandom);
    }
}
