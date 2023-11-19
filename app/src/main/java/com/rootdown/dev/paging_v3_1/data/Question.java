package com.rootdown.dev.paging_v3_1.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Question {

    public String title;
    public String body;

    @SerializedName("question_id")
    public String questionId;

    @NonNull
    @Override
    public String toString() {
        return(title);
    }
}