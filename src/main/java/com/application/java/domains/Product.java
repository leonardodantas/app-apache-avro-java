package com.application.java.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public final class Product implements Serializable {

    private String id;
    private String code;
    private String name;
    private BigDecimal price;
    private Categories categories;
    private boolean promotionActive;
    private Promotion promotion;

    private Product(final String id, final String code, final String name, final BigDecimal price, final Categories categories, final boolean promotionActive, final Promotion promotion) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.promotionActive = promotionActive;
        this.promotion = promotion;
    }

    public static Builder builder(final String name, final String code, final Categories categories, final BigDecimal price) {
        return new Builder(name, code, categories, price);
    }

    public static class Builder {

        private String id;
        private final String name;
        private final String code;
        private final Categories categories;
        private final BigDecimal price;
        private boolean promotionActive;
        private Promotion promotion;

        public Builder(final String name, final String code, final Categories categories, final BigDecimal price) {
            this.name = name;
            this.code = code;
            this.categories = categories;
            this.price = price;
            this.promotionActive = false;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder promotion(final Promotion promotion) {
            this.promotion = promotion;
            return this;
        }

        public Product build() {
            return new Product(id, code, name, price, categories, promotionActive, promotion);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
