package com.exampleqq.myWebProgr.models;


import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sales {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "salesid")
    private int salesId;

    @Column(name = "salestime")
    private LocalDateTime salesTime;

    @Column(name = "salestag")
    private BigDecimal salesTag;

    @Column(name = "exbarbody")
    private String exbarBody;

    @Column(name = "packname")
    private String packName;

    @Column(name = "unitname")
    private String unitName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "bonusprice")
    private BigDecimal bonusPrice;

    @Column(name = "quantity")
    private BigDecimal quantity;

    public Sales(LocalDateTime salesTime, BigDecimal salesTag, String exbarBody, String packName, String unitName, BigDecimal price, BigDecimal bonusPrice, BigDecimal quantity) {
        this.salesTime = salesTime;
        this.salesTag = salesTag;
        this.exbarBody = exbarBody;
        this.packName = packName;
        this.unitName = unitName;
        this.price = price;
        this.bonusPrice = bonusPrice;
        this.quantity = quantity;
    }

    public Sales() {

    }


    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public LocalDateTime getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(LocalDateTime salesTime) {
        this.salesTime = salesTime;
    }

    public BigDecimal getSalesTag() {
        return salesTag;
    }

    public void setSalesTag(BigDecimal salesTag) {
        this.salesTag = salesTag;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBonusPrice() {
        return bonusPrice;
    }

    public void setBonusPrice(BigDecimal bonusPrice) {
        this.bonusPrice = bonusPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
