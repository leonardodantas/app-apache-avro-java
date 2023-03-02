package com.application.java.converters;

import com.application.java.domains.Categories;
import com.application.java.domains.DayAndHour;
import com.application.java.domains.Product;
import com.application.java.domains.Promotion;
import com.application.java.kafka.schemas.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ProductToProductSchema implements Converter<Product, ProductSchema> {
    @Override
    public ProductSchema convert(final Product product) {
        return ProductSchema.newBuilder()
                .setId(product.getId())
                .setCode(product.getCode())
                .setName(product.getName())
                .setPrice(convertBigDecimalToByteBuffer(product.getPrice()))
                .setCategories(getCategoriesSchema(product.getCategories()))
                .setPromotionActive(product.isPromotionActive())
                .setPromotion(getPromotionSchema(product.getPromotion()))
                .build();
    }

    private PromotionSchema getPromotionSchema(final Promotion promotion) {
        return PromotionSchema.newBuilder()
                .setDescription(promotion.getDescription())
                .setPromotionalPrice(convertBigDecimalToByteBuffer(promotion.getPromotionalPrice()))
                .setDayAndHours(getDayAndHoursSchema(promotion.getDayAndHours()))
                .build();
    }

    private List<DayAndHoursSchema> getDayAndHoursSchema(final Collection<DayAndHour> dayAndHours) {
        return dayAndHours.stream().map(dH -> DayAndHoursSchema.newBuilder()
                .setDays(new DaysSchema(dH.getDay().name()))
                .setStartTime(dH.getStartTime().toNanoOfDay())
                .setEndTime(dH.getEndTime().toNanoOfDay())
                .build()).toList();
    }

    private static ByteBuffer convertBigDecimalToByteBuffer(final BigDecimal value) {
        return ByteBuffer.wrap(value.unscaledValue().toByteArray());
    }

    private CategoriesSchema getCategoriesSchema(final Categories categories) {
        return CategoriesSchema.newBuilder()
                .setValues(new ArrayList<>(categories.getValues()))
                .build();
    }
}
