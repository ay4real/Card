package com.ay4real.card.remote.request;

import com.ay4real.card.remote.response.FetchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/{digits}")
    Call <FetchResponse> getCardDetails(@Path("digits") int digits);
}
