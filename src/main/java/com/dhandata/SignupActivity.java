package com.dhandata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.OptionalInt;

public class SignupActivity extends AppCompatActivity {

    EditText regname,lname,regmob,regemail,regpass,regpass1;
    Button btn;
    TextView textview;
    String emailPattern="\"[a-z0-9._%+-]+@[gmail.com]";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);



        textview=findViewById(R.id.txtRegLogin); //find button via ID
        regname=findViewById(R.id.txtregname);
        lname=findViewById(R.id.txtreglname);
        regemail=findViewById(R.id.txtRegEmail);
        regmob=findViewById(R.id.txtRegmobile);
        regpass=findViewById(R.id.txtRegPassword);
        regpass1=findViewById(R.id.txtRegPassword1);
        btn=findViewById(R.id.btnRegSignup);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();





        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();


            }
        });
    }

    private void PerforAuth() {
        String firstname=regname.getText().toString();
        String lastname=lname.getText().toString();
        int mobile= regmob.getText().length();
        String email=regemail.getText().toString();
        String password=regpass.getText().toString();
        String confirmPassword=regpass1.getText().toString();
        
        if(email.matches(emailPattern)){
            regemail.setError("Enter Correct Email");
        }
        else if (password.isEmpty() || password.length()<6) {
           regpass.setError("Enter proper password");
        }
        else if (!password.equals(confirmPassword)) {
            regpass1.setError("password not match");
        }
        else if (firstname.isEmpty() || firstname.length()<2) {
            regname.setError("Filled Name ");
        }else if (lastname.isEmpty() || firstname.length()<2) {
            lname.setError("Filled Name ");
        }
        else {
            progressDialog.setMessage("please wait while registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignupActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(SignupActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    private void sendUserToNextActivity() {
        Intent intent =new Intent(SignupActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
