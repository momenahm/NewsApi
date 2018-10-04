package com.example.melshaeir.newsapi.ui.MainActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.melshaeir.newsapi.Adapter.RecAdapter;
import com.example.melshaeir.newsapi.Model.Sources;
import com.example.melshaeir.newsapi.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ui_view {
      RecyclerView recyclerView;
      RecyclerView.LayoutManager layoutManager;
      RecAdapter recAdapter;
      Ui_Presenter ui_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui_presenter = new Ui_Presenter(getApplicationContext(),this);
        ui_presenter.interfaceData();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerv);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public void getNews(List<Sources> list) {
        list.remove(3);
        recAdapter = new RecAdapter(getApplicationContext(),list);
        recAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recAdapter);
    }
}
