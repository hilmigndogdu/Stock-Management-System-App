package com.hilmigndogdu.graduationproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hilmigndogdu.graduationproject.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Zaten bir hesabı olduğu için LoginActivitiye geri yönlendiriliyor

                Intent intenttoLog = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intenttoLog);
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Veritabanı ile çalışırken string ifadelerle çalıştığımdan dolayı herhangi bir hataya karşın
                // program çökmesin diye try catch içinde yazıyorum

                try {

                    // Edittexte girilen bilgileri değişkenlere atıyorum

                    String name = binding.nameEdtTxt.getText().toString();
                    String phoneNumber = binding.phoneEdtTxt.getText().toString();
                    String password = binding.passwordEdtTxt.getText().toString();

                    // Veritabanını oluşturup çalıştırıyorum

                    database = SignupActivity.this.openOrCreateDatabase("UsersInfo", MODE_PRIVATE, null);

                    // Tablo oluşturup değişkenleri tanıtıyorum

                    database.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, name VARCHAR, phoneNumber VARCHAR,password VARCHAR)");

                    // Veri tabanına bilgileri kaydediyorum

                    String sqlstring = "INSERT INTO users(name, phoneNumber, password) VALUES(?, ?, ?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(sqlstring);
                    sqLiteStatement.bindString(1, name);
                    sqLiteStatement.bindString(2, phoneNumber);
                    sqLiteStatement.bindString(3, password);

                    sqLiteStatement.execute();

                }catch (Exception e){
                    e.printStackTrace();
                }

                // Eğer girilen bilgiler uygunsa yeni hesap oluşturulduysa LoginActiviye yönlendiriyorum

                Toast.makeText(SignupActivity.this,"Account Created",Toast.LENGTH_SHORT).show();

                Intent intenttoLog = new Intent(SignupActivity.this, LoginActivity.class);
                intenttoLog.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intenttoLog);
            }
        });

        binding.igButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Kullanıcı instagram hesabı ile hesap oluşturucak
                // Burayı kodlayamadığım için toast mesajı ile bunu bildiriyorum

                Toast.makeText(SignupActivity.this,"This Action is Unavailable",Toast.LENGTH_LONG).show();
            }
        });

    }
}