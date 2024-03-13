package com.exampleqq.myWebProgr.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "packprc")
public class PackPrc implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "packid",referencedColumnName = "packid")
    private Pack pack;

    @Column(name = "packprice")
    private BigDecimal packPrice;


    @Column(name = "packbonus")
    private  BigDecimal packBonus;

    public PackPrc(Pack pack, BigDecimal packPrice, BigDecimal packBonus) {
        this.pack = pack;
        this.packPrice = packPrice;
        this.packBonus = packBonus;
    }

    public PackPrc() {

    }


    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
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
}
