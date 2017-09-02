package com.example.lisuke.mytest.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.lisuke.mytest.Model.Equip;
import com.example.lisuke.mytest.Model.EquipCate;
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

/**
 * Created by lisuke on 17-6-15.
 */

public class EquipMainActivity extends AppCompatActivity {

    Map<Integer,Integer> bind = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_list);
        bind = new HashMap<Integer,Integer>();
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        ArrayList<Equip> allEquips = null;
        ForeignCollection<Equip> cateEquips = null;
        int equipCateId = getIntent().getFlags();
        System.out.println(equipCateId);
        try {
            Dao equipCateDao = MyApplication.getDao(EquipCate.class);
            Dao equipDao = MyApplication.getDao(Equip.class);

            if (equipCateId == 0){//无分类
                allEquips = (ArrayList<Equip>) equipDao.queryForAll();
                System.out.println(11111111);
            }else{//有分类
                EquipCate equipCate = (EquipCate) equipCateDao.queryForId(equipCateId);
                System.out.println(22222222);
                cateEquips = (ForeignCollection<Equip>) equipCate.getAllEquip();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int k = 0;
        if (equipCateId == 0)
            for (int i = 0;i<allEquips.size();i++){
                Equip equip = allEquips.get(i);
                System.out.println(equip);
                int equipId = equip.getEquipId();
                String iconPath = MyApplication.getIconPath()+"/equip/";
                isDownLoad(equipId,iconPath);
                Bitmap icon = BitmapFactory.decodeFile(iconPath+equipId+".jpg");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("equipListIcon",icon);
                map.put("equipListName",equip.getEquipName());
                listItems.add(map);
                bind.put(i,equipId);
            }
        else
            for (Equip equip :cateEquips){
                System.out.println(equip);
                int equipId = equip.getEquipId();
                String iconPath = MyApplication.getIconPath()+"/equip/";
                isDownLoad(equipId,iconPath);
                Bitmap icon = BitmapFactory.decodeFile(iconPath+equipId+".jpg");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("equipListIcon",icon);
                map.put("equipListName",equip.getEquipName());
                listItems.add(map);
                bind.put(k++,equipId);
            }

        GridView grid = (GridView) findViewById(R.id.grid_equip_list);
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.content_equip,new String[]{"equipListIcon","equipListName"},new int[]{R.id.equipListIcon,R.id.equipListName});
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
                Intent intent = new Intent(EquipMainActivity.this,EquipActivity.class);
                intent.setFlags(bind.get(position));
                startActivity(intent);
            }
        });

    }

    private void isDownLoad(int equipId, String iconPath) {

        System.out.println("http://game.gtimg.cn/images/yxzj/img201606/itemimg/" + equipId + ".jpg");
        System.out.println(iconPath+equipId+".jpg");
        new File(iconPath).mkdirs();
        if (!new File(iconPath+equipId+".jpg").exists()) {
            DownloadUtil.get().download("http://game.gtimg.cn/images/yxzj/img201606/itemimg/" + equipId + ".jpg", iconPath, new DownloadUtil.OnDownloadListener() {
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
                    //Snackbar.make(EquipMainActivity.this, getString(R.string.download_error), Snackbar.LENGTH_LONG).show();
//                    System.out.println("http://game.gtimg.cn/images/yxzj/img201606/itemimg/" + equipId + ".jpg");
//                    System.out.println(iconPath+equipId+".jpg");
                    finish();
                }
            });
        }
    }

}
