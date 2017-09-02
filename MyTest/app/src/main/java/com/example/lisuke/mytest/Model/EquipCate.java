/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lisuke.mytest.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author lisuke
 */


@DatabaseTable(tableName= "equip_cate")
public class EquipCate {
    
    @DatabaseField(id=true)
    private int equipCateId;
    
    @DatabaseField(unique=true)
    private String equipCateName;
    
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Equip> allEquip;
    
    public ForeignCollection<Equip> getAllEquip() {
        return allEquip;
    }

    public void setAllEquip(ForeignCollection<Equip> allEquip) {
        this.allEquip = allEquip;
    }

    @Override
    public String toString() {
        return "EquipCate{" + "equipCateId=" + equipCateId + ", equipCateName=" + equipCateName + ", allEquip=" + allEquip + '}';
    }

    public int getEquipCateId() {
        return equipCateId;
    }

    public void setEquipCateId(int equipCateId) {
        this.equipCateId = equipCateId;
    }

    public String getEquipCateName() {
        return equipCateName;
    }

    public void setEquipCateName(String equipCateName) {
        this.equipCateName = equipCateName;
    }

}
