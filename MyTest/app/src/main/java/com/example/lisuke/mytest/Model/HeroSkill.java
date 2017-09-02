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
@DatabaseTable(tableName= "hero_skill")
public class HeroSkill {
    
    @DatabaseField(generatedId= true,unique=true)
    private int  heroSkillId ;
    
    @DatabaseField(canBeNull=false)
    private String heroSkillName = null;
    
    @DatabaseField(foreign= true,foreignAutoRefresh = true)
    private Hero hero = null;
    
    @DatabaseField()
    private String coolingRate = null;
    
    @DatabaseField()
    private String consume = null;
    
    @DatabaseField()
    private String description = null;
    
    @DatabaseField()
    private int suggestPriority;

    public int getHeroSkillId() {
        return heroSkillId;
    }

    public void setHeroSkillId(int heroSkillId) {
        this.heroSkillId = heroSkillId;
    }
    
    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public String getCoolingRate() {
        return coolingRate;
    }

    public void setCoolingRate(String coolingRate) {
        this.coolingRate = coolingRate;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSuggestPriority() {
        return suggestPriority;
    }

    public void setSuggestPriority(int suggestPriority) {
        this.suggestPriority = suggestPriority;
    }

    public String getHeroSkillName() {
        return heroSkillName;
    }

    public void setHeroSkillName(String heroSkillName) {
        this.heroSkillName = heroSkillName;
    }

    @Override
    public String toString() {
        return "HeroSkill{" + "heroSkillId=" + heroSkillId + ", heroSkillName=" + heroSkillName + ", hero=" + hero + ", coolingRate=" + coolingRate + ", consume=" + consume + ", description=" + description + ", suggestPriority=" + suggestPriority + '}';
    }
    
}
