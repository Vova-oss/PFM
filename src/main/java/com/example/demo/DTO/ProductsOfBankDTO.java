package com.example.demo.DTO;

import com.example.demo.Entity.ProductsOfBank;
import lombok.Data;

@Data
public class ProductsOfBankDTO {
    ProductsOfBankDTO(){}

    private String title;
    private String description;
    private String link;

    public static ProductsOfBankDTO createProductsOfBankDTO(ProductsOfBank productsOfBank){
        ProductsOfBankDTO productsOfBankDTO = new ProductsOfBankDTO();
        productsOfBankDTO.setDescription(productsOfBank.getDescription());
        productsOfBankDTO.setTitle(productsOfBank.getTitle());
        productsOfBankDTO.setLink(productsOfBank.getLink());
        return productsOfBankDTO;
    }

}
