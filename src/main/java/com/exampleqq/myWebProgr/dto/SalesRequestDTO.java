package com.exampleqq.myWebProgr.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class SalesRequestDTO {

    @NotEmpty(message = "Should not be empty")

    private List<ProductDTO> productDTOList;
    @Min(value = 0, message = "Should not be less than 0;")
    private BigDecimal cashPrice;
    @Min(value = 0, message = "Should not be less than 0;")
    private BigDecimal cardPrice;

    public SalesRequestDTO(List<ProductDTO> productDTOList, BigDecimal cashPrice, BigDecimal cardPrice) {
        this.productDTOList = productDTOList;
        this.cashPrice = cashPrice;
        this.cardPrice = cardPrice;
    }


    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public BigDecimal getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(BigDecimal cashPrice) {
        this.cashPrice = cashPrice;
    }

    public BigDecimal getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(BigDecimal cardPrice) {
        this.cardPrice = cardPrice;
    }
}
