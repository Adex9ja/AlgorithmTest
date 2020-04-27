package com.decagon.algorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void test1Debug(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> usernames = FirstAlgorithmTest.getUsernamesSortedByRecordDate(5);
                String s = "";
            }
        }).start();
    }

    public void test2Debug(View view) {
        int noOfWashes = 2;
        int [] cleanPile = new int[]{1, 2, 1, 1};
        int [] dirtyPile = new int[]{1, 4, 3, 2, 4};
        int max = SecondAlgorithmTest.maxPairCleanSocks(noOfWashes, cleanPile, dirtyPile);
        Toast.makeText(this, "The maximum is" + max, Toast.LENGTH_SHORT).show();

    }
}
