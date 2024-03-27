package com.hilmigndogdu.graduationproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hilmigndogdu.graduationproject.databinding.ActivityReadCardsBinding;

import java.util.ArrayList;

public class ReadCardsActivity extends AppCompatActivity {

    ActivityReadCardsBinding binding;

    private ArrayList<Cards> cardsArrayList;

    private ReadCardAdapter readAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityReadCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardsRv.setHasFixedSize(true);
        binding.cardsRv.setLayoutManager(new LinearLayoutManager(this));

        Cards television = new Cards("img1", "Television", "Technology","Samsung");
        Cards water = new Cards("img2", "Water", "Drink","Aquafina");

        cardsArrayList = new ArrayList<>();

        cardsArrayList.add(television);
        cardsArrayList.add(water);


        readAdapter = new ReadCardAdapter(ReadCardsActivity.this,cardsArrayList);
        binding.cardsRv.setAdapter(readAdapter);
    }
}