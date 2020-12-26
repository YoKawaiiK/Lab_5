package com.example.lab_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySetToken extends AppCompatActivity {

    private Button b_setToken;
    private EditText et_setToken;
    private String flickr_api_key;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_token);

        b_setToken = findViewById(R.id.b_set_token);
        et_setToken = findViewById(R.id.et_set_token);

        intent = getIntent();

        flickr_api_key = intent.getExtras().getString("flickr_api_key");
        et_setToken.setText(flickr_api_key);

        b_setToken.setOnClickListener(view -> {
            String new_flickr_api_key = et_setToken.getText().toString();
            intent.putExtra("new_flickr_api_key", new_flickr_api_key);
            //Принудительно закрыть текущее окно
            finish();
        });
    }

    @Override
    public void finish(){
        setResult(RESULT_OK, intent);
        super.finish();
    }
}