package com.exampleqq.myWebProgr.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @Column(name = "unit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitid;

    @Column(name = "unitname")
    private String  unitName;

    @OneToMany(mappedBy = "unitid")
    private List<Pack> packList;


    public Unit(String unitName, List<Pack> packList) {
        this.unitName = unitName;
        this.packList = packList;
    }

    public Unit() {

    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public List<Pack> getPackList() {
        return packList;
    }

    public void setPackList(List<Pack> packList) {
        this.packList = packList;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }
}
