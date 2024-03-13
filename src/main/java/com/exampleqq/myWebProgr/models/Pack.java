package com.exampleqq.myWebProgr.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "pack")
public class Pack {

    @Id
    @Column(name = "packid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "packname")
    private String packName;


    @Column(name = "packquant")
    private BigDecimal packQuant;

    @Column(name = "packtype")
    private BigDecimal packType;

    @ManyToOne
    @JoinColumn(name = "unitid",referencedColumnName = "unit_id")
    private Unit unitid;

    @OneToOne(mappedBy = "pack")
    private Exbarc exbarc;

    public Pack(String packName, BigDecimal packQuant,BigDecimal packType, Unit unitid) {
        this.packName = packName;
        this.packQuant = packQuant;
        this.packType = packType;
        this.unitid = unitid;
    }

    public Pack() {
    }

    public Number getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Exbarc getExbarc() {
        return exbarc;
    }

    public void setExbarc(Exbarc exbarc) {
        this.exbarc = exbarc;
    }



    public Unit getUnitid() {
        return unitid;
    }

    public void setUnitid(Unit unitid) {
        this.unitid = unitid;
    }
}
