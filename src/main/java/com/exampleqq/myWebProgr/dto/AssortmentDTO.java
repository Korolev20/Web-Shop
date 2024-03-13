package com.exampleqq.myWebProgr.dto;


import java.math.BigDecimal;
import java.math.BigInteger;

public class AssortmentDTO {

    private String exbarBody;
    private String packName;
    private BigDecimal packQuant;
    private BigDecimal packType;
    private BigDecimal packPrice;
    private BigDecimal packBonus;
    private String unitName;


    public AssortmentDTO(String exbarBody, String packName, BigDecimal packQuant, BigDecimal packType,BigDecimal packPrice, BigDecimal packBonus, String unitName) {
        this.exbarBody = exbarBody;
        this.packName = packName;
        this.packQuant = packQuant;
        this.packType = packType;
        this.packPrice = packPrice;
        this.packBonus = packBonus;
        this.unitName = unitName;
    }

    public AssortmentDTO() {
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

    public BigDecimal getPackQuant() {
        return packQuant;
    }

    public void setPackQuant(BigDecimal packQuant) {
        this.packQuant = packQuant;
    }

    public BigDecimal getPackType() {
        return packType;
    }

    public void setPackType(BigDecimal packType) {
        this.packType = packType;
    }

    public BigDecimal getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(BigDecimal packPrice) {
        this.packPrice = packPrice;
    }

    public BigDecimal getPackBonus() {
        return packBonus;
    }

    public void setPackBonus(BigDecimal packBonus) {
        this.packBonus = packBonus;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}


