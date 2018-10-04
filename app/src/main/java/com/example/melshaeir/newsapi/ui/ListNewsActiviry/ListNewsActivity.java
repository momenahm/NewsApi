package com.example.melshaeir.newsapi.ui.ListNewsActiviry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.melshaeir.newsapi.Adapter.ListNewsAdapter;
import com.example.melshaeir.newsapi.Model.News;
import com.example.melshaeir.newsapi.R;
import com.example.melshaeir.newsapi.ui.DetailActivity.DetailArticle;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;

import java.util.List;

public class ListNewsActivity extends AppCompatActivity implements Ui_list_Activity{
    KenBurnsView kbnv;
    DiagonalLayout diagonalLayout;
    TextView top_auther, top_title;
    String source = "", name="";
    ListNewsAdapter listNewsAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Ui_list_presenter presenter;
    String WebtoUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        presenter = new Ui_list_presenter(getApplicationContext(),this);
        source = getIntent().getStringExtra("sources");
        name  =getIntent().getStringExtra("name");
        presenter.interfaceData(source);

        diagonalLayout = (DiagonalLayout)findViewById(R.id.diagonal_layout);
        kbnv = (KenBurnsView)findViewById(R.id.topimage);
        top_auther = (TextView)findViewById(R.id.top_author);
        top_title = (TextView)findViewById(R.id.top_title);
        recyclerView = (RecyclerView)findViewById(R.id.recyclervc);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailArticle.class);
                intent.putExtra("IMageurl",WebtoUrl);
                startActivity(intent);

            }
        });

    }



    @Override
    public void getArticles(List<News.Article> list) {
          if (name.equals(list.get(0).getSource().getName())) {
              Glide.with(getApplicationContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.logonew)).load(list.get(0).getUrlToImage()).into(kbnv);
              top_auther.setText(list.get(0).getAuthor());
              top_title.setText(list.get(0).getTitle());
              WebtoUrl = list.get(0).getUrlToImage();
                 list.remove(0);
              listNewsAdapter = new ListNewsAdapter(getApplicationContext(), list);
              listNewsAdapter.notifyDataSetChanged();
              recyclerView.setAdapter(listNewsAdapter);
          }


    }


}
