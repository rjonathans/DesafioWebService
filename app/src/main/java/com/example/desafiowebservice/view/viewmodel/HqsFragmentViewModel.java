package com.example.desafiowebservice.view.viewmodel;

import androidx.annotation.NonNull;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafiowebservice.model.repository.ComicsRepository;
import com.example.desafiowebservice.model.pojo.Hqs.Quadrinhos;
import com.example.desafiowebservice.model.pojo.Hqs.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.desafiowebservice.util.AppUtils.md5;

/*passo 1 - extender a classe para "AndroiViewModel
passo 2 - "usando o alt + enter": sobrescrever os construtores
passo 3 - criar o atributos: MutableLiveData = que acessa e pode alterar os dados de um Observable
passo 4 - "usando o alt + enter" importe as dependencias <List<"dessa classe">>
passo 5 - "usando o alt + enter" importe as dependencias do <List>
passo 6 - Inicialize o MutableLiveData com "new MutableLiveData<>();"
passo 7 - atribui a chave a um
*/

public class HqsFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> listHqs = new MutableLiveData<>();
    /*private MutableLiveData<Boolean> loading = new MutableLiveData<>();*/
    private CompositeDisposable disposable = new CompositeDisposable();
    private ComicsRepository comicsRepository = new ComicsRepository();

    public static final String PRIVATE_KEY = "fe81c0a4bd6c7f00e3df25d68d8d8a92";

    public static final String PUBLIC_KEY = "ceac13aef2089eaf3c704ba9da60cf2156b60912";

    String ts = Long.toString(System.currentTimeMillis() / 1000);

    String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);

    public HqsFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getListaQuadrinhos(){
        return this.listHqs;
    }

    /*public LiveData<Boolean> getLoading(){
        return this.loading;
    }*/


    public void getComics(){
        disposable.add(
                comicsRepository.getComicsRepository("magazine", "comic", true, "title", ts, hash, PUBLIC_KEY)

                        .subscribeOn(Schedulers.newThread())

                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Consumer<Quadrinhos>() {
                            @Override
                            public void accept(Quadrinhos response) throws Exception {

                                listHqs.setValue(response.getData().getResults());
                            }
                        }, throwable -> {

                            Log.i("LOG", "Error: " + throwable.getMessage());
                        }));
    }
}


