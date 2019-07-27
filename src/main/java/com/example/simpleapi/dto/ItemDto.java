package com.example.simpleapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Double price;

    public ItemDto(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public ItemDto(ItemDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static class ItemDtoBuilder {

         Long id;

         String name;

         String description;

         Double price;

         public ItemDtoBuilder() {}

        public ItemDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ItemDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDtoBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public ItemDto build() {
             return new ItemDto(this);
        }
    }
}
