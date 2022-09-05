package com.example.customsidenavbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
ChipNavigationBar chipNavigationBar;
Fragment fragment=null;
FragmentManager fragmentManager;
FragmentTransaction fragmentTransaction;
LinearLayout constraintMain ;
ImageView expandButton;
ChangeBounds changeBounds = new ChangeBounds();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipNavigationBar = findViewById(R.id.bottom_menu);
        constraintMain = findViewById(R.id.linearLayout);
        expandButton = findViewById(R.id.expand_button);
        if (fragment==null){
            fragmentManager =getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,new HomeFragment())
                    .commit();
        }
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        break;
                }
                fragmentManager =getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout,fragment)
                        .commit();
            }
        });

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chipNavigationBar.isExpanded()){
                    TransitionManager.beginDelayedTransition(constraintMain,changeBounds);
                    chipNavigationBar.collapse();
                    expandButton.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                }else{
                    TransitionManager.beginDelayedTransition(constraintMain,changeBounds);
                    chipNavigationBar.expand();
                    expandButton.setImageResource(R.drawable.ic_baseline_arrow_forward_24 );
                }
            }
        });
    }
}