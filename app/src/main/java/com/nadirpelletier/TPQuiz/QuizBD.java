package com.nadirpelletier.TPQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nadirpelletier.TPQuiz.QuizConstante.*;

import java.util.LinkedList;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-05-24
 * Time : 17:35
 */
public class QuizBD extends SQLiteOpenHelper
{
    private static final String NOM_BD = "QuizBD.db";
    private static final int VERSION_BD = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    // REFERENCE BASE DE DONNÉES
    private SQLiteDatabase bd;


    //====================CONSTRUCTEUR====================
    public QuizBD(Context context)
    {
        super(context, NOM_BD, null, VERSION_BD);
    }


    // Appel lors du premiere accès à l'application
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        this.bd = db;

        final String CREATION_TABLE_QUESTIONS_SQL = "CREATE TABLE " +
                                                    tableQuestions.NOM_TABLE +
                                                    " ( " +
                                                        tableQuestions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                        tableQuestions.COLONE_QUESTION + " TEXT, "+
                                                        tableQuestions.COLONE_OP1 + " TEXT, "+
                                                        tableQuestions.COLONE_OP2 + " TEXT, "+
                                                        tableQuestions.COLONE_OP3 + " TEXT, "+
                                                        tableQuestions.COLONE_REPONSE_NB + " INTEGER"+
                                                    ")";
        db.execSQL(CREATION_TABLE_QUESTIONS_SQL);

        remplirTableQuestion();
    }

    // Apellé si nous voulons modifié des eléments de la table;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE+tableQuestions.NOM_TABLE);
        onCreate(db);
    }

    //Fabrique une question et appel ajouter question.
    // REPONSE: A = 1 , B = 2, C = 3
    private void remplirTableQuestion()
    {
//        Questions à Trois Choix.
        QuestionsTroisChoix questions1 = new QuestionsTroisChoix("Un homme peut-il se marier avec la soeur de sa veuve ?","Oui","Oui, mais c'est très mal vu !","Non",3);
        ajouterQuestion(questions1);

        QuestionsTroisChoix questions2 = new QuestionsTroisChoix("Combien d'animaux arrivent à manger avec leur queue ?","Un seul, l'ornithorynque","Une vingtaine d'espèces","Tous ceux qui en ont une",3);
        ajouterQuestion(questions2);

        QuestionsTroisChoix questions3 = new QuestionsTroisChoix("Un Canard pond un oeuf toutes les 2 heures. Combien de temps va t-il prendre pour pondre 3 oeufs ?","0 h","2 h","6 h",1);
        ajouterQuestion(questions3);

        QuestionsTroisChoix questions4 = new QuestionsTroisChoix("Un nénuphar, sur un étang, double de taille chaque jour. Au bout de 100 jours, il recouvre entièrement l'étang. En combien de jours en recouvre-t-il la moitié ?","50 jours","99 jours","10 jours",2);
        ajouterQuestion(questions4);

        QuestionsTroisChoix questions5 = new QuestionsTroisChoix("Que récompensent chaque année les Darwin Awards, créés aux États-Unis en 1993 ?","Les façons de mourir les plus idiotes","Les découvertes scientifiques les plus insolites","Les meilleures variétés de pommes",1);
        ajouterQuestion(questions5);

        QuestionsTroisChoix questions6 = new QuestionsTroisChoix("Qu'est ce que c'est un canif ?","Un p'tit fien","Un couteau","lol",1);
        ajouterQuestion(questions6);

        QuestionsTroisChoix questions7 = new QuestionsTroisChoix("Quel célèbre dictateur dirigea l’URSS du milieu des années 1920 à 1953 ?","Lénine","Staline","Poutine",2);
        ajouterQuestion(questions7);

        QuestionsTroisChoix questions8 = new QuestionsTroisChoix("Dans  la phrase 'Le voleur a volé les pommes', où est le sujet ?","En  prison.","Le voleur","Les pommes",1);
        ajouterQuestion(questions8);

        QuestionsTroisChoix questions9 = new QuestionsTroisChoix("Que c'est t-il passé le 25 décembre 1955 ?","Noel","Mort du poete paillasson","Rien",1);
        ajouterQuestion(questions9);

        QuestionsTroisChoix questions10 = new QuestionsTroisChoix("Combien de jours d'anniversaires a une personne qui a vécu 50 ans ?","50","0","1",3);
        ajouterQuestion(questions10);

        QuestionsTroisChoix questions11 = new QuestionsTroisChoix("Le 14 Juillet existe-t-il en Tanzanie ?","oui","non","Tanzanie ?",1);
        ajouterQuestion(questions11);

        QuestionsTroisChoix questions12 = new QuestionsTroisChoix("Je divise 40 par un demi (1/2), je multiplie le tout par 2, et j'enlève 60. Combien me reste-t-il ?","40","80","100",1);
        ajouterQuestion(questions12);

        QuestionsTroisChoix questions13 = new QuestionsTroisChoix("Certains mois de l'année comptent 31 jours. Combien en ont 28 ?","6","12","24",2);
        ajouterQuestion(questions13);

        QuestionsTroisChoix questions14 = new QuestionsTroisChoix("Il y a 10 prunes. Vous en prenez 4. Combien en avez-vous ?","0","4","6",2);
        ajouterQuestion(questions14);

        QuestionsTroisChoix questions15 = new QuestionsTroisChoix("Combien d'animaux de chaque sexe Moïse emmena t-il sur son arche ?","0","2","Tous les animaux qui existent",1);
        ajouterQuestion(questions15);

        QuestionsTroisChoix questions16 = new QuestionsTroisChoix("Combien y a t-il de paires de chaussettes dans une vingtaine ?","80","20","40",2);
        ajouterQuestion(questions16);

        QuestionsTroisChoix questions17 = new QuestionsTroisChoix("Combien de temps a duré la guerre de cent ans ?","30","100","+ de 130 ans",3);
        ajouterQuestion(questions17);

        QuestionsTroisChoix questions18 = new QuestionsTroisChoix("De quel pays proviennent les groseilles chinoises ?","Maroc","Chine","Nouvelle-Zélande",3);
        ajouterQuestion(questions18);

        QuestionsTroisChoix questions19 = new QuestionsTroisChoix("Quel est le prénom du roi George VI ?","Albert","Godefroy","Georges",1);
        ajouterQuestion(questions19);

        QuestionsTroisChoix questions20 = new QuestionsTroisChoix("Il faut 3 minutes pour cuire 1 oeuf à la coque. Combien de temps faut-il pour en cuire 2 ?","4 min","3 min","6 min",2);
        ajouterQuestion(questions20);



    }

    //Ajoute une question à la table et les infos à la table de la BD
    private void ajouterQuestion(QuestionsTroisChoix questionsTroisChoix)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(tableQuestions.COLONE_QUESTION, questionsTroisChoix.getQuestion());
        contentValues.put(tableQuestions.COLONE_OP1, questionsTroisChoix.getOption1());
        contentValues.put(tableQuestions.COLONE_OP2, questionsTroisChoix.getOption2());
        contentValues.put(tableQuestions.COLONE_OP3, questionsTroisChoix.getOption3());
        contentValues.put(tableQuestions.COLONE_REPONSE_NB, questionsTroisChoix.getReponse());

        bd.insert(tableQuestions.NOM_TABLE, null, contentValues);
    }

    public LinkedList<QuestionsTroisChoix> getQuestions()
    {
        LinkedList<QuestionsTroisChoix> linkedListQuestions = new LinkedList<>();
        bd = getReadableDatabase();
        Cursor cursorQuestion = bd.rawQuery("SELECT * FROM "+ tableQuestions.NOM_TABLE,null);

        //Verifie que le curseur n'est pas vide
        if (cursorQuestion.moveToFirst())
        {
            do
            {
                String[] s = cursorQuestion.getColumnNames();
                QuestionsTroisChoix questionsTroisChoix = new QuestionsTroisChoix();

                int i = cursorQuestion.getColumnIndex(tableQuestions._ID);
                questionsTroisChoix.setQuestion(cursorQuestion.getString(cursorQuestion.getColumnIndex(tableQuestions.COLONE_QUESTION)));
                questionsTroisChoix.setOption1(cursorQuestion.getString(cursorQuestion.getColumnIndex(tableQuestions.COLONE_OP1)));
                questionsTroisChoix.setOption2(cursorQuestion.getString(cursorQuestion.getColumnIndex(tableQuestions.COLONE_OP2)));
                questionsTroisChoix.setOption3(cursorQuestion.getString(cursorQuestion.getColumnIndex(tableQuestions.COLONE_OP3)));
                questionsTroisChoix.setReponse(cursorQuestion.getInt(5));

                linkedListQuestions.add(questionsTroisChoix);
            }
            while(cursorQuestion.moveToNext());
        }

        cursorQuestion.close();
        return linkedListQuestions;
    }
}
