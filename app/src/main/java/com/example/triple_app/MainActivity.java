package com.example.triple_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendData(String data) {
        InfoFragment fragment = (InfoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.infoFragment);
        if (fragment != null)
            fragment.setSelectedInput(data);
    }
}