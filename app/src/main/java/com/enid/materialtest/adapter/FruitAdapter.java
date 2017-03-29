package com.enid.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enid.materialtest.FruitDetailActivity;
import com.enid.materialtest.R;
import com.enid.materialtest.model.FruitModel;

import java.util.List;

/**
 * Created by enid on 2017/1/3.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private Context mContext;
    private List<FruitModel> mDatas;

    public FruitAdapter(List<FruitModel> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, null);
        final ViewHolder holder = new ViewHolder(inflate);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                FruitDetailActivity.actionStart(mContext,mDatas.get(position));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FruitModel fruitModel = mDatas.get(position);
        Glide.with(mContext).load(fruitModel.getFruitImageId()).into(holder.imageView);
        holder.textView.setText(fruitModel.getFruitName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.fruit_image);
            textView = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
