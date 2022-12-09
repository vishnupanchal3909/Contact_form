package com.vishnu.contact_form;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> {
    Context context;
    ArrayList<userInfoData> userInfoDataArrayList;

    public UserInfoAdapter(Context context, ArrayList<userInfoData> userInfoDataArrayList) {
        this.context = context;
        this.userInfoDataArrayList = userInfoDataArrayList;
    }

    @NonNull
    @Override
    public UserInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.userinfotheme,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdapter.MyViewHolder holder, int position) {
        userInfoData user=userInfoDataArrayList.get(position);
        holder.name.setText(user.getName());
        holder.address.setText(user.getAddress());
        holder.registrtionFee.setText(user.getGender());
        holder.ExamFees.setText(user.getDate());
        holder.emailId.setText(user.getEmailId());
        holder.collegeFee.setText(user.getCollegeFee());
        holder.totalFees.setText(user.getTotalFees());
    }

    @Override
    public int getItemCount() {
        return userInfoDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,address,registrtionFee,ExamFees,emailId,collegeFee,totalFees,date,gender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//          TextView name,address,registrtionFee,ExamFees,emailId,collegeFee,totalFees,date,gender;
          name=itemView.findViewById(R.id.Rname);
          address=itemView.findViewById(R.id.Raddress);
          registrtionFee=itemView.findViewById(R.id.Rregistrtion);
          ExamFees=itemView.findViewById(R.id.RexamFee);
          emailId=itemView.findViewById(R.id.REmailid);
          collegeFee=itemView.findViewById(R.id.RcollegeFee);
          totalFees=itemView.findViewById(R.id.RtotalFee);
//          date=itemView.findViewById(R.id.);
//          gender=itemView.findViewById(R.id,)
        }
    }
}
