package com.lifeapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Questionaire extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);

        final ListView myList = (ListView) findViewById(R.id.listView);

        String[] answers = new String [] {"Vidya Games","Being a Shitter","Being a Baller"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < answers.length; i++) {
            list.add(answers[i]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);


    }

}
