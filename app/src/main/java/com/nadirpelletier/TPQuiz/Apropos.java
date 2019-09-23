package com.nadirpelletier.TPQuiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.quiztest.R;

/**
 * Created by Nadir Pelletier
 * For : testQuizz
 * Date : 2019-06-10
 * Time : 12:24
 */
public class Apropos extends Fragment
{

    private static final String NOM_TOAST = "NOM_TOAST";
    private ImageButton imageButtonFragment;
    private TextView textViewAproposNom;
    private String nom;
    private OnFragmentInteractionListener mListener;

    public Apropos()
    {

    }


    public static Apropos newInstance(String nom)
    {
        Apropos fragment = new Apropos();
        Bundle args = new Bundle();
        args.putString(NOM_TOAST, nom);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            nom = getArguments().getString(NOM_TOAST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_apropos, container, false);

        imageButtonFragment=view.findViewById(R.id.imageButtonFragment);
        textViewAproposNom = view.findViewById(R.id.textViewAproposNom);
        textViewAproposNom.setText("SALUT  "+ nom.toUpperCase()+" !");

        imageButtonFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                retourMenu();
            }
        });

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();
        return view;
    }


    public void retourMenu()
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction();
    }
}
