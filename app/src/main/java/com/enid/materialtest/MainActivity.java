package com.enid.materialtest;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.enid.materialtest.fragment.FruitFragment;
import com.enid.materialtest.fragment.TabLayoutFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        //set Toolbar
        setSupportActionBar(toolbar);

        //get ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//允许在选择home时在界面上面显示新的一层
            actionBar.setHomeAsUpIndicator(R.drawable.nav_ic_drawer);//给home设置一个图标代替默认的返回图标
        }
//        toolbar.setNavigationIcon(R.drawable.toolbar_add); //也可以替代默认的返回图标
        getNavigation();
        setListener();
        addFragment();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    /**
     * 滑动菜单导航里使用NavigationView
     */
    private void getNavigation() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_item_1);
        ColorStateList colorStateList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorStateList = getResources().getColorStateList(R.color.selector_button_text_color,null);
        }
//        navView.setItemTextColor(colorStateList);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_item_1:
                        toastMsg("click nav item 1");
                        break;
                    case R.id.nav_item_2:
                        toastMsg("click nav item 2");
                        break;
                    case R.id.nav_item_3:
                        toastMsg("click nav item 3");
                        break;
                    case R.id.nav_sub_1:
                        toastMsg("click nav sub item 1");
                        break;
                    case R.id.nav_sub_2:
                        toastMsg("click nav sub item 2");
                        break;
                }
                return true;
            }
        });
    }

    private void setListener() {
        //FloatingActionButton & Snack bar
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "share data to someone", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                toastMsg("share canceled");
                            }
                        })
                        .show();
            }
        });
    }


    /**
     * add Fragment
     */
    private void addFragment() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position % 2 == 0) {
                    return new FruitFragment();
                } else {
                    return new TabLayoutFragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Main " + position;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //set tool bar title
        toolbar.setTitle("Fruit");//setTitle 要在Activity加载完成后设置才有效果
        toolbar.setSubtitle("are favorite fruit");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                toastMsg("click item Add");
                break;
            case R.id.remove_item:
                toastMsg("click item Remove");
                break;
            case R.id.filter_item:
                toastMsg("click item filter");
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    private void toastMsg(String toastMsg) {
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
