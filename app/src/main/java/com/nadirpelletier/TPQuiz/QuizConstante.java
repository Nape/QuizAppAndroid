package com.nadirpelletier.TPQuiz;

import android.provider.BaseColumns;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-05-20
 * Time : 11:03
 */
public final class QuizConstante
{

    //Sassure que l'on ne pourrat RIEN faire d'autre avec cette classe.
    //Pratique l'orsque l'on travaille en equipe.
    private QuizConstante()
    {
    }
    //BaseColums nous donne un _id
    public static class tableQuestions implements BaseColumns
    {
        public static final String NOM_TABLE = "questions_quiz";
        public static final String COLONE_QUESTION = "question";
        public static final String COLONE_OP1 = "option1";
        public static final String COLONE_OP2 = "option2";
        public static final String COLONE_OP3 = "option3";
        public static final String COLONE_REPONSE_NB = "reponse ";
    }




}
