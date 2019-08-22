package com.floom.drdrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }
    private void initialize() {

        getBaseContext().getApplicationContext().sendBroadcast(
                new Intent("StartupReceiver_Manual_Start"));
    }
}