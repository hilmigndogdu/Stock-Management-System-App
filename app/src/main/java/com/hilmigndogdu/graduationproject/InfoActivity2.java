package com.hilmigndogdu.graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivityInfo2Binding;

public class InfoActivity2 extends AppCompatActivity {

    ActivityInfo2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfo2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nexte bastıktan sonra bizi InfoActivitye yönlendiriyor

                Intent intenttoLogin = new Intent(InfoActivity2.this,LoginActivity.class);
                startActivity(intenttoLogin);
            }
        });

    }
}