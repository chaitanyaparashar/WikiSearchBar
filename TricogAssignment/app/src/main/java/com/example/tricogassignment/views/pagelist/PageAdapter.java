package com.example.tricogassignment.views.pagelist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tricogassignment.R;
import com.example.tricogassignment.api.model.PagesItem;
import com.example.tricogassignment.views.wikipage.WikiPage;

import java.util.List;

/**
 * Created by Mayur on 6/25/2017.
 */

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {

    Context context;
    private List<PagesItem> pageList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImg;
        TextView titletxt, descriptiontxt;

        MyViewHolder(View view) {
            super(view);
            articleImg = (ImageView) view.findViewById(R.id.article_img);
            titletxt = (TextView) view.findViewById(R.id.titletxt);
            descriptiontxt = (TextView) view.findViewById(R.id.artist_name_and_genre);
        }
    }

    PageAdapter(Context context, List<PagesItem> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PagesItem pageItem = pageList.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        if(pageItem.getThumbnail() !=null){
            Glide.with(context)
                    .applyDefaultRequestOptions(options)
                    .load(pageItem.getThumbnail().getSource())
                    .into(holder.articleImg);
        }
        else{
            Glide.with(context)
                    .applyDefaultRequestOptions(options)
                    .load(R.drawable.ic_placeholder)
                    .into(holder.articleImg);
        }


        holder.titletxt.setText(pageItem.getTitle());
        holder.descriptiontxt.setText(pageItem.getTerms().getDescription().get(0));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(context, WikiPage.class);
                    i.putExtra("id", String.valueOf(pageItem.getPageid()));
                    context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }

}