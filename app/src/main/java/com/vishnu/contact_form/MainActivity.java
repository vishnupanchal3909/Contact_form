package com.vishnu.contact_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    RadioGroup Rg;
    RadioButton F;
    Button save,Delete;
    TextInputEditText Username,UserAddress,UserRegistrtionFees,UserExamFess,UserMailID,UserCollegeFees,UserTotalFees;
    ///For realtime Database
    DatabaseReference databaseReference;
    // screen ready or not
    boolean isandroidReady=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ////Splash Screen Using API
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        androidx.core.splashscreen.SplashScreen splashScreen=androidx.core.splashscreen.SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //For Date
        dateView = (TextView) findViewById(R.id.showdate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        //For Gender selection
        Rg=findViewById(R.id.RadioGroup);

        //Username,UserAddress,UserRegistrtionFees,UserExamFess,UserMailID,UserCollegeFees,UserTotalFees;
        Username=findViewById(R.id.NameOfUser);
        UserAddress=findViewById(R.id.userAddress);
        UserRegistrtionFees=findViewById(R.id.userRegistrationFees);
        UserExamFess=findViewById(R.id.userExamFees);
        UserMailID=findViewById(R.id.userEmailId);
        UserCollegeFees=findViewById(R.id.usercollegeFees);
        UserTotalFees=findViewById(R.id.userTotalFees);
        //save , Delete;
        save=findViewById(R.id.saveBtn);
        Delete=findViewById(R.id.Show);

        //For RealTime Database
        databaseReference= FirebaseDatabase.getInstance().getReference("UserInformation");

        //Saving Data To Database
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Username,UserAddress,UserRegistrtionFees,UserExamFess,UserMailID,UserCollegeFees,UserTotalFees;
                if(TextUtils.isEmpty(Username.getText().toString())){
                    Username.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserAddress.getText().toString())){
                    UserAddress.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserRegistrtionFees.getText().toString())){
                    UserRegistrtionFees.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserExamFess.getText().toString())){
                    UserExamFess.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserMailID.getText().toString())){
                    UserMailID.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserCollegeFees.getText().toString())){
                    UserCollegeFees.setError("Please enter error");
                }else if(TextUtils.isEmpty(UserTotalFees.getText().toString())){
                    UserTotalFees.setError("Please enter error");
                }else if(TextUtils.isEmpty(F.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Select The Gender...", Toast.LENGTH_SHORT).show();
                } else{
                    String Name=Username.getText().toString();
                    String Address=UserAddress.getText().toString();
                    String RegistrtionFee=UserRegistrtionFees.getText().toString();
                    String ExamFees=UserExamFess.getText().toString();
                    String MailId=UserMailID.getText().toString();
                    String CollegeFees=UserCollegeFees.getText().toString();
                    String TotalFees=UserTotalFees.getText().toString();
                    String Datepic=dateView.getText().toString();
                    String Gender=F.getText().toString();
                    UserInfoClassHelper userInfoClassHelper=new UserInfoClassHelper(Name,Address,
                            RegistrtionFee,ExamFees,MailId,
                            CollegeFees,TotalFees,Datepic,Gender);
                    databaseReference.push().setValue(userInfoClassHelper);
                    Toast.makeText(getApplicationContext(), "Saved....!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Showing Data
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AllDetailsInformation.class);
                startActivity(intent);
            }
        });

    }


    public void setDate(View view) {
        showDialog(999);
//        Toast.makeText(getApplicationContext(), "ca",
//                Toast.LENGTH_SHORT)
//                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void Function(View view) {
        int radioId=Rg.getCheckedRadioButtonId();
        F=findViewById(radioId);
//        Toast.makeText(getApplicationContext(), F.getText().toString(), Toast.LENGTH_SHORT).show();

    }
}