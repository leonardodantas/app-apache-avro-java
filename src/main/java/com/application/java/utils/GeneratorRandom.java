package com.application.java.utils;

import com.application.java.domains.Categories;
import com.application.java.domains.Product;
import com.application.java.domains.Promotion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneratorRandom {

    public static Product getProduct() {
        log.info("Generate Product with random values");
        return Product.builder(
                        string(), code(), categories(), bigDecimal())
                .id(code())
                .promotion(Promotion.of(string(), bigDecimal(), new ArrayList<>()))
                .build();
    }

    private static String string() {
        return RandomStringUtils.random(20, true, false);
    }

    private static Categories categories() {
        final var values = new ArrayList<String>();
        for (var i = 0; i < 5; i++) {
            values.add(RandomStringUtils.random(5, true, false));
        }
        return Categories.from(values);
    }

    private static String code() {
        return UUID.randomUUID().toString();
    }

    private static BigDecimal bigDecimal() {
        final var random = new Random();
        int numberRandom = random.nextInt();
        return BigDecimal.valueOf(numberRandom);
    }
}
