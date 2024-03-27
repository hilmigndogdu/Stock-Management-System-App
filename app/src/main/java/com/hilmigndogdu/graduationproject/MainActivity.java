package com.hilmigndogdu.graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nexte bastıktan sonra bizi InfoActivitye yönlendiriyor

                Intent intenttoInfo = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intenttoInfo);
            }
        });

        binding.skiptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Skipe bastıkdan sonra bizi direk LoginActivitye yönlendiriyor

                Intent intenttoLogin = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intenttoLogin);
            }
        });
    }
}