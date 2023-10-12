package com.example.secondapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView imgv;
    NavigationView nvg;
    DrawerLayout nvdrw;

    BottomNavigationView bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loading the default fragment

        imgv = findViewById(R.id.slid);
        nvdrw = findViewById(R.id.nvgdrower);
        nvg = findViewById(R.id.nvgdr);
      bottom=findViewById(R.id.bottom);

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nvdrw.openDrawer(GravityCompat.START);
            }
        });

        loadFragment(new Homefragment());

        nvg.bringToFront();
        nvg.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nvhome) {
                    loadFragment(new Homefragment());
                    nvdrw.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.myact) {
                    loadFragment(new AccountFragment());
                    nvdrw.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.setting) {
                    loadFragment(new SettingFragment());
                    nvdrw.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.logout) {
                    loadFragment(new LogoutFragment());
                    nvdrw.closeDrawer(GravityCompat.START);
                }

                return false;
            }
        });
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.person){
                        loadFragment(new BottomFragment());
                    }else if(item.getItemId()==R.id.home){
                        loadFragment(new Homefragment());
                    }else if(item.getItemId()==R.id.setting){
                        loadFragment(new SettingFragment());
                    }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment){
         FragmentManager fm=  getSupportFragmentManager();
         FragmentTransaction fragmentTransaction=fm.beginTransaction();
         fragmentTransaction.replace(R.id.framelayout,fragment);
         fragmentTransaction.commit();
    }
}