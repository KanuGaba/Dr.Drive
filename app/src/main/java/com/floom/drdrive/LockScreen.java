package com.floom.drdrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 2017kgaba on 2/2/2016.
 */
public class LockScreen extends Activity implements View.OnClickListener{
    private EditText pass;
    private Button login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) this.findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == login) {
            if (pass.getText().toString().equals("123")) ;
            Intent lockIntent = new Intent(this, MainActivity.class);
            this.startActivity(lockIntent);
        }
            else {
                Intent startHomescreen=new Intent(Intent.ACTION_MAIN);
                startHomescreen.addCategory(Intent.CATEGORY_HOME);
                startHomescreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(startHomescreen);
            }
        }
    }