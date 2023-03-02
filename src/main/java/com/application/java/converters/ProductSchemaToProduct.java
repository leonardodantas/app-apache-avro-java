package com.application.java.converters;

import com.application.java.domains.Categories;
import com.application.java.domains.Product;
import com.application.java.domains.Promotion;
import com.application.java.kafka.schemas.CategoriesSchema;
import com.application.java.kafka.schemas.ProductSchema;
import com.application.java.kafka.schemas.PromotionSchema;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class ProductSchemaToProduct implements Converter<ProductSchema, Product> {

    @Override
    public Product convert(final ProductSchema productSchema) {
        return Product.builder(productSchema.getName().toString(), productSchema.getName().toString(), getCategories(productSchema.getCategories()), BigDecimal.valueOf(productSchema.getPrice().get())).id(productSchema.getId().toString()).promotion(getPromotion(productSchema.getPromotion())).build();
    }

    private Promotion getPromotion(final PromotionSchema promotion) {
        return Promotion.of(promotion.getDescription().toString(), BigDecimal.valueOf(promotion.getPromotionalPrice().get()), new ArrayList<>());
    }

    private Categories getCategories(final CategoriesSchema categories) {
        final var values = categories.getValues().stream().map(CharSequence::toString).toList();
        return Categories.from(values);
    }
}
