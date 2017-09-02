package com.example.lisuke.mytest.Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.lisuke.mytest.Model.Equip;
import com.example.lisuke.mytest.Model.Hero;
import com.example.lisuke.mytest.Model.HeroCate;
import com.example.lisuke.mytest.Model.HeroEquipSuggest;
import com.example.lisuke.mytest.Model.HeroSkill;
import com.example.lisuke.mytest.MyApplication;
import com.example.lisuke.mytest.R;
import com.example.lisuke.mytest.download.DownloadUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class HeroActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        imageView = (ImageView)findViewById(R.id.imageView);
        int heroId = getIntent().getFlags();
        System.out.println(heroId);
        Hero hero = null;

        try {
            Dao heroDao = MyApplication.getDao(Hero.class);
            hero = (Hero) heroDao.queryForId(heroId);
            ForeignCollection<HeroSkill> skills = hero.getHeroAllSkill();
            ForeignCollection<HeroEquipSuggest> suggestEquip = hero.getHeroAllEquipSuggest();
            ((TextView)findViewById(R.id.view_hero_name)).setText(hero.getHeroName());
            ((ImageView)findViewById(R.id.view_hero_image)).setImageBitmap(BitmapFactory.decodeFile(MyApplication.getIconPath()+"/hero/"+heroId+".jpg"));
            ((TextView)findViewById(R.id.view_hero_story)).setText(hero.getHeroBackStory());
            ((TextView)findViewById(R.id.view_hero_history)).setText(hero.getHeroAnnal());

            progress(R.id.view_hero_difficulty,hero.getHeroOverHand());
            progress(R.id.view_hero_viability,hero.getHeroViability());
            progress(R.id.view_hero_skillEffect,hero.getHeroSkillDamage());
            progress(R.id.view_hero_harm,hero.getHeroAttackDamage());
            // 出装
            int top = R.id.view_hero_topequip1;
            int mid = R.id.view_hero_midequip1;
            int after = R.id.view_hero_afterequip1;
            for (HeroEquipSuggest heroEquipSuggest:suggestEquip){
                int index = heroEquipSuggest.getHeroEquipSuggestIndex();
                if (index==0){// 早期
                    setHeroEquip(top,heroEquipSuggest.getEquip().getEquipId());
                }
                if (index>=1 && index<=3) {//中期
                    setHeroEquip(mid++,heroEquipSuggest.getEquip().getEquipId());
                }
                if (index!=0){//后期
                    setHeroEquip(after++,heroEquipSuggest.getEquip().getEquipId());
                }
            }
            //技能
            TabHost th =(TabHost) findViewById(R.id.view_hero_skill_tabs);
            LinearLayout layout =(LinearLayout) findViewById(R.id.view_hero_skill1);
            th.setup();
            int skillIndex = 0;
            for (HeroSkill heroSkill : skills){
                TextView name = (TextView)findViewById(R.id.view_hero_skill1_name);
                System.out.println(name);
                System.out.println(heroSkill.getHeroSkillName());
                if (heroSkill.getHeroSkillName().equals("undefined")){
                    break;
                }
                name.setText(heroSkill.getHeroSkillName());
                TextView consume = (TextView)findViewById(R.id.view_hero_skill1_consume);
                consume.setText(heroSkill.getConsume());
                TextView cooling = (TextView)findViewById(R.id.view_hero_skill1_cooling);
                cooling.setText(heroSkill.getCoolingRate());
                TextView desc = (TextView)findViewById(R.id.view_hero_skill1_describe);
                desc.setText(heroSkill.getDescription());
                String skillPath = MyApplication.getIconPath()+"/skills/";
                isDownLoad(heroId*100+skillIndex*10,heroId,skillPath);
                Bitmap icon = BitmapFactory.decodeFile(skillPath+(heroId*100+skillIndex*10)+".png");
                Drawable drawable = new BitmapDrawable(icon);
                LayoutInflater factory = LayoutInflater.from(this);
/*
                LinearLayout l = (LinearLayout) factory.inflate(R.layout.hero_skill,null);
                l.setId(View.generateViewId());
                */
                th.addTab(th.newTabSpec("view_hero_skill"+skillIndex).setIndicator("技能"+skillIndex,drawable).setContent(R.id.view_hero_skill1));

                th.setCurrentTab(skillIndex);
                skillIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void progress(int id, float scale) {
        TextView v = (TextView)findViewById(id);

        float d = v.getWidth();
        System.out.println(d);
        d= scale / 100 ;
        System.out.println(d);
        v.setScaleX(d);
        //v.setWidth((int)d);
    }

    private void setHeroEquip(int id,int equipId){
        ImageView imageView = (ImageView)findViewById(id);
        imageView.setImageBitmap(BitmapFactory.decodeFile(MyApplication.getIconPath()+"/equip/"+equipId+".jpg"));

    }

    private void isDownLoad(int skillId,int heroId, String iconPath) {

        System.out.println("http://game.gtimg.cn/images/yxzj/img201606/heroimg/"+heroId+"/"+skillId+".png");

        new File(iconPath).mkdirs();
        if (!new File(iconPath+skillId+".png").exists()) {
            DownloadUtil.get().download("http://game.gtimg.cn/images/yxzj/img201606/heroimg/"+heroId+"/"+skillId+".png", iconPath, new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess() {
                    //Snackbar.make(EquipMainActivity.this, getString(R.string.download_succ), Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onDownloading(int progress) {
                    //progressBar.setProgress(progress);
                    System.out.println(progress);
                }

                @Override
                public void onDownloadFailed() {
                    finish();
                }
            });
        }
    }


    public void clicked(View view){
        finish();
    }

}
