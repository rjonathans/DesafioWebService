package com.example.desafiowebservice.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafiowebservice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeroesFragment extends Fragment{


    public HeroesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heroes, container, false);

        initViews(view);
        
        return view;
    }

    private void initViews(View view) {
    }

}
