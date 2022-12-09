package com.vishnu.contact_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllDetailsInformation extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference;
    ///
    UserInfoAdapter useradapter;
    ArrayList<userInfoData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details_information);

        recyclerView=findViewById(R.id.InformationOfuser);
        reference = FirebaseDatabase.getInstance().getReference("UserInformation");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrayList = new ArrayList<>();
        useradapter = new UserInfoAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(useradapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
//                    userInfoData user = dataSnapshot.getValue(AllDetailsInformation.class);
//                    arrayList.add(user);
                    userInfoData user=dataSnapshot.getValue(userInfoData.class);
                    arrayList.add(user);
                }
                useradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}