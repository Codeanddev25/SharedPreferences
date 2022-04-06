package com.jindal.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText ed;
    private EditText ed_multi;
    private Button counter;
    private CheckBox remember;

    String name;
    String message;
    boolean isChecked;

    SharedPreferences sharedPreferences;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.ed1);
        ed_multi = findViewById(R.id.ed_multi);
        counter = findViewById(R.id.btn);
        remember = findViewById( R.id.checkBox);

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count +=1;
                counter.setText("" + count);

            }
        });

        retrieveData();
    }



    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public void saveData(){
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name = ed.getText().toString();
        message = ed_multi.getText().toString();
        if(remember.isChecked())
            isChecked = true;
        else
            isChecked = false;

        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString("key name", name);
        editor.putString("key message", message);
        editor.putInt("key count", count);
        editor.putBoolean("key check", isChecked);
        editor.commit();

        Toast.makeText(getApplicationContext(),"your data is saved",Toast.LENGTH_LONG).show();


    }

    public void retrieveData(){
        sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
        name = sharedPreferences.getString("key name", null);
        message = sharedPreferences.getString("key message", null);
        count = sharedPreferences.getInt("key count", 0);
        isChecked = sharedPreferences.getBoolean("key check", false);

        ed.setText(name);
        ed_multi.setText(message);
        counter.setText("" +count);

        if(isChecked)
            remember.setChecked(true);
        else
            remember.setChecked(false);
    }
}