package com.exampleqq.myWebProgr.service;

import com.exampleqq.myWebProgr.exceptions.NotFoundException;
import com.exampleqq.myWebProgr.models.Exbarc;
import com.exampleqq.myWebProgr.models.Pack;
import com.exampleqq.myWebProgr.dto.ProductDTO;
import com.exampleqq.myWebProgr.models.Sales;
import com.exampleqq.myWebProgr.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> calculateProductCost(String exbarBody, BigDecimal quantity1,String isDiscountProvided) {

        Exbarc exbarc = getExbarcByExbarBody(exbarBody);
        if (exbarc == null ) {
            throw new NotFoundException();
        }
        Pack pack = exbarc.getPack();

        BigDecimal packType = pack.getPackType();
        int comparisonResult =packType.compareTo(BigDecimal.ZERO);
        if (comparisonResult == 1) {
            quantity1 = quantity1.setScale(2, RoundingMode.HALF_UP);
        } else {
            if (quantity1.scale() > 0) {
                throw new IllegalArgumentException("Для штучных товаров недопустимо вводить дробное количество.");
            }
        }
       return productRepository.calculateProductCost(exbarBody,quantity1,isDiscountProvided);
    }


    public List<Object []> findSalesWithTotalPriceAboveThreshold(BigDecimal priceThreshold,BigDecimal bonusThreshold) {
        return productRepository.findSalesWithTotalPriceGreaterThan(priceThreshold, bonusThreshold);
    }

    private Exbarc getExbarcByExbarBody(String exbarBody) {
        return productRepository.findByExbarBody(exbarBody);
    }

}
