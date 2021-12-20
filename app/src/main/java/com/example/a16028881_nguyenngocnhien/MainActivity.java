package com.example.a16028881_nguyenngocnhien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    List<User> mUsers = new ArrayList<>();

    TextView tvReg;
    ImageButton btnLogin;
    EditText edtAccount, adtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button);
        btnLogin = findViewById(R.id.btnLogin);
        edtAccount = findViewById(R.id.edtacc);
        adtPass = findViewById(R.id.edtpass);
        tvReg = findViewById(R.id.tvReg);

        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterMainActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList();
                String acc = edtAccount.getText().toString();
                String pass = adtPass.getText().toString();
                for (User user: mUsers
                ) {
                    if(user.getEmail().equals(acc) && user.getPassword().equals(pass)){
                        Intent intent=  new Intent(MainActivity.this, TransferMainActivity.class);
                        Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }

                }
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void getList(){
        ApiGmail.apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mUsers = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Date date = new Date();

            ApiGmail.apiService.addGmail(new Gmail(personName, personEmail, date+"")).enqueue(new Callback<Gmail>() {
                @Override
                public void onResponse(Call<Gmail> call, Response<Gmail> response) {
                    Toast.makeText(MainActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Gmail> call, Throwable t) {

                }
            });
            startActivity(new Intent(MainActivity.this, TransferMainActivity.class));

        } catch (ApiException e) {

            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}