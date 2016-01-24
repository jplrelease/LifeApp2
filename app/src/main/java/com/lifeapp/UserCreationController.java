package com.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserCreationController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button button = (Button)findViewById(R.id.userButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCreationController.this, QuestionaireController.class));
            }
        });
    }
    
    public void clickUsername(View v){
        EditText feild = (EditText) findViewById(R.id.editText);
        feild.setText("");
    }

}
