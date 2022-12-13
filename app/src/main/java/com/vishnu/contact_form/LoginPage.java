package com.vishnu.contact_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    TextInputEditText userId,userPassword;
    Button login;
    boolean logingprocess=false;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        userId=findViewById(R.id.userId);
        userPassword=findViewById(R.id.userPassword);
        login=findViewById(R.id.loginUser);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(userId.getText().toString())){
                    userId.setError("Enter user Id");
                }
                else if(TextUtils.isEmpty(userPassword.getText().toString())){
                    userPassword.setError("Please enter password");
                }else{
                    String email=userId.getText().toString();
                    String pass=userPassword.getText().toString();

//                    Firebase Firestore Loging
                    LoginUsingFirestore(email,pass);
//                    CreateUserFirestore(email,pass);
                /*
                    Firebase Authentication
                    CreatingUser(email,pass);
                    LoginUser(email,pass);
                 */


                }
            }
        });
    }

    ///Firebase Firestore Through Loging
    private void LoginUsingFirestore(String Lemail, String Lpass) {
        ///Getting Data
        DocumentReference document= FirebaseFirestore.getInstance().collection("User").document(Lemail);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
              String password=documentSnapshot.getString("password").toString();
              if(password.equals(Lpass)){
                  startActivity(new Intent(getApplicationContext(),Dashboard.class));
                  finish();
              }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Creating Data
                Toast.makeText(getApplicationContext(), "You Enter Wrong Information", Toast.LENGTH_SHORT).show();
                CreateUserFirestore(Lemail,Lpass);
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(getApplicationContext(), "Cancel Session", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CreateUserFirestore(String Cemail, String Cpass) {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        Map<String , Object> information=new HashMap<>();
        information.put("email",Cemail);
        information.put("password",Cpass);
        db.collection("User").document(Cemail).set(information).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Added..", Toast.LENGTH_SHORT).show();
                    LoginUsingFirestore(Cemail,Cpass);
                }
            }
        });
    }

    /////Firebase Authentication Through Login
    private void CreatingUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    LoginUser(email,pass);
                }
                    LoginUser(email,pass);
            }
        });
    }

    private void LoginUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                }else{
                    CreatingUser(email,pass);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null){
//            startActivity(new Intent(getApplicationContext(),Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        }
//        if(logingprocess==true){
//            startActivity(new Intent(getApplicationContext(),Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        }
    }
}