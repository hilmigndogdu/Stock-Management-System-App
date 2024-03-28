package com.hilmigndogdu.graduationproject;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hilmigndogdu.graduationproject.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Signupa basarsa hesap oluşturmak için SignupActivitye yönlendiriyor

                Intent intenttoSign = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intenttoSign);
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Login butonuna basarsa bilgiler kontrol edilecek

                if(isValid(binding.phoneEdtTxt.getText().toString(), binding.passwordEdtTxt.getText().toString())){

                    // isValid metoduna girilen bilgileri gönderiyor
                    // Return değeri doğruysa giriş yapılıp MainMenuActivitye yönlendirilecek

                    Intent intenttoMenu = new Intent(LoginActivity.this, ProcessActivity.class);
                    intenttoMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intenttoMenu);

                }else {

                    // Giriş bilgileri yanlışsa toast mesajı ile gösterilecek

                    Toast.makeText(LoginActivity.this, "Phone number or password is incorrect", Toast.LENGTH_SHORT).show();

                }
            }
        });

        binding.forgotTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Kullanıcı şifresini unuttuğu için SaveActivitye yönlendiriyor

                Intent intenttoSave = new Intent(LoginActivity.this, SaveActivity.class);
                startActivity(intenttoSave);
            }
        });

        binding.igButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Kullanıcı instagram hesabı ile giriş yapacak
                // Burayı kodlayamadığım için toast mesajı ile bunu bildiriyorum

                Toast.makeText(LoginActivity.this, "This Action is Unavailable", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean isValid(String phoneInput, String passwordInput) {

        // Veritabanı ile çalışırken string ifadelerle çalıştığımdan dolayı herhangi bir hataya karşın
        // program çökmesin diye try catch içinde yazıyorum

        try {

            // Veritabanını açıp çalıştırıyorum

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("UsersInfo", MODE_PRIVATE, null);

            // Her bir veriye özel olarak erişebilmek için cursor oluşturuyorum

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

            // Verilerin konumlarını indexlere atıyorum

            int idIndex = cursor.getColumnIndex("id");
            int phoneNumberIndex = cursor.getColumnIndex("phoneNumber");
            int passwordIndex = cursor.getColumnIndex("password");

            while (cursor.moveToNext()) {

                // Cursor indexlerine göre bilgileri veri tabanından çekip tanıladığım değişkenlere atıyorum


                String phoneNumber = cursor.getString(phoneNumberIndex);
                String password = cursor.getString(passwordIndex);


                // Atan değerler login ekranında girilen bilgilerle eşleşiyor mu diye kontrol ediyorum

                if (phoneNumber.equals(phoneInput) && password.equals(passwordInput)) {

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