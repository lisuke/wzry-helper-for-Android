package com.example.lisuke.mytest.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lisuke
 */

@DatabaseTable(tableName = "equip")
public class Equip {
    
    @DatabaseField(id = true,unique = true)
    int equipId;
    
    @DatabaseField(canBeNull = false)
    String equipName = null;
    
    @DatabaseField(foreign=true,foreignAutoRefresh = true)
    private EquipCate equipCate;
    
    @DatabaseField()
    private int price;

    @DatabaseField()
    private int totalPrice;
    
    @DatabaseField()
    private String des1;
    
    @DatabaseField()
    private String des2;
    
    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDes1() {
        return des1;
    }

    public void setDes1(String des1) {
        this.des1 = des1;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public EquipCate getEquipCate() {
        return equipCate;
    }

    public void setEquipCate(EquipCate equipCate) {
        this.equipCate = equipCate;
    }

    @Override
    public String toString() {
        return "Equip{" + "equipId=" + equipId + ", equipName=" + equipName + ", equipCate=" + equipCate + ", price=" + price + ", totalPrice=" + totalPrice + ", des1=" + des1 + ", des2=" + des2 + '}';
    }
    
}
