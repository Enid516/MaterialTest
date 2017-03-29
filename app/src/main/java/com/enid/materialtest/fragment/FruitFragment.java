package com.enid.materialtest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enid.materialtest.R;
import com.enid.materialtest.adapter.FruitAdapter;
import com.enid.materialtest.model.FruitModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *一个带有下拉刷新功能的Fragment，用来展示水果内容为主的图片
 * 布局文件只有一个SwipeRefreshLayout包裹着RecyclerView
 * Created by enid on 2017/1/12.
 */

public class FruitFragment extends Fragment{
    private SwipeRefreshLayout swipeRefreshLayout;
    private FruitModel[] fruitDataArray;
    private List<FruitModel> fruitModels;
    private FruitAdapter fruitAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                initFruits();
                fruitAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_fruit,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //swipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.cardview_dark_background);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        //card view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        fruitModels = new ArrayList<>();
        fruitDataArray = getFruitDataArray();
        initFruits();
        fruitAdapter = new FruitAdapter(fruitModels);
        recyclerView.setAdapter(fruitAdapter);
    }


    private void initFruits() {
        fruitModels.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(fruitDataArray.length);
            fruitModels.add(fruitDataArray[nextInt]);
        }
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    private FruitModel[] getFruitDataArray() {
        return new FruitModel[]{new FruitModel("Apple", R.drawable.fruit_apple),
                new FruitModel("Banana", R.drawable.fruit_banana),
                new FruitModel("Carambola", R.drawable.fruit_carambola),
                new FruitModel("Durian", R.drawable.fruit_durian),
                new FruitModel("Grape", R.drawable.fruit_grape),
                new FruitModel("HamiMelon", R.drawable.fruit_hami_melon),
                new FruitModel("Kivi", R.drawable.fruit_kivi),
                new FruitModel("Lemon", R.drawable.fruit_lemon),
                new FruitModel("Lime", R.drawable.fruit_lime),
                new FruitModel("Orange", R.drawable.fruit_orange),
                new FruitModel("Passion", R.drawable.fruit_passion),
                new FruitModel("Pawpaw", R.drawable.fruit_pawpaw),
                new FruitModel("Peach", R.drawable.fruit_peach),
                new FruitModel("Pear", R.drawable.fruit_pear),
                new FruitModel("Pineapple", R.drawable.fruit_pineapple),
                new FruitModel("Strawberry", R.drawable.fruit_strawberry),
                new FruitModel("Tomato", R.drawable.fruit_tomato),
                new FruitModel("Watermelon", R.drawable.fruit_watermelon),};
    }
}
