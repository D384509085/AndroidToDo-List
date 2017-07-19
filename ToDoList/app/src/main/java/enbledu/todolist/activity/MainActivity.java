package enbledu.todolist.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import enbledu.todolist.R;
import enbledu.todolist.adapter.MyFragmentAdapter;
import enbledu.todolist.fragment.FragmentAlarm;
import enbledu.todolist.fragment.FragmentTodoList;
import enbledu.todolist.helper.DepthPageTransformer;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mvViewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initViewPage();
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        mToolbar.setTitle(getString(R.string.app_name));//设置主标题
        //toolbar.setSubtitle("Subtitle");//设置子标题

        mToolbar.inflateMenu(R.menu.menu_main);//设置右上角的填充菜单

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.menu1) {
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.menu2) {
                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.menu3) {
                    Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }


    private void initViewPage() {
        Fragment fragment1 = new FragmentTodoList(mContext);
        Fragment fragment2 = new FragmentAlarm(mContext);

        // 将要分页显示的View装入数组中
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(fragment1);
        list.add(fragment2);
        mvViewPager=(ViewPager)findViewById(R.id.pager);
        mvViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding);
        tabs.setViewPager(mvViewPager);
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);
    }
}
