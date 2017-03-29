package com.enid.materialtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enid.materialtest.model.FruitModel;

/**
 * Created by enid on 2017/1/3.
 */

public class FruitDetailActivity extends AppCompatActivity {
    private static final String EXTRA_FRUIT_MODEL = "extra_fruit_model";
    private FruitModel fruitModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);//collapsingToolbarView
        ImageView imageView = (ImageView) findViewById(R.id.fruit_image);
        TextView textView = (TextView) findViewById(R.id.fruit_content);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        fruitModel = (FruitModel) intent.getSerializableExtra(EXTRA_FRUIT_MODEL);
        collapsingToolbarLayout.setTitle(fruitModel.getFruitName());

        Glide.with(this).load(fruitModel.getFruitImageId()).into(imageView);
        textView.setText(generateFruit(fruitModel.getFruitName()));
    }

    public static void actionStart(Context context, FruitModel fruitModel) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        intent.putExtra(EXTRA_FRUIT_MODEL, fruitModel);
        context.startActivity(intent);
    }


    private String generateFruit(String fruitName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(fruitName);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
