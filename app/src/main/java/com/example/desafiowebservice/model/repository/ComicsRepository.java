package com.example.desafiowebservice.model.repository;

import io.reactivex.Observable;

import com.example.desafiowebservice.model.pojo.Hqs.Quadrinhos;

import static com.example.desafiowebservice.model.data.remote.Retrofit.getApiService;

public class ComicsRepository {

    public Observable<Quadrinhos> getComicsRepository(String format,
                                                      String formatType,
                                                      Boolean noVariants,
                                                      String orderBy,
                                                      String ts,
                                                      String hash,
                                                      String apikey) {
        return getApiService().getAllQuadrinhos(format,
                                        formatType,
                                        noVariants,
                                        orderBy,
                                        ts, hash, apikey);
    }
}


