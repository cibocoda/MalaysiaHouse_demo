package com.fiveteam.malaysiahouse2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragMgr = getSupportFragmentManager();
    NavigationView navigationView;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.inflateHeaderView(R.layout.nav_header_main);

        ImageView iv = (ImageView) header.findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.sidebar_avatar_shadow);
        TextView tv = (TextView) header.findViewById(R.id.textView);
        tv.setText(R.string.guest);

        initFirstPage();

    }

    public void initFirstPage(){
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = fragMgr.beginTransaction();
        FirstPageFragment fragment = new FirstPageFragment();
        transaction.replace(R.id.FL_dresses, fragment, fragment.getClass().getName()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = fragMgr.beginTransaction();

        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        int id = item.getItemId();

        switch (id){
            case R.id.theme_zone:
                ThemeZoneFragment tzfragment = new ThemeZoneFragment();
                transaction.replace(R.id.FL_dresses, tzfragment, tzfragment.getClass().getName()).commit();
                break;
            case R.id.map_gps:
                MapGpsFragment mgfragment = new MapGpsFragment();
                transaction.replace(R.id.FL_dresses, mgfragment, mgfragment.getClass().getName()).commit();
                break;
            case R.id.search:
                SearchFragment scfragment = new SearchFragment();
                transaction.replace(R.id.FL_dresses, scfragment, scfragment.getClass().getName()).commit();
                break;
            case R.id.barchart:
                BarchartFragment bcfragment = new BarchartFragment();
                transaction.replace(R.id.FL_dresses, bcfragment, bcfragment.getClass().getName()).commit();
                break;
            case R.id.favorite:
                FavoriteFragment frfragment = new FavoriteFragment();
                transaction.replace(R.id.FL_dresses, frfragment, frfragment.getClass().getName()).commit();
                break;
            case R.id.contact_us:
                ContactUsFragment cufragment = new ContactUsFragment();
                transaction.replace(R.id.FL_dresses, cufragment, cufragment.getClass().getName()).commit();
                break;
            case R.id.language_setting:
                LanguageSettingFragment lsfragment = new LanguageSettingFragment();
                transaction.replace(R.id.FL_dresses, lsfragment, lsfragment.getClass().getName()).commit();
                break;
            case R.id.q_a:
                QAFragment qafragment = new QAFragment();
                transaction.replace(R.id.FL_dresses, qafragment, qafragment.getClass().getName()).commit();
                break;
            case R.id.member_zone:
                MemberZoneFragment mzfragment = new MemberZoneFragment();
                transaction.replace(R.id.FL_dresses, mzfragment, mzfragment.getClass().getName()).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateDrawerHeader(){
        if(member_logged.member_loggin){
            ImageView iv = (ImageView) header.findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.sidebar_avatar);
            TextView tv = (TextView) header.findViewById(R.id.textView);
            tv.setText(R.string.member_a12345);
        }else{
            ImageView iv = (ImageView) header.findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.sidebar_avatar_shadow);
            TextView tv = (TextView) header.findViewById(R.id.textView);
            tv.setText(R.string.guest);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragMgr.getBackStackEntryCount() == 0) {
                AlertDialog.Builder ad=new AlertDialog.Builder(this); //建立訊息方塊
                ad.setTitle("離開 Malaysia House");
                ad.setMessage("確定要離開?");
                ad.setPositiveButton("是", new DialogInterface.OnClickListener() { //按"是"退出程式
                    public void onClick(DialogInterface dialog, int i) {
                        finish();
                    }
                });
                ad.setNegativeButton("否",new DialogInterface.OnClickListener() { //按"否",則不執行任何操作
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
                ad.show();//顯示訊息視窗
                //super.onBackPressed();
            } else {
                fragMgr.popBackStack();
            }
        }
    }
}
