package com.example.desafiowebservice.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

import com.example.desafiowebservice.R;
import com.example.desafiowebservice.view.fragment.AutorsFragment;
import com.example.desafiowebservice.view.fragment.HeroesFragment;
import com.example.desafiowebservice.view.fragment.HqsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment( new HqsFragment());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        getSupportActionBar().hide();



        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Heroi, R.id.navigation_Quadrinhos, R.id.navigation_Autores).build();



        navView.setOnNavigationItemSelectedListener(menuItem ->{
            int id =menuItem.getItemId();

            if(id == R.id.navigation_Heroi){
                replaceFragment(new HeroesFragment());

            } else if (id == R.id.navigation_Quadrinhos){

                replaceFragment(new HqsFragment());

            } else if (id == R.id.navigation_Autores){

                replaceFragment(new AutorsFragment());
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerPrincipal, fragment);
        transaction.commit();

    }
}
