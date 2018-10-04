package com.example.melshaeir.newsapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.melshaeir.newsapi.Model.Sources;
import com.example.melshaeir.newsapi.Model.WebSite;

import com.example.melshaeir.newsapi.R;
import com.example.melshaeir.newsapi.ui.ListNewsActiviry.ListNewsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecAdapter extends RecyclerView.Adapter<RecAdapterViewHolder> {
    private Context context;
    private List<Sources>list;
    WebSite webSite;

    public RecAdapter(Context context, List<Sources> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout,parent,false);
        return new RecAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecAdapterViewHolder holder, final int position) {
             holder.source_name.setText(list.get(position).getName());
        Glide.with(context).load(R.mipmap.nas).into(holder.source_image);
             holder.setItemClickListner(new ItemClickListner() {
                 @Override
                 public void onClick(View view, int position, boolean islongClick) {
                     Intent intent =new Intent(context, ListNewsActivity.class);
                     intent.putExtra("sources",list.get(position).getId());
                     intent.putExtra("name",list.get(position).getName());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
             });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecAdapterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
{

    ItemClickListner itemClickListner;
    TextView source_name;
    CircleImageView source_image;

    public RecAdapterViewHolder(View itemView) {
        super(itemView);
        source_image =  (CircleImageView)itemView.findViewById(R.id.source_image);
        source_name  = (TextView)itemView.findViewById(R.id.source_text);
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