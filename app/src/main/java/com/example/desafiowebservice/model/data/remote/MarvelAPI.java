package com.example.desafiowebservice.model.data.remote;

import com.example.desafiowebservice.model.pojo.Hqs.Quadrinhos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelAPI {

        @GET( "comics?" )
        Observable<Quadrinhos> getAllQuadrinhos(
                @Query( "format" ) String format,
                @Query ( "formatType" ) String formatType,
                @Query ( "noVariants" ) boolean noVariants,
                @Query ( "orderBy" ) String orderBy,
                @Query ( "ts" ) String ts,
                @Query ( "hash" ) String hash,
                @Query ( "apikey" ) String apikey);
    }

