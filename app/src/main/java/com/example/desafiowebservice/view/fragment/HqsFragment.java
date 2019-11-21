package com.example.desafiowebservice.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafiowebservice.R;
import com.example.desafiowebservice.model.pojo.Hqs.Result;
import com.example.desafiowebservice.view.adapter.HqsFragmentAdapter;
import com.example.desafiowebservice.view.interfaces.HqsOnClick;
import com.example.desafiowebservice.view.viewmodel.HqsFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HqsFragment extends Fragment implements HqsOnClick {

    private List<Result> listResults = new ArrayList<>();
    private HqsFragmentViewModel viewlModelHqs;
    private RecyclerView recyclerViewHqs;
    private HqsFragmentAdapter adapterHqs;

    public static final String COMIC_KEY = "comics";

    public HqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hqs, container, false);

        initViews(view);

        recyclerViewHqs.setAdapter(adapterHqs);
        recyclerViewHqs.setLayoutManager(new GridLayoutManager(getContext(),3));

        viewlModelHqs.getComics();
        viewlModelHqs.getListaQuadrinhos().observe(this, results -> {
            adapterHqs.atualizaListaHqs(results);

        });

        return view;
    }

    private void initViews(View view) {
        recyclerViewHqs = view.findViewById(R.id.recycler_hqs);
        viewlModelHqs = ViewModelProviders.of(this).get(HqsFragmentViewModel.class);
        adapterHqs = new HqsFragmentAdapter(listResults, this);

    }

    @Override
    public void HqsOnclick(Result result) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMIC_KEY, result);

        Fragment hqsDetailsFragment = new HqsDetailsFragment();
        hqsDetailsFragment.setArguments(bundle);
        replaceFragment(hqsDetailsFragment);
    }

    //metodo substituição de fragment colocado na activity que executa a ação
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        assert manager != null;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerPrincipal, fragment);
        transaction.commit();
    }


}

