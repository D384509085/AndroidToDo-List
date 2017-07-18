package enbledu.todolist.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import enbledu.todolist.R;
import enbledu.todolist.adapter.MyFragmentAdapter;
import enbledu.todolist.fragment.FragmentAlarm;
import enbledu.todolist.fragment.FragmentTodoList;
import enbledu.todolist.helper.DepthPageTransformer;

public class MainActivity extends AppCompatActivity {
    ViewPager mvViewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Fragment fragment1 = new FragmentTodoList(mContext);
        Fragment fragment2 = new FragmentAlarm(mContext);
        //Fragment fragment4 = new Fragment();

        // 将要分页显示的View装入数组中
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(fragment1);
        list.add(fragment2);
        mvViewPager=(ViewPager)findViewById(R.id.pager);
        mvViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);

    }
}
