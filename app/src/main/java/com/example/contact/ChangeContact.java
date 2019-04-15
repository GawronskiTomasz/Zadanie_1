package com.example.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChangeContact extends AppCompatActivity {
    String[] tabString;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    Button cancel;
    boolean checked = false;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact);
        tabString = getResources().getStringArray(R.array.contacts);
        radio1 = findViewById(R.id.rb1);
        radio1.setText(tabString[0]);
        radio2 = findViewById(R.id.rb2);
        radio2.setText(tabString[1]);
        radio3 = findViewById(R.id.rb3);
        radio3.setText(tabString[2]);
        radio4 = findViewById(R.id.rb4);
        radio4.setText(tabString[3]);
        radio5 = findViewById(R.id.rb5);
        radio5.setText(tabString[4]);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void chooseName(View view)
    {
        checked = ((RadioButton) view).isChecked();

        if(view.getId()==R.id.rb1)
            name = tabString[0];
        if(view.getId()==R.id.rb2)
            name = tabString[1];
        if(view.getId()==R.id.rb3)
            name = tabString[2];
        if(view.getId()==R.id.rb4)
            name = tabString[3];
        if(view.getId()==R.id.rb5)
            name = tabString[4];
            /*switch (view.getId()) {
                case R.id.rb1:
                    name = tabString[0];
                    break;
                case R.id.rb2:
                    name = tabString[1];
                    break;
                case R.id.rb3:
                    name = tabString[2];
                    break;
                case R.id.rb4:
                    name = tabString[3];
                    break;
                case R.id.rb5:
                    name = tabString[4];
                    break;

        }*/
    }

    public void confirm(View view) {
        if(checked) {
            Intent intent = new Intent();
            intent.putExtra("name", name);
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Select contact", Toast.LENGTH_LONG).show();
        }
    }
}
