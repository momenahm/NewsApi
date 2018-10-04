package com.example.melshaeir.newsapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.melshaeir.newsapi.Model.News;
import com.example.melshaeir.newsapi.R;
import com.example.melshaeir.newsapi.ui.DetailActivity.DetailArticle;
import com.example.melshaeir.newsapi.util.ISO8601DateParser;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class  ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
        ItemClickListner itemClickListner;
        TextView top_article;
        RelativeTimeTextView relativeTimeTextView;
        CircleImageView top_image;
    public ListNewsViewHolder(View itemView) {
        super(itemView);
        top_article  = (TextView)itemView.findViewById(R.id.article_title);
        top_image   = (CircleImageView) itemView.findViewById(R.id.source_image);
        relativeTimeTextView = (RelativeTimeTextView)itemView.findViewById(R.id.articel_time);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);
    }
}

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {
    private Context context;
    private List<News.Article> list;

    public ListNewsAdapter(Context context, List<News.Article> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_layout, parent, false);
        return new ListNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getUrlToImage()).into(holder.top_image);
        if (list.get(position).getTitle().length() > 65) {
            holder.top_article.setText(list.get(position).getTitle().substring(0, 65) + ".....");
        } else {
            holder.top_article.setText(list.get(position).getTitle());
        }
        Date date = null;
        try {
            date = ISO8601DateParser.parse(list.get(position).getPublishedAt());
            holder.relativeTimeTextView.setReferenceTime(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean islongClick) {
                Intent intent = new Intent(context, DetailArticle.class);
                intent.putExtra("IMageurl",list.get(position).getUrlToImage());
              //  intent.putExtra("webURL", list.get(position).getUrl());
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
