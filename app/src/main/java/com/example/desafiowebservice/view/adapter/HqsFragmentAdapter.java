package com.example.desafiowebservice.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiowebservice.R;
import com.example.desafiowebservice.model.pojo.Hqs.Result;
import com.example.desafiowebservice.view.interfaces.HqsOnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HqsFragmentAdapter extends RecyclerView.Adapter <HqsFragmentAdapter.ViewHolder> {

    private List<Result> resultListHqs;
    private HqsOnClick listener;

    public HqsFragmentAdapter(List<Result> resultListHqs, HqsOnClick listener) {
        this.resultListHqs = resultListHqs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result result = resultListHqs.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.HqsOnclick(result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultListHqs.size();
    }

    public void atualizaListaHqs(List<Result> novaLista){
        if (this.resultListHqs.isEmpty()){
            this.resultListHqs = novaLista;
        }else {
            this.resultListHqs.addAll(novaLista);
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;
        private ImageView cartaz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txtTitulo);
            cartaz = itemView.findViewById(R.id.imagem_hqs);

        }

        public void onBind(Result result) {
            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(cartaz);
            titulo.setText(result.getTitle());
        }
    }
}
