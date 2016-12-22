package com.fiveteam.malaysiahouse2;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment {
    private View rootView;
    private ViewPager ad1viewPager;
    private int selectedItem = 0;
    private int[] ad1images = new ItemGroups().ad1Images;
    private Toolbar toolbar;

    public FirstPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_first_page, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setHasOptionsMenu(true);

        ImageView ivNewhouse = (ImageView) rootView.findViewById(R.id.IV_pic_1) ;
        ivNewhouse.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                NewHouseFragment frg = new NewHouseFragment();
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragMgr.beginTransaction();
                transaction.replace(R.id.FL_dresses, frg, frg.getClass().getName()).addToBackStack(null).commit();
            }
        });
        initad1VP();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.e(TAG, "onCreateOptionsMenu()");
        menu.clear();
        inflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }

        searchView.setIconifiedByDefault(true);

        MenuItem item = toolbar.getMenu().findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        ImageView icon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        //你想指定的icon
        icon.setImageResource(R.drawable.icon_search);

        //return true;
        //return super.onCreateOptionsMenu(menu);
    }

    public void initad1VP(){
        P1ad1VPAdapter mCustomPagerAdapter = new P1ad1VPAdapter(getContext());
        ad1viewPager = (ViewPager) rootView.findViewById(R.id.VP_p1_ad1);
        ad1viewPager.setAdapter(mCustomPagerAdapter);
        ad1viewPager.setCurrentItem(0);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            ad1viewPager.setCurrentItem(selectedItem);
            selectedItem++;
            if(selectedItem == ad1images.length){
                selectedItem = 0;
            }
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    //每次當onResume有焦點時發送個空消息開始輪播
    @Override
    public void onResume(){
        super.onResume();
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    //當暫停時停止輪播
    @Override
    public void onPause(){
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

}
