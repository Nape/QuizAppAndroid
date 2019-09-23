package com.nadirpelletier.TPQuiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-05-20
 * Time : 10:58
 */
public class QuestionsTroisChoix implements Parcelable
{
    private String Question;
    private String option1;
    private String option2;
    private String option3;
    private int reponse;

    public QuestionsTroisChoix()
    {

    }

    public QuestionsTroisChoix(String question, String option1, String option2, String option3, int reponse)
    {
        Question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.reponse = reponse;
    }

    protected QuestionsTroisChoix(Parcel in)
    {
        Question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        reponse = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(Question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(reponse);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<QuestionsTroisChoix> CREATOR = new Creator<QuestionsTroisChoix>()
    {
        @Override
        public QuestionsTroisChoix createFromParcel(Parcel in)
        {
            return new QuestionsTroisChoix(in);
        }

        @Override
        public QuestionsTroisChoix[] newArray(int size)
        {
            return new QuestionsTroisChoix[size];
        }
    };

    public String getQuestion()
    {
        return Question;
    }

    public void setQuestion(String question)
    {
        Question = question;
    }

    public String getOption1()
    {
        return option1;
    }

    public void setOption1(String option1)
    {
        this.option1 = option1;
    }

    public String getOption2()
    {
        return option2;
    }

    public void setOption2(String option2)
    {
        this.option2 = option2;
    }

    public String getOption3()
    {
        return option3;
    }

    public void setOption3(String option3)
    {
        this.option3 = option3;
    }

    public int getReponse()
    {
        return reponse;
    }

    public void setReponse(int reponse)
    {
        this.reponse = reponse;
    }


}
