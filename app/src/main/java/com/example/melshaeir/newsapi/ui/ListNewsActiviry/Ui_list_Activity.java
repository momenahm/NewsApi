package com.example.melshaeir.newsapi.ui.ListNewsActiviry;

import com.example.melshaeir.newsapi.Model.News;

import java.util.List;

import retrofit2.Response;

public interface Ui_list_Activity {
    void getArticles(List<News.Article> list);


}
