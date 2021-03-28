package com.malik.urfan.microservice.domain;

import java.util.Arrays;

public class CrimeCategoryProducer {

    private String[] categories;

    public CrimeCategoryProducer(String[] categories)
    {
        this.categories = categories;
    }

    public String[] getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "CrimeCategoryProducer{" +
                "categories=" + Arrays.toString(categories) +
                '}';
    }
}
