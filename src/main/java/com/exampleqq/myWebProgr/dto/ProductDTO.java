package com.exampleqq.myWebProgr.dto;



import java.math.BigDecimal;

public class ProductDTO {


    private String exbarBody;
    private String packName;
    private BigDecimal quantity1;
    private BigDecimal totalPrice ;

    private BigDecimal bonusPrice;

    private String unitName;
    private BigDecimal packType;

    public ProductDTO(String exbarBody, String packName,BigDecimal quantity1, BigDecimal totalPrice,BigDecimal bonusPrice, String unitName, BigDecimal packType) {
        this.exbarBody = exbarBody;
        this.packName = packName;
        this.quantity1 = quantity1;
        this.totalPrice = totalPrice;
        this.bonusPrice=bonusPrice;
        this.unitName = unitName;
        this.packType = packType;
    }



    public String getExbarBody() {
        return exbarBody;
    }

    public void setExbarBody(String exbarBody) {
        this.exbarBody = exbarBody;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public BigDecimal getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(BigDecimal quantity1) {
        this.quantity1 = quantity1;
    }

    public BigDecimal getBonusPrice() {
        return bonusPrice;
    }

    public void setBonusPrice(BigDecimal bonusPrice) {
        this.bonusPrice = bonusPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getPackType() {
        return packType;
    }

    public void setPackType(BigDecimal packType) {
        this.packType = packType;
    }
}

