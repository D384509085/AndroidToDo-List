package enbledu.todolist.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import enbledu.todolist.R;
import enbledu.todolist.adapter.MyFragmentAdapter;
import enbledu.todolist.fragment.FragmentChart;
import enbledu.todolist.fragment.FragmentTodoList;
import enbledu.todolist.helper.DepthPageTransformer;
import enbledu.todolist.helper.SortHelper;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private FragmentTodoList fragmentTodoList;
    private Toolbar mToolbar;
    private ViewPager mvViewPager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);


        initViewPage();
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        mToolbar.setTitle(getString(R.string.app_name));//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题

        mToolbar.inflateMenu(R.menu.menu_main);//设置右上角的填充菜单

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.menu1) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    startActivity(intent);
                } else if (menuItemId == R.id.menu2) {
                    SortHelper.save("时间", MainActivity.this);
                    fragmentTodoList.refleshVIew();

                } else if (menuItemId == R.id.menu3) {
                    SortHelper.save("优先级", MainActivity.this);
                    fragmentTodoList.refleshVIew();

                } else if (menuItemId == R.id.menu4) {
                    SortHelper.save("是否完成", MainActivity.this);
                    fragmentTodoList.refleshVIew();


                }
                return true;
            }
        });


    }


    private void initViewPage() {
        fragmentTodoList = new FragmentTodoList(mContext);
        Fragment fragment2 = new FragmentChart(mContext);

        // 将要分页显示的View装入数组中
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(fragmentTodoList);
        list.add(fragment2);
        mvViewPager = (ViewPager) findViewById(R.id.pager);
        mvViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding);
        tabs.setViewPager(mvViewPager);
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);
    }
}
