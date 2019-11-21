package com.example.desafiowebservice.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desafiowebservice.R;
import com.example.desafiowebservice.model.pojo.Hqs.Result;
import com.example.desafiowebservice.view.interfaces.HqsOnClick;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HqsDetailsFragment extends Fragment implements HqsOnClick {
    private static final String COMICS_KEY = "comics";
    private ImageView imgFundo;
    private ImageView imgFrente;
    private TextView txtDescricao;
    private TextView txtPreco;
    private TextView txtPublicacao;
    private TextView txtPaginas;
    private TextView txtTitulo;


    public HqsDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hqs_details, container, false);
        initViews(view);

        validacaoDeDados();

        return view;

    }

    private void validacaoDeDados() {
        if (getArguments() != null) {

            Result result = getArguments().getParcelable(COMICS_KEY);

            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imgFrente);
            Picasso.get().load(result.getImages().get(0).getPath() + ".jpg").into(imgFundo);

            txtDescricao.setText(result.getDescription());
            txtPublicacao.setText(result.getDates().get(0).getDate());
            txtPreco.setText(result.getPrices().get(0).getType());
            txtPaginas.setText(result.getFormat());
            txtTitulo.setText(result.getTitle());

        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerPrincipal, fragment);
        transaction.commit();
    }

    private void initViews(View view) {
        txtDescricao = view.findViewById(R.id.text_details);
        imgFrente = view.findViewById(R.id.imagem_hqs);
        imgFundo = view.findViewById(R.id.imageFundo);
        txtPublicacao = view.findViewById(R.id.text_publicacao);
        txtPreco = view.findViewById(R.id.text_price);
        txtPaginas = view.findViewById(R.id.text_paginas);
        txtTitulo = view.findViewById(R.id.text_titulo_descricao);


    }

    @Override
    public void HqsOnclick(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMICS_KEY, result);

        Fragment detalheFragment = new HqsDetailsFragment();
        detalheFragment.setArguments(bundle);
        replaceFragment(detalheFragment);
    }
}
