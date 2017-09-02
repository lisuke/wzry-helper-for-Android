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

@DatabaseTable(tableName = "hero")
public class Hero {
    
    @DatabaseField(id = true)
    int heroId;
    
    @DatabaseField(canBeNull = false,unique = true)
    String heroName;
    
    @DatabaseField(foreign = true,foreignAutoRefresh= true)
    HeroCate heroCate;
    
    @ForeignCollectionField(eager = true)
    private ForeignCollection<HeroSkill> heroAllSkill;
    
    @ForeignCollectionField(eager = true)
    private ForeignCollection<HeroEquipSuggest> heroAllEquipSuggest;
    
    @DatabaseField()
    int heroViability ;//生存能力
    
    @DatabaseField()
    int heroAttackDamage ;//攻击伤害
    
    @DatabaseField()
    int heroSkillDamage ;//技能效果
    
    @DatabaseField()
    int heroOverHand ;//上手难度
    
    @DatabaseField()
    String heroBackStory = null;
    
    @DatabaseField()
    String heroAnnal = null;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public HeroCate getHeroCate() {
        return heroCate;
    }

    public void setHeroCate(HeroCate heroCate) {
        this.heroCate = heroCate;
    }

    public int getHeroViability() {
        return heroViability;
    }

    public void setHeroViability(int heroViability) {
        this.heroViability = heroViability;
    }

    public int getHeroAttackDamage() {
        return heroAttackDamage;
    }

    public void setHeroAttackDamage(int heroAttackDamage) {
        this.heroAttackDamage = heroAttackDamage;
    }

    public int getHeroSkillDamage() {
        return heroSkillDamage;
    }

    public void setHeroSkillDamage(int heroSkillDamage) {
        this.heroSkillDamage = heroSkillDamage;
    }

    public int getHeroOverHand() {
        return heroOverHand;
    }

    public void setHeroOverHand(int heroOverHand) {
        this.heroOverHand = heroOverHand;
    }

    public String getHeroBackStory() {
        return heroBackStory;
    }

    public void setHeroBackStory(String heroBackStory) {
        this.heroBackStory = heroBackStory;
    }

    public String getHeroAnnal() {
        return heroAnnal;
    }

    public void setHeroAnnal(String heroAnnal) {
        this.heroAnnal = heroAnnal;
    }

    public ForeignCollection<HeroSkill> getHeroAllSkill() {
        return heroAllSkill;
    }

    public void setHeroAllSkill(ForeignCollection<HeroSkill> heroAllSkill) {
        this.heroAllSkill = heroAllSkill;
    }

    public ForeignCollection<HeroEquipSuggest> getHeroAllEquipSuggest() {
        return heroAllEquipSuggest;
    }

    public void setHeroAllEquipSuggest(ForeignCollection<HeroEquipSuggest> heroAllEquipSuggest) {
        this.heroAllEquipSuggest = heroAllEquipSuggest;
    }

    @Override
    public String toString() {
        return "Hero{" + "heroId=" + heroId + ", heroName=" + heroName + ", heroCate=" + heroCate + ", heroAllSkill=" + heroAllSkill + ", heroAllEquipSuggest=" + heroAllEquipSuggest + ", heroViability=" + heroViability + ", heroAttackDamage=" + heroAttackDamage + ", heroSkillDamage=" + heroSkillDamage + ", heroOverHand=" + heroOverHand + ", heroBackStory=" + heroBackStory + ", heroAnnal=" + heroAnnal + '}';
    }

}
