package com.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class UserCreationController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }
<<<<<<< HEAD:app/src/main/java/com/lifeapp/UserCreationController.java

        public void andrewClick(View v)
        {
            Intent intent = new Intent(UserCreationController.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }



=======
    public void andrewClick(View v)
    {
        Intent intent = new Intent(newUser.this, Questionaire.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void clickUsername(View v){
        EditText feild = (EditText) findViewById(R.id.editText);
        feild.setText("");
    }
>>>>>>> 68cd033d75a11fcfb4ca0df142aaacc3eec8bfa4:app/src/main/java/com/lifeapp/newUser.java
}
