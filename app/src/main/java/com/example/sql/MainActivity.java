package com.example.sql;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText name,email,mobile;
    TextView data,nodata;
    SQL mysql = new SQL(this);
    String str_name,str_email,str_mobile;

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
        str_name=name.getText().toString();
        str_email=email.getText().toString();
        str_mobile=mobile.getText().toString();
        if(str_name.equals("")||str_email.equals("")||str_mobile.equals("")) {
            Toast.makeText(getApplicationContext(), "No empty values", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.query("information",null,"mobile=?",new String[]{str_mobile},null,null,null,null);

        if(cursor.getCount()==0){
            values.put("name",str_name);
            values.put("email",str_email);
            values.put("mobile",str_mobile);
            db.insert("information",null,values);
            Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_LONG).show();
            db.close();
        }
        else{
            Toast.makeText(getApplicationContext(),"Phone number duplicated",Toast.LENGTH_LONG).show();
        }
    }

    public void read(View view){
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.query("information",null,null,null,null,null,null,null);
        if(cursor.getCount()==0){
            data.setVisibility(View.INVISIBLE);
            nodata.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            cursor.moveToFirst();
            data.setText("name:"+cursor.getString(0)+"\nemail:"+cursor.getString(1)+"\nmobile:"+cursor.getString(2)+"\n");
            data.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.INVISIBLE);
        }
        while(cursor.moveToNext())
            data.append("\nname:"+cursor.getString(0)+"\nemail:"+cursor.getString(1)+"\nmobile:"+cursor.getString(2)+"\n");
        cursor.close();
        db.close();
    }

    public void update(View view){
        str_name=name.getText().toString();
        str_email=email.getText().toString();
        str_mobile=mobile.getText().toString();
        if(str_name.equals("")||str_email.equals("")||str_mobile.equals("")) {
            Toast.makeText(getApplicationContext(), "No empty values", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",str_name);
        values.put("email",str_email);
        values.put("mobile",str_mobile);
        db.update("information",values,"mobile=?",new String[]{str_mobile});
        Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();
        db.close();
    }

    public void remove(View view){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete("information",null,null);
        db.close();
        data.setVisibility(View.INVISIBLE);
        nodata.setVisibility(View.VISIBLE);
    }
}



