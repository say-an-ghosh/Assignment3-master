package com.example.realmassignment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private int count = 0;
    private int bColor[] = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
    private RealmResults<Student> mStudentRealmResults;
    private Context mConntext;

    public MyAdapter(RealmResults<Student> students, Context context){
        mStudentRealmResults = students;
        mConntext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(bColor[count%4]);
        count++;
        Student student = mStudentRealmResults.get(position);
        holder.name.setText(student.getName());
        holder.dept.setText(student.getDept());
        holder.phone.setText(holder.phone.getText()+student.getPhone());
        holder.gender.setText(student.getGender());
        holder.age.setText(holder.age.getText()+Integer.toString(student.getAge()));
        holder.roll.setText(holder.roll.getText()+Integer.toString(student.getRoll()));
    }

    @Override
    public int getItemCount() {
        return mStudentRealmResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, dept, roll, age, phone, gender;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
            dept = itemView.findViewById(R.id.dept_tv);
            roll = itemView.findViewById(R.id.roll_tv);
            age = itemView.findViewById(R.id.age_tv);
            phone = itemView.findViewById(R.id.phone_tv);
            gender = itemView.findViewById(R.id.gender_tv);
        }
    }
}
