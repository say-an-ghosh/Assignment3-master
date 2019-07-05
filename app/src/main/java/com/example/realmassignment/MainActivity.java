package com.example.realmassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private EditText name, dept, roll, age, phone;
    private Switch gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.main_name_et);
        dept = findViewById(R.id.main_dept_et);
        roll = findViewById(R.id.main_roll_et);
        age = findViewById(R.id.main_age_et);
        phone = findViewById(R.id.main_phone_et);
        gender = findViewById(R.id.main_gender_sw);
    }

    public void saveRecord(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try
        {
            Student student = realm.createObject(Student.class, System.currentTimeMillis()/1000);
            student.setName(name.getText().toString());
            student.setDept(dept.getText().toString());
            student.setRoll(Integer.parseInt(roll.getText().toString()));
            student.setAge(Integer.parseInt(age.getText().toString()));
            student.setPhone(phone.getText().toString());
            if(gender.isChecked())
                student.setGender("Female");
            else
                student.setGender("Male");
            realm.commitTransaction();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            realm.cancelTransaction();
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        realm.close();
    }

    public void displayRecord(View view) {
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
