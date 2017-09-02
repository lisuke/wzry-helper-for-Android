package com.example.lisuke.mytest.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import com.example.lisuke.mytest.Model.Hero;
import com.example.lisuke.mytest.Model.HeroCate;
import com.example.lisuke.mytest.MyApplication;
import com.example.lisuke.mytest.R;
import com.example.lisuke.mytest.download.DownloadUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroMainActivity extends AppCompatActivity {
    Map<Integer,Integer> bind = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);
        bind = new HashMap<Integer,Integer>();
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        ArrayList<Hero> allHeros = null;
        ForeignCollection<Hero> cateHeros = null;
        int heroCateId = getIntent().getFlags();
        try {
            Dao heroCateDao = MyApplication.getDao(HeroCate.class);
            Dao heroDao = MyApplication.getDao(Hero.class);

            if (heroCateId == 0){//无分类
                allHeros = (ArrayList<Hero>) heroDao.queryForAll();
            }else{//有分类
                HeroCate heroCate = (HeroCate) heroCateDao.queryForId(heroCateId);
                cateHeros = (ForeignCollection<Hero>) heroCate.getAllHero();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        if (heroCateId == 0)
        for (int i = 0;i<allHeros.size();i++){
            Hero hero = allHeros.get(i);
            System.out.println(hero);
            int heroId = hero.getHeroId();
            String iconPath = MyApplication.getIconPath()+"/hero/";
            isDownLoad(heroId,iconPath);
            Bitmap icon = BitmapFactory.decodeFile(iconPath+heroId+".jpg");
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("heroListIcon",icon);
            map.put("heroListName",hero.getHeroName());
            listItems.add(map);
            bind.put(i,heroId);
        }
        else
            for (Hero hero :cateHeros){
                System.out.println(hero);
                int heroId = hero.getHeroId();
                String iconPath = MyApplication.getIconPath()+"/hero/";
                isDownLoad(heroId,iconPath);
                Bitmap icon = BitmapFactory.decodeFile(iconPath+heroId+".jpg");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("heroListIcon",icon);
                map.put("heroListName",hero.getHeroName());
                listItems.add(map);
                bind.put(k++,heroId);
            }

        final ImageView imageView =  (ImageView)findViewById(R.id.imageView);
        GridView grid = (GridView) findViewById(R.id.grid_hero_list);
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.content_hero,new String[]{"heroListIcon","heroListName"},new int[]{R.id.heroListIcon,R.id.heroListName});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap){
                    ((ImageView) view).setImageBitmap((Bitmap) data);
                    return true;
                }else
                    return false;
            }
        });
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //imageView.setImageResource(R.drawable.hero_105+position);
                Intent intent = new Intent(HeroMainActivity.this,HeroActivity.class);
                intent.setFlags(bind.get(position));
                startActivity(intent);
            }
        });

    }

    private void isDownLoad(int equipId, String iconPath) {

        System.out.println("http://game.gtimg.cn/images/yxzj/img201606/heroimg/"+equipId+"/" + equipId + ".jpg");


        System.out.println(iconPath+equipId+".jpg");
        new File(iconPath).mkdirs();
        if (!new File(iconPath+equipId+".jpg").exists()) {
            DownloadUtil.get().download("http://game.gtimg.cn/images/yxzj/img201606/heroimg/"+equipId+"/" + equipId + ".jpg", iconPath, new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess() {
                    //Snackbar.make(EquipMainActivity.this, getString(R.string.download_succ), Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onDownloading(int progress) {
                    System.out.println(progress);
                }

                @Override
                public void onDownloadFailed() {
                    //Snackbar.make(EquipMainActivity.this, getString(R.string.download_error), Snackbar.LENGTH_LONG).show();
//
                    finish();
                }
            });
        }
    }

}