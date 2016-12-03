package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvText= (TextView) findViewById(R.id.tv_test);
        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvText.setText("再次");
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
