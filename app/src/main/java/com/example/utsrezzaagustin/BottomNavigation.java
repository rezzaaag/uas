package com.example.utsrezzaagustin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.utsrezzaagustin.databinding.ActivityBottomNavigation3Binding;

public class BottomNavigation extends AppCompatActivity {
    Button website;
    EditText edit_website;

    private ActivityBottomNavigation3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomNavigation3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_person)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navigation3);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        website = findViewById(R.id.btn_openwebsite);
        edit_website = findViewById(R.id.website_edit_text);

        website.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = edit_website.getText().toString();
                Uri webPage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                if (intent.resolveActivity(getPackageManager()) !=null){
                    startActivity(intent);
                }else{
                    Log.d("ImplicitIntents", "Can't handle this Intent!");
                }
            }
        });
    }

}