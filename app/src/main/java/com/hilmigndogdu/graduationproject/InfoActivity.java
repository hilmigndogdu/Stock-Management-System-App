package com.hilmigndogdu.graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {
    ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nexte bastıktan sonra bizi InfoActivitye yönlendiriyor

                Intent intenttoInfo2 = new Intent(InfoActivity.this,InfoActivity2.class);
                startActivity(intenttoInfo2);
            }
        });
        binding.skiptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Skipe bastıkdan sonra bizi direk LoginActivitye yönlendiriyor

                Intent intenttoLogin = new Intent(InfoActivity.this,LoginActivity.class);
                startActivity(intenttoLogin);
            }
        });
    }
}