package com.example.productshopxml.config;

import com.example.productshopxml.utils.ValidationUtil;
import com.example.productshopxml.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

    /*    Converter<User, String> converter = context -> context.getSource().getFullName();

        TypeMap<Product, ProductNamePriceSellerNameDTO> typeMap = modelMapper.getTypeMap(Product.class, ProductNamePriceSellerNameDTO.class);

        typeMap.addMappings(map -> map.using(converter).map(Product::getSeller, ProductNamePriceSellerNameDTO::setSeller));*/

        return modelMapper;
    }

    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
