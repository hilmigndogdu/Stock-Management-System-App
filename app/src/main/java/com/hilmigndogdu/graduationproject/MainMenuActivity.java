package com.hilmigndogdu.graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {

    ActivityMainMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.readCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Readcardview'e basarsa kategorileri okuyabileceği ReadCardActivitye yönlendirilir



                Intent intenttoReadCard = new Intent(MainMenuActivity.this,ReadCardsActivity.class);
                startActivity(intenttoReadCard);
            }
        });

        binding.createCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Createcardview'e basarsa kategori ekleyebileceği ekrana gönderir

                Toast.makeText(MainMenuActivity.this,"create",Toast.LENGTH_LONG).show();
            }
        });

        binding.updateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updatecardview'e basarsa kategorileri düzenleyebileceği ekrana gönderir

                Toast.makeText(MainMenuActivity.this,"update",Toast.LENGTH_LONG).show();
            }
        });

        binding.deleteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deletecardview'e basarsa kategori silebileceği ekrana gönderir

                Toast.makeText(MainMenuActivity.this,"delete",Toast.LENGTH_LONG).show();
            }
        });
    }
}