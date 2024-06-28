package com.khair.tablayout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout,new Home_Fragment());
        transaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition=tab.getPosition();
                if (tabPosition==0){
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.add(R.id.frameLayout,new Home_Fragment());
                    transaction.commit();

                }else if (tabPosition==1){
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.add(R.id.frameLayout,new PROFILE());
                    transaction.commit();

                }else if (tabPosition==2){
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.add(R.id.frameLayout,new NOTIFICATION_Fragment());
                    transaction.commit();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
}