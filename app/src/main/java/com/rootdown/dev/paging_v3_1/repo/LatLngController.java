package com.rootdown.dev.paging_v3_1.repo;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LatLngController {
    static final String API_KEY = "AIzaSyDxRj1l3HFluAjpxwT7V6fTu_v6GXYGb3c";

    public String resultOut;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String go() throws ExecutionException, InterruptedException {

        Callable<String> callableTask = () -> {
            String result;
            try{
                String xVar = "heading%3D90%3A37.773279%2C-122.468780&";
                String yVar = "37.773245%2C-122.469502";
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url(MessageFormat.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins={0}destinations={1}&mode=driving&key=AIzaSyDxRj1l3HFluAjpxwT7V6fTu_v6GXYGb3c", xVar, yVar))
                        .method("GET", null)
                        .build();
                Response response = client.newCall(request).execute();
                result = response.body().string();
            }catch (IOException e){
                result = e.toString();
            }
            return result;
        };
        Callable<String> callableTask2 = () -> {
            String aVariable = "of herbs";
            String aVar = " is good medicine!";
            String string = MessageFormat.format("A string {0}. {1}", aVariable, aVar);
            return string;
        };

        //Executor service instance
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Callable<String>> tasksList = Collections.singletonList(callableTask);

        //1. execute tasks list using invokeAll() method
        try
        {
            List<Future<String>> results = executor.invokeAll(tasksList);

            for(Future<String> result : results) {
                Log.w("JAVA$$", result.get().toString());
                resultOut = result.get().toString();
                //System.out.println(result.get());
            }
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }
        //Shut down the executor service
        executor.shutdownNow();
        //executor.awaitTermination(1, TimeUnit.HOURS);
        return resultOut;
    }
}
