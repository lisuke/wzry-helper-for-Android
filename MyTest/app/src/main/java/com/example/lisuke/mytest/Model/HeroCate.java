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
@DatabaseTable(tableName = "hero_cate")
public class HeroCate {
    
    @DatabaseField(id = true)
    private int heroCateId;
    
    @DatabaseField(canBeNull = false,unique=true)
    private String heroCateName;
    
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Hero> allHero;
    
    public int getHeroCateId() {
        return heroCateId;
    }

    public void setHeroCateId(int heroCateId) {
        this.heroCateId = heroCateId;
    }

    public ForeignCollection<Hero> getAllHero() {
        return allHero;
    }

    public void setAllHero(ForeignCollection<Hero> allHero) {
        this.allHero = allHero;
    }

    public String getHeroCateName() {
        return heroCateName;
    }

    public void setHeroCateName(String heroCateName) {
        this.heroCateName = heroCateName;
    }

    @Override
    public String toString() {
        return "HeroCate{" + "heroCateId=" + heroCateId + ", heroCateName=" + heroCateName + ", allHero=" + allHero + '}';
    }

}
