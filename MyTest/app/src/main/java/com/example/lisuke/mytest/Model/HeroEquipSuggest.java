/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lisuke.mytest.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author lisuke
 */

@DatabaseTable(tableName="hero_equip_suggest")
public class HeroEquipSuggest {
    
    @DatabaseField(generatedId = true)
    private int suggestEquipId;
    
    @DatabaseField(foreign= true,foreignAutoRefresh= true)
    private Hero hero;
    
    @DatabaseField(foreign= true,foreignAutoRefresh= true)
    private Equip equip;
    
    @DatabaseField(canBeNull = false)
    private int heroEquipSuggestIndex;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public int getHeroEquipSuggestIndex() {
        return heroEquipSuggestIndex;
    }

    public void setHeroEquipSuggestIndex(int heroEquipSuggestIndex) {
        this.heroEquipSuggestIndex = heroEquipSuggestIndex;
    }

    public int getSuggestEquipId() {
        return suggestEquipId;
    }

    public void setSuggestEquipId(int suggestEquipId) {
        this.suggestEquipId = suggestEquipId;
    }

    @Override
    public String toString() {
        return "HeroEquipSuggest{" + "suggestEquipId=" + suggestEquipId + ", hero=" + hero + ", equip=" + equip + ", heroEquipSuggestIndex=" + heroEquipSuggestIndex + '}';
    }
    
}
