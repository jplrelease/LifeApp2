package com.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserCreationController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        String str = "Insert Username";
        EditText username = (EditText)findViewById(R.id.userText);
        username.setText(str, TextView.BufferType.EDITABLE);

        Button button = (Button)findViewById(R.id.userButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCreationController.this, QuestionaireController.class));
            }
        });
    }
}
