package com.application.java.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public final class Categories implements Serializable {
    private Collection<String> values;

    private Categories(final Collection<String> values) {
        this.values = values;
    }

    public static Categories from(final Collection<String> categories) {
        final var values = categories.stream().map(String::toUpperCase).toList();
        return new Categories(values);
    }

}
