package com.nadirpelletier.TPQuiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.example.quiztest.R;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-05-24
 * Time : 10:57
 */
public class MainActivity extends AppCompatActivity implements Apropos.OnFragmentInteractionListener
{
    private final static int DEMANDE_RETOUR_SCORE = 11;
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String CLE_MEILLEUR_SCORE = "CLE_MEILLEUR_SCORE";
    private TextView textViewMeilleurScore;
    private TextInputEditText textInputNomEditText;
    private int meilleurScore;

    private FrameLayout conteneurFragment;
    private Button buttonApropos;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        Button buttonDebuter = findViewById(R.id.button_debuter);
        buttonDebuter.animate().rotation(5);

        textViewMeilleurScore = findViewById(R.id.text_Meilleur_Score);
        getMeilleurScore();

        conteneurFragment = (FrameLayout) findViewById(R.id.conteneurFragment);
        buttonApropos = (Button) findViewById(R.id.buttonApropos);


    }

    public void debutQuiz(View view)
    {
        Switch switchQuiz = findViewById(R.id.switchQuizImage);
        textInputNomEditText = findViewById(R.id.textInputNomEditText);

        if (!textInputNomEditText.getText().toString().isEmpty())
        {
            if (switchQuiz.isChecked())
            {
                CoinMeme();
            } else
            {
                QuizTraditionnel(textInputNomEditText.getText().toString());
            }
        } else
        {
            Toast toast = Toast.makeText(this, "Entre ton nom !", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void QuizTraditionnel(String nom)
    {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("nom", nom);
        startActivityForResult(intent, DEMANDE_RETOUR_SCORE);
    }

    public void CoinMeme()
    {
        Intent intent = new Intent(MainActivity.this, CoinMeme.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DEMANDE_RETOUR_SCORE)
        {
            if (resultCode == RESULT_OK)
            {
                int scoreTemp = data.getIntExtra(QuizActivity.MEILLEUR_SCORE, 0);
                if (scoreTemp > meilleurScore)
                {
                    majMeilleurScore(scoreTemp);

                }
            }
        }
    }

    private void getMeilleurScore()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        meilleurScore = sharedPreferences.getInt(CLE_MEILLEUR_SCORE, 0);
        textViewMeilleurScore.setText("Meilleur Score : " + meilleurScore);
    }

    private void majMeilleurScore(int nouveauMeilleurScore)
    {
        meilleurScore = nouveauMeilleurScore;
        textViewMeilleurScore.setText("Meilleur Score : " + meilleurScore);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editorSP = sharedPreferences.edit();
        editorSP.putInt(CLE_MEILLEUR_SCORE, meilleurScore);
        editorSP.apply();
    }

    public void aPropos(View view)
    {
        textInputNomEditText = findViewById(R.id.textInputNomEditText);
        if (!textInputNomEditText.getText().toString().isEmpty())
        {
            ouvrirFragmentApropos();
        }
        else
            {
                Toast toast = Toast.makeText(this, "Entre ton nom !", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

    }

    @SuppressLint("ResourceType")
    public void ouvrirFragmentApropos()
    {
        Apropos fragmentApropos = Apropos.newInstance( textInputNomEditText.getText().toString());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.xml.entrer_vers_la_droite,R.xml.sortie_vers_la_droite,R.xml.entrer_vers_la_droite,R.xml.sortie_vers_la_droite);
        transaction.addToBackStack(null);
        transaction.add(R.id.conteneurFragment,fragmentApropos,"FRAGMENT_APROPOS").commit();

    }

    @Override
    public void onFragmentInteraction()
    {
        onBackPressed();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }
}
