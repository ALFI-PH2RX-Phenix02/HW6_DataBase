package com.example.sql;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    EditText name,email,mobile;
    TextView data,nodata;
    SQL mysql = new SQL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.edit_name);
        email=findViewById(R.id.edit_email);
        mobile=findViewById(R.id.edit_mobile);
        data=findViewById(R.id.t_data);
        nodata=findViewById(R.id.t_nodata);

        data.setVisibility(View.INVISIBLE);
        mysql.getWritableDatabase();
    }


    public void write(View view){
        String str_name=name.getText().toString();
        String str_email=email.getText().toString();
        String str_mobile=mobile.getText().toString();

        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",str_name);
        values.put("email",str_email);
        values.put("mobile",str_mobile);
        long id = db.insert("data",null,values);
        db.close();
    }

    public void read(View view){

    }

    public void update(View view){

    }

    public void remove(View view){

    }
}



