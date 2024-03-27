    package com.hilmigndogdu.graduationproject;

    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteStatement;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import com.hilmigndogdu.graduationproject.databinding.ActivityNewpassBinding;

    public class NewpassActivity extends AppCompatActivity {

        ActivityNewpassBinding binding;

        // Kullanıcının idsini diğer activityden almak için bir sharedprefences tanımladım

        SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityNewpassBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());


            // Sharedpreferencesi çalıştırıyorum

            sharedPreferences = getSharedPreferences("com.hilmigndogdu.graduationproject", MODE_PRIVATE);

            binding.createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Eğer 2 şifre birbiriyle uyuşuyorsa parolayı değiştirebilir ve LoginActivitye yönlendirilir

                    // Girilen bilgileri değişkenlere atıyorum

                    String newpass = binding.passwordEdtTxt.getText().toString();
                    String confirmpass = binding.confirmEdtTxt.getText().toString();

                    if (newpass.equals(confirmpass)){

                        // Girilen şifreler eşleşiyorsa yeni şifreyi veri tabanına atıyorum

                        // Veritabanı ile çalışırken string ifadelerle çalıştığımdan dolayı herhangi bir hataya karşın
                        // program çökmesin diye try catch içinde yazıyorum

                        try {

                            // Kullanıcının idsini alıyorum ve değişkene atıyorum

                            int storedId = sharedPreferences.getInt("storedId", 0);

                            // Veritabanını açıp çalıştırıyorum

                            SQLiteDatabase sqLiteDatabase = NewpassActivity.this.openOrCreateDatabase("UsersInfo",MODE_PRIVATE,null);


                            // Güncellenen şifreyi veri tabanına kaydedebilmek için string oluşturuyom

                            String updateQuery = "UPDATE users SET password = ? WHERE id = ?";

                            // Güncellenen şifreyi veri tabanına atıyorum

                            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(updateQuery);
                            sqLiteStatement.bindString(1, confirmpass);
                            sqLiteStatement.bindString(2, String.valueOf(storedId));

                            // İşlem başarıyla gerçekleşti mi diye kontrol etmek için bir değişkene sonucu atıyorum

                            int rowsAffected = sqLiteStatement.executeUpdateDelete();


                            sqLiteDatabase.close();

                            if (rowsAffected > 0) {

                                // İşlem başarıyla tamamlandıysa LoginActivitye yönlendiriyorum

                                Toast.makeText(NewpassActivity.this,"The password changed",Toast.LENGTH_SHORT).show();

                                Intent intenttoLogin = new Intent(NewpassActivity.this,LoginActivity.class);
                                startActivity(intenttoLogin);
                            } else {

                                // İşlem sırasında hata çıktyısa bildiriyorum

                                Toast.makeText(NewpassActivity.this, "The password not changed", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        // İki şifre eşleşmiyorsa bunu bildiriyorum

                        Toast.makeText(NewpassActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }