package com.example.healthcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edPassword,edConfirmPass,edEmail;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextLTBFullname);
        edPassword=findViewById(R.id.editTextLTBPincode);
        edEmail=findViewById(R.id.editTextLTBAddress);
        edConfirmPass=findViewById(R.id.editTextLTBContact);
        tv=findViewById(R.id.textViewExistingUser);
        btn=findViewById(R.id.buttonLTBBooking);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=edUsername.getText().toString();
                String Email=edEmail.getText().toString();
                String Password=edPassword.getText().toString();
                String ConfirmPass=edConfirmPass.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(Username.length()==0 || Password.length()==0 || ConfirmPass.length()==0 || Email.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill all Details", Toast.LENGTH_SHORT).show();
                } else{
                    if(Password.compareTo(ConfirmPass)==0){
                        if(isvalid(Password)) {
                            if(db.checkuserexists(Username)==1){
                                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                            } else {
                                db.register(Username, Email, Password);
                                Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                            } else {
                                Toast.makeText(getApplicationContext(), "Password must contain 8 character, having letter,digit and special character", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Password and confirm password didn't match!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }
    public static boolean isvalid(String passwordhere) {
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8) {
            return false;
        } else {
            for(int p=0;p<passwordhere.length();p++) {
                if(Character.isLetter(passwordhere.charAt(p))) {
                    f1=1;
                }
            }
            for(int r=0;r<passwordhere.length();r++) {
                if(Character.isDigit(passwordhere.charAt(r))) {
                    f2=1;
                }
            }
            for(int q=0;q<passwordhere.length();q++) {
                char c=passwordhere.charAt(q);
                if(c>=33 && c<=46 || c==64) {
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1) {
                return true;
            } else {
                return false;
            }
        }
    }
}