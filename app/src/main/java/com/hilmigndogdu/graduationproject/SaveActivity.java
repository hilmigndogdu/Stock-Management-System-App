package com.hilmigndogdu.graduationproject;

import static android.opengl.ETC1.isValid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivitySaveBinding;

public class SaveActivity extends AppCompatActivity {

    ActivitySaveBinding binding;

    // Kullanıcının idsini diğer activitylere atacak bir sharedprefences tanımladım
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sharedpreferencesi çalıştırıyorum

        sharedPreferences = getSharedPreferences("com.hilmigndogdu.graduationproject", MODE_PRIVATE);

        binding.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check butonuna basarsa bilgiler kontrol edilecek

                if(isValid(binding.phoneEdtTxt.getText().toString(), binding.nameEdtTxt.getText().toString())){

                    // isValid metoduna girilen bilgileri gönderiyor
                    // Return değeri doğruysa giriş yapılıp NewpassActivitye yönlendirilecek

                    Intent intenttopass = new Intent(SaveActivity.this,NewpassActivity.class);
                    intenttopass.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intenttopass);
                }else {

                    // Giriş bilgileri yanlışsa ekrana toast mesajı bastırcak

                    Toast.makeText(SaveActivity.this, "Phone number or name is incorrect", Toast.LENGTH_SHORT).show();

                }

            }
        });

        binding.turnbackTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // LoginActiviye geri dönecek

                Intent intenttoLog = new Intent(SaveActivity.this,LoginActivity.class);
                startActivity(intenttoLog);
            }
        });
    }
    private boolean isValid(String phoneInput, String nameInput) {

        // Veritabanı ile çalışırken string ifadelerle çalıştığımdan dolayı herhangi bir hataya karşın
        // program çökmesin diye try catch içinde yazıyorum

        try {

            // Veritabanını açıp çalıştırıyorum

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("UsersInfo", MODE_PRIVATE, null);

            // Her bir veriye özel olarak erişebilmek için cursor oluşturuyorum

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

            // Verilerin konumlarını indexlere atıyorum

            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int phoneNumberIndex = cursor.getColumnIndex("phoneNumber");


            while (cursor.moveToNext()) {

                // Cursor indexlerine göre bilgileri veri tabanından çekip tanıladığım değişkenlere atıyorum

                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String phoneNumber = cursor.getString(phoneNumberIndex);

                if (phoneNumber.equals(phoneInput) && name.equals(nameInput)) {

                    // Giriş yapılan hesabın idsini sharedPreferencesa atıyorum

                    sharedPreferences.edit().putInt("storedId",id).apply();

                    // Eşleşiyorsa true booleanını geri döndürüyorum

                    cursor.close();
                    return true;
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Eşleşmiyorsa false booleanını geri döndürüyorum

        return false;
    }
}