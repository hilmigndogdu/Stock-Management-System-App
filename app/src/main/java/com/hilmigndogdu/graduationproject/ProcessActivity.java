package com.hilmigndogdu.graduationproject;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hilmigndogdu.graduationproject.databinding.ActivityProcessBinding;

import java.util.ArrayList;

public class ProcessActivity extends AppCompatActivity {

    ActivityProcessBinding binding;

    private ArrayList<Cards> cardsArrayList;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProcessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Sayfa ilk açıldığında hiçbir navigationa tıklanmadığı için görünüm bozuk oluyordu.
        // Bunu düzeltmek için fonskiyon tanımladım

        firstProcess();


        //Bu kod parçaları, RecyclerView'in istediğim gibi çalışması için kullanılıyor

        binding.cardsRv.setHasFixedSize(true);




        Cards television = new Cards("televisionimg","Television","Technology","Samsung");
        Cards phone = new Cards("phoneimg","Mobile Phone","Technology","Apple");
        Cards shoe = new Cards("shoeimg","Shoes","Fashion","Nike AirForce");
        Cards ball = new Cards("ballimg","Basketball Ball","Sport","Molten");

        cardsArrayList = new ArrayList<>();

        cardsArrayList.add(television);
        cardsArrayList.add(phone);
        cardsArrayList.add(shoe);
        cardsArrayList.add(ball);

        cardAdapter = new CardAdapter(this,cardsArrayList);
        binding.cardsRv.setAdapter(cardAdapter);

        // Aşağıda bulunan ikonlara basınıldığında işlem gerçekleşmesi için fonksiyon tanımlıyorum
        // Kullanıcı hangi seçeneğe tıklarsa o seçeneğe tanımlı arkaplan, başlık ve tanım yazıları ekrana gelecek

        binding.bottomNavigaitonView.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.readCard) {
                replaceFragment(new CommonFragment());
                binding.getRoot().setBackgroundResource(R.drawable.readcard);
                binding.bottomNavigaitonView.setBackgroundResource(R.drawable.readcard);
                binding.welcomeTxt.setText("Read");
                binding.selectTxt.setText("Select the card you want to show.");
            } else if (menuItem.getItemId() == R.id.createCard) {
                replaceFragment(new CommonFragment());
                binding.getRoot().setBackgroundResource(R.drawable.createcard);
                binding.bottomNavigaitonView.setBackgroundResource(R.drawable.createcard);
                binding.welcomeTxt.setText("Create");
                binding.selectTxt.setText("Select the card you want to add.");
            } else if (menuItem.getItemId() == R.id.updateCard) {
                replaceFragment(new CommonFragment());
                binding.getRoot().setBackgroundResource(R.drawable.updatecard);
                binding.bottomNavigaitonView.setBackgroundResource(R.drawable.updatecard);
                binding.welcomeTxt.setText("Update");
                binding.selectTxt.setText("Select the card you want to edit.");
            } else if (menuItem.getItemId() == R.id.deleteCard) {
                replaceFragment(new CommonFragment());
                binding.getRoot().setBackgroundResource(R.drawable.deletecard);
                binding.bottomNavigaitonView.setBackgroundResource(R.drawable.deletecard);
                binding.welcomeTxt.setText("Delete");
                binding.selectTxt.setText("Select the card you want to remove.");
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
    private void firstProcess(){
        binding.getRoot().setBackgroundResource(R.drawable.readcard);
        binding.bottomNavigaitonView.setBackgroundResource(R.drawable.readcard);
        binding.welcomeTxt.setText("Read");
        binding.selectTxt.setText("Select the card you want to show.");
    }
}