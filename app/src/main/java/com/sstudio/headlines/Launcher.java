package com.sstudio.headlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sstudio.headlines.everything.BaseFullActivity;
import com.sstudio.headlines.heads.BaseTabActivity;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ((Button)findViewById(R.id.heads)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Launcher.this, BaseTabActivity.class));
            }
        });
        ((Button)findViewById(R.id.full)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Launcher.this, BaseFullActivity.class));
            }
        });
    }
}
