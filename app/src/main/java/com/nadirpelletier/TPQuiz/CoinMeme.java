package com.nadirpelletier.TPQuiz;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.quiztest.R;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-06-19
 * Time : 11:03
 */
public class CoinMeme extends AppCompatActivity
{
    Button buttonMeme;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_coin_meme);
        buttonMeme = findViewById(R.id.buttonMeme);
    }

    public void clickMeme(View view)
    {
        onBackPressed();
    }
}
