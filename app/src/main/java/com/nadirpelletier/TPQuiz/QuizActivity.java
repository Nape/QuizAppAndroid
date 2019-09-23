package com.nadirpelletier.TPQuiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.quiztest.R;

import java.util.*;
/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-05-20
 * Time : 11:03
 */
public class QuizActivity extends AppCompatActivity
{
    //POUR MAIN_ACTIVITY (ON RESULT)
    public static final String MEILLEUR_SCORE = "MEILLEUR_SCORE";

    //A LA ROTATION !
    public static final String CLE_SCORE = "CLE_SCORE";
    public static final String CLE_NB_QUESTION_RESTANT = "CLE_NB_QUESTION_RESTANT";
    public static final String CLE_NB_QUESTION_TOTAL = "CLE_NB_QUESTION_TOTAL";
    public static final String CLE_REPONDU = "CLE_REPONDU";
    public static final String CLE_LINKEDLIST_QUESTIONS = "CLE_LINKEDLIST_QUESTIONS";
    public static final String CLE_TEMP_RESTANT = "CLE_TEMP_RESTANT";


    //POUR THIS
    private TextView textViewQuestion, textViewScore, textViewNbQuestion, textViewCompteur, textViewNomJoueur;
    private RadioGroup groupeRadio;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Button buttonConfirmer;

    private LinkedList<QuestionsTroisChoix> linkedListQuestions;

    private ColorStateList radioButtonCouleur;
    private int cmptNbQuestions;
    private int nbQuestionsTotal;
    private QuestionsTroisChoix questionCourante;
    private int score;
    private Boolean repondu;
    private Long buttonTriangleAppuyer = Long.valueOf(0);

    private static final long COMPTEUR_MILLISECONDES = 15000;
    private ColorStateList minuteurCouleur;
    private CountDownTimer countDownTimer;
    private long tempRestant;


    @SuppressLint("SetTextI18n")
    /**
     * Nom du joueur entré à demarage.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).hide();



        setContentView(R.layout.activity_quiz);
        initQuestions();
        initTextView();
        initRadioEtButton();
        initStatistiques();
        initMinuteur();
        afficherQuestion();

        textViewNomJoueur.setText(getString(R.string.joueur) + Objects.requireNonNull(getIntent().getExtras()).getString("nom"));
        if (savedInstanceState != null)
        {
            countDownTimer.cancel();
            linkedListQuestions = new LinkedList<>(savedInstanceState.<QuestionsTroisChoix>getParcelableArrayList(CLE_LINKEDLIST_QUESTIONS));
            nbQuestionsTotal = savedInstanceState.getInt(CLE_NB_QUESTION_TOTAL);
            cmptNbQuestions = savedInstanceState.getInt(CLE_NB_QUESTION_RESTANT);
            score = savedInstanceState.getInt(CLE_SCORE);
            tempRestant = savedInstanceState.getLong(CLE_TEMP_RESTANT);
            repondu = savedInstanceState.getBoolean(CLE_REPONDU);
            questionCourante = linkedListQuestions.get(cmptNbQuestions - 1);
            if (!repondu)
            {
                demarrerMinuteur();
            } else
            {
                majMinuteurTextView();
                afficherReponse();
            }
        }
        if (cmptNbQuestions == 1)
        {
            Toast toast = Toast.makeText(QuizActivity.this, "\uD83E\uDD11 Bonne réponse +10 pts \uD83E\uDD11\n\n\uD83D\uDC79 Mauvaise réponse -5 pts \uD83D\uDC79", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toast.show();
        }
    }

    private void afficherQuestion()
    {
        for (int i = 0; i < groupeRadio.getChildCount(); i++)
        {
            (groupeRadio.getChildAt(i)).setEnabled(true);
        }
        radioButton1.setTextColor(radioButtonCouleur);
        radioButton2.setTextColor(radioButtonCouleur);
        radioButton3.setTextColor(radioButtonCouleur);
        groupeRadio.clearCheck();

        if (cmptNbQuestions < nbQuestionsTotal)
        {
            questionCourante = linkedListQuestions.get(cmptNbQuestions);
            textViewQuestion.setText(questionCourante.getQuestion());
            radioButton1.setText(questionCourante.getOption1());
            radioButton2.setText(questionCourante.getOption2());
            radioButton3.setText(questionCourante.getOption3());

            cmptNbQuestions++;
            textViewNbQuestion.setText("Question " + cmptNbQuestions + " de " + nbQuestionsTotal);
            repondu = false;
            buttonConfirmer.setText("\uD83E\uDD14Confirmer\uD83E\uDD14");

            tempRestant = this.COMPTEUR_MILLISECONDES;
            demarrerMinuteur();
        } else
        {
            quizTermine();
        }
    }

    public void questionSuivante(View view)
    {
        if (!repondu)
        {
            if (groupeRadio.getCheckedRadioButtonId() != -1)
            {
                verifierReponse();
            } else
            {
                Toast toast = Toast.makeText(QuizActivity.this, "\uD83D\uDE44 Une réponse peut-être \uD83D\uDE44 ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                toast.show();
            }
        }
        else
        {
            afficherQuestion();
        }
    }

    private void verifierReponse()
    {
        repondu = true;
        countDownTimer.cancel();
        RadioButton tempRB = findViewById(groupeRadio.getCheckedRadioButtonId());
        int indRb = groupeRadio.indexOfChild(tempRB) + 1;

        if (questionCourante.getReponse() == indRb)
        {
            score += 10;
            textViewScore.setText("Votre Score " + Integer.toString(score) + " !");
        } else
        {
            score -= 5;
            textViewScore.setText("Votre Score " + Integer.toString(score) + " !");
            Toast toast = Toast.makeText(QuizActivity.this, "NOP !\n\uD83E\uDD23HAHAHA\uD83E\uDD23",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(40);
            messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toast.show();
        }

        afficherReponse();
    }

    public void afficherReponse()
    {
        for (int i = 0; i < groupeRadio.getChildCount(); i++)
        {
            (groupeRadio.getChildAt(i)).setEnabled(false);
        }
        radioButton1.setTextColor(Color.RED);
        radioButton2.setTextColor(Color.RED);
        radioButton3.setTextColor(Color.RED);

        switch (questionCourante.getReponse())
        {
            case 1:
                radioButton1.setTextColor(Color.GREEN);
                break;
            case 2:
                radioButton2.setTextColor(Color.GREEN);
                break;
            case 3:
                radioButton3.setTextColor(Color.GREEN);
                break;
        }
        if (cmptNbQuestions < nbQuestionsTotal)
        {
            buttonConfirmer.setText("\uD83E\uDD21Continuer\uD83E\uDD21");
        }
        else
        {
            buttonConfirmer.setText("\uD83E\uDD75C'est fini !\uD83E\uDD75");
        }
    }

    private void quizTermine()
    {
        Intent meilleurScore = new Intent();
        meilleurScore.putExtra(MEILLEUR_SCORE, score);
        setResult(RESULT_OK, meilleurScore);

        finish();
    }

    private void initTextView()
    {
        textViewQuestion = findViewById(R.id.text_question);
        textViewScore = findViewById(R.id.text_Score_Partie);
        textViewNbQuestion = findViewById(R.id.text_nbQuestion);
        textViewCompteur = findViewById(R.id.text_compteur);
        textViewNomJoueur = findViewById(R.id.textViewNomJoueur);
    }

    private void initRadioEtButton()
    {
        groupeRadio = findViewById(R.id.radio_button_group);
        radioButton1 = findViewById(R.id.radio_button_1);
        radioButton2 = findViewById(R.id.radio_button_2);
        radioButton3 = findViewById(R.id.radio_button_3);

        buttonConfirmer = findViewById(R.id.button_confirmer_question_suivante);

        radioButtonCouleur = radioButton1.getTextColors();
    }

    private void initQuestions()
    {
        QuizBD quizBD = new QuizBD(this);
        linkedListQuestions = quizBD.getQuestions();

        //Melanger les questions.
        Collections.shuffle(linkedListQuestions);
    }

    private void initStatistiques()
    {
        nbQuestionsTotal = linkedListQuestions.size();
    }

    private void initMinuteur()
    {
        minuteurCouleur = textViewCompteur.getTextColors();
    }

    private void demarrerMinuteur()
    {
        countDownTimer = new CountDownTimer(tempRestant, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                tempRestant = millisUntilFinished;
                majMinuteurTextView();
            }

            @Override
            public void onFinish()
            {
                tempRestant = 0;
                majMinuteurTextView();
                verifierReponse();
            }
        }.start();
    }

    private void majMinuteurTextView()
    {
        int min = (int) (tempRestant / 1000 / 60);
        int sec = (int) (tempRestant / 1000 % 60);
        String tempCompletRestant = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
        textViewCompteur.setText(tempCompletRestant);

        if (tempRestant < 5000)
        {
            textViewCompteur.setTextColor(Color.RED);
        } else if (tempRestant < 10000)
        {
            textViewCompteur.setTextColor(Color.YELLOW);
        } else
        {
            textViewCompteur.setTextColor(minuteurCouleur);
        }
    }

    @Override
    public void onBackPressed()
    {
        //Si le temp passé est plus petit que 1,5 seconde on quitte
        if (buttonTriangleAppuyer + 1500 > System.currentTimeMillis())
        {
            quizTermine();
        } else
        {
            Toast.makeText(this, "Appuyer sur triangle pour quitter le quiz", Toast.LENGTH_SHORT).show();
        }
        buttonTriangleAppuyer = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        ArrayList<QuestionsTroisChoix> t = new ArrayList<>();
        t.addAll(linkedListQuestions);

        outState.putInt(CLE_SCORE, score);
        outState.putInt(CLE_NB_QUESTION_RESTANT, cmptNbQuestions);
        outState.putInt(CLE_NB_QUESTION_TOTAL, nbQuestionsTotal);
        outState.putBoolean(CLE_REPONDU, repondu);
        outState.putLong(CLE_TEMP_RESTANT, tempRestant);
        outState.putParcelableArrayList(CLE_LINKEDLIST_QUESTIONS, t);
    }

}