package com.example.a16028881_nguyenngocnhien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterMainActivity extends AppCompatActivity {
    EditText edtEmail, edtPass, edtPhone, edtName;
    ImageButton btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtNameRe);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                String phone = edtPhone.getText().toString();
                String name = edtName.getText().toString();
                ApiGmail.apiService.addUser(new User(email, pass, phone, name)).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(RegisterMainActivity.this, "Ban da them thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterMainActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });



    }
}
