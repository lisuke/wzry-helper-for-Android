package com.example.lisuke.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.lisuke.mytest.Activity.EquipMainActivity;
import com.example.lisuke.mytest.Activity.HeroActivity;
import com.example.lisuke.mytest.Activity.HeroMainActivity;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab;
    private Menu iMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "刷新...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                rleifsruekseh();
            }
        });



        final Button [] bEquip = new Button[6];

        bEquip[0] = (Button) findViewById(R.id.allequip);
        bEquip[1]  = (Button)findViewById(R.id.equip1);
        bEquip[2]  =(Button) findViewById(R.id.equip2);
        bEquip[3] = (Button)findViewById(R.id.equip3);
        bEquip[4]  =(Button) findViewById(R.id.equip4);
        bEquip[5]  = (Button)findViewById(R.id.equip5);

        final Button [] bHero = new Button[7];

        bHero[0] = (Button) findViewById(R.id.allhero);
        bHero[1]  = (Button)findViewById(R.id.hero1);
        bHero[2]  =(Button) findViewById(R.id.hero2);
        bHero[3] = (Button)findViewById(R.id.hero3);
        bHero[4]  =(Button) findViewById(R.id.hero4);
        bHero[5]  = (Button)findViewById(R.id.hero5);
        bHero[6]  = (Button)findViewById(R.id.hero6);
        for (int i = 0; i<bHero.length;i++){
            bHero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;

                    if (b == findViewById(R.id.hero1)){
                        showHeroView(1);
                    }
                    if (b == findViewById(R.id.hero2)){
                        showHeroView(2);
                    }
                    if (b == findViewById(R.id.hero3)){
                        showHeroView(3);
                    }
                    if (b == findViewById(R.id.hero4)){
                        showHeroView(4);
                    }
                    if (b == findViewById(R.id.hero5)){
                        showHeroView(5);
                    }
                    if (b == findViewById(R.id.hero6)){
                        showHeroView(6);
                    }
                    if (b == findViewById(R.id.allhero)){
                        showHeroView(0);
                    }
                }
            });
        }

        for (int i = 0; i<bEquip.length;i++){
            bEquip[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;

                    if (b == findViewById(R.id.equip1)){
                        showEquipView(1);
                    }
                    if (b == findViewById(R.id.equip2)){
                        showEquipView(2);
                    }
                    if (b == findViewById(R.id.equip3)){
                        showEquipView(3);
                    }
                    if (b == findViewById(R.id.equip4)){
                        showEquipView(4);
                    }
                    if (b == findViewById(R.id.equip5)){
                        showEquipView(5);
                    }
                    if (b == findViewById(R.id.allequip)){
                        showEquipView(0);
                    }
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        iMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static boolean showHeroCateItem = false;
    private static boolean showEquipCateItem = false;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        NavigationView nv = (NavigationView)findViewById(R.id.nav_view);

        if (id == R.id.nav_all_hero) {
            showHeroView();
        } else if (id == R.id.nav_all_hero_cate_switch) {
            nv.getMenu().setGroupVisible(R.id.nav_all_hero_cate,showHeroCateItem);
            showHeroCateItem = !showHeroCateItem;
        } if (id == R.id.nav_all_equip) {
            showEquipView();
        } else if (id == R.id.nav_all_hero_equip_switch) {
            nv.getMenu().setGroupVisible(R.id.nav_all_equip_cate,showEquipCateItem);
            showEquipCateItem = !showEquipCateItem;
        } else if (id == R.id.nav_refresh){
            Snackbar.make(fab, "同步...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            refresh();
        } else if (id == R.id.nav_hero_cate1){
            showHeroView(1);
        } else if (id == R.id.nav_hero_cate2){
            showHeroView(2);
        } else if (id == R.id.nav_hero_cate3){
            showHeroView(3);
        } else if (id == R.id.nav_hero_cate4){
            showHeroView(4);
        } else if (id == R.id.nav_hero_cate5){
            showHeroView(5);
        } else if (id == R.id.nav_hero_cate6){
            showHeroView(6);
        } else if (id == R.id.nav_equip_cate1){
            showEquipView(1);
        } else if (id == R.id.nav_equip_cate2){
            showEquipView(2);
        } else if (id == R.id.nav_equip_cate3){
            showEquipView(3);
        } else if (id == R.id.nav_equip_cate4){
            showEquipView(4);
        } else if (id == R.id.nav_equip_cate5){
            showEquipView(5);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showHeroView(int i) {
        Intent intent = new Intent(NavActivity.this, HeroMainActivity.class);
        intent.setFlags(i);
        startActivity(intent);
    }

    private void showHeroView() {
        showHeroView(0);
    }

    private void showEquipView() {
        showEquipView(0);
    }

    private void showEquipView(int i) {
        Intent intent = new Intent(NavActivity.this, EquipMainActivity.class);
        intent.setFlags(i);
        startActivity(intent);
    }

    private void rleifsruekseh() {

    }
}






