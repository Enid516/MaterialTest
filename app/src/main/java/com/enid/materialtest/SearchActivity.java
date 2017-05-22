package com.enid.materialtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SearchView的使用
 * Created by Enid on 2017/5/22.
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setTextColor(Color.WHITE);
        mSearchAutoComplete.setHintTextColor(Color.parseColor("#bbbbbb"));
        searchViewSetting();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected() " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                //点击home返回按钮， searchView search 状态显示时，通过反射关闭search View
                //SearchView 为关闭界面时，关闭界面（finish）
                if (mSearchAutoComplete.isShown()) {
                    mSearchAutoComplete.setText("");
                    try {
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 这里还有不太明白
     *  mSearchView.setIconified(true);//默认不打开搜索界面，搜索界面左边不显示搜索图标（true）,搜索框右侧总是显示叉叉图标
     *  mSearchView.setIconifiedByDefault(false);//默认打开搜索界面，搜索界面左边显示搜索图标,有文字时搜索框右侧显示叉叉图标，没有文字时不显示
     *  mSearchView.onActionViewExpanded();     // 默认打开搜索界面，搜索框左侧不显示搜索图标，有文字时搜索框右侧显示叉叉图标，没有文字时不显示
     */
    private void searchViewSetting() {
        //TODO
        //SearchView有文字时显示叉叉图标，没有文字时隐藏叉叉图标
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);//进入时SearchView不打开查询界面
//        mSearchView.setIconifiedByDefault(true);

        mSearchView.setQueryHint("请输入查找关键字");
        mSearchView.setSubmitButtonEnabled(true);

        //打开搜索界面监听
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick()");
            }
        });
        //关闭搜索界面监听
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d(TAG, "onClose()");

                return false;
            }
        });
        //搜索关键字 提交/ 改变监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit()");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange()");
                return false;
            }
        });
    }
}
