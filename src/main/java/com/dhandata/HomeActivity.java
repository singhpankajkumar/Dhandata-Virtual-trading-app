package com.dhandata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
BottomNavigationView bnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bnView = findViewById(R.id.homeNavi); //call by though button navigation id

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id==R.id.nav_home){
                    loadFrag(new HomeFragment(),false);

                }else if (id==R.id.nav_watchlist) {
                    loadFrag(new WatchlistFragment(), true);
                }
                 else if (id==R.id.nav_order) {
                    loadFrag(new OrderFragment(),false);

                } else if (id==R.id.nav_portfolio) {
                    loadFrag(new PortfolioFragment(),false);

                } else { //fund
                    loadFrag(new FundFragment(),false);
                }

                return true;
            }
        });
         bnView.setSelectedItemId(R.id.nav_watchlist); //for open fist fragmrnt


    }
    public void loadFrag(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.homeframe,new Fragment());

        if (flag)
            ft.add(R.id.homeframe,fragment); //homeframe is name of fragment id call from home activity
        else
            ft.replace(R.id.homeframe,fragment);
        ft.commit();
    }
}