package com.dhandata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
     //create button as a object
    EditText lemail,lpass;
    Button btnlogin,btnsingup;
    String emailPattern="\"[a-z0-9._%+-]+@[gmail.com]";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin=findViewById(R.id.btn_login);
        lemail=findViewById(R.id.txtloginemail);
        lpass=findViewById(R.id.txtloginpassword);
        btnsingup = findViewById(R.id.btnsignup);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singup;
                singup= new Intent(MainActivity.this,SignupActivity.class);
                startActivity(singup);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforlogin();
            }
        });


    }

    private void perforlogin() {

        String email = lemail.getText().toString();
        String password = lpass.getText().toString();

        if (email.matches(emailPattern)) {
            lemail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            lpass.setError("Enter proper password");
        } else {
            progressDialog.setMessage("please wait while Login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent =new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);}
}