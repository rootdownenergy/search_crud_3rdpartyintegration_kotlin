package com.rootdown.dev.paging_v3_1.db;

import com.rootdown.dev.paging_v3_1.api.Change;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {

    @GET("changes/")
    Call<List<Change>> loadChanges(@Query("q") String status);
}
