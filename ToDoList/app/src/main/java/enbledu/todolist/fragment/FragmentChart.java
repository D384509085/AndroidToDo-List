package enbledu.todolist.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import enbledu.todolist.R;
import enbledu.todolist.database.NoteDAOImpl;
import enbledu.todolist.entity.NoteEntity;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class FragmentChart extends Fragment {
    private Context mContext;
    private LayoutInflater inflater;
    private NoteDAOImpl mDaos;
    PieChart mPieChart;
    View mView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        mView = inflater.inflate(R.layout.fragment_chart, container, false);
        mPieChart = (PieChart) mView.findViewById(R.id.pie_chart);
        mPieChart.setUsePercentValues(true);
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setCenterText(getString(R.string.MPstring));
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);
        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);
        mPieChart.setDrawCenterText(true);
        mPieChart.setRotationAngle(0);
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(false);

        // add a selection listener
        // mPieChart.setOnChartValueSelectedListener(this);
        String sortMethod = "";
        ArrayList<NoteEntity> noteDatas = mDaos.getNoteDatas(sortMethod);
        float finishedCount = 0;
        float unFinishedCount = 0;
        float allCount = 0;
        for (NoteEntity noteEntity : noteDatas) {
            if (noteEntity.isFinished()) {
                finishedCount++;
            }
            else {
                unFinishedCount++;
            }
            allCount++;
        }
        TreeMap<String, Float> data = new TreeMap<>();
        data.put(getString(R.string.finished), finishedCount/allCount);
        data.put(getString(R.string.unfinished), unFinishedCount/allCount);
        setData(data);

        // 设置动画
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        // 设置显示的比例
        Legend l = mPieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setData(TreeMap<String, Float> data) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<PieEntry> yVals1 = new ArrayList<PieEntry>();

        int i = 0;
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            // entry的输出结果如key0=value0等
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            float value = (float) entry.getValue();
            xVals.add(key);
            yVals1.add(new PieEntry(value, key, i++));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, getString(R.string.consequece));
        // 设置饼图区块之间的距离
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        // 添加颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        // dataSet.setSelectionShift(0f);

        PieData data1 = new PieData(dataSet);
        data1.setValueFormatter(new PercentFormatter());
        data1.setValueTextSize(10f);
        data1.setValueTextColor(Color.BLACK);
        dataSet.setValueLineColor(Color.BLACK);
        mPieChart.setData(data1);
        // undo all highlights
        mPieChart.highlightValues(null);
        mPieChart.invalidate();
    }


    public FragmentChart(Context mContext) {
        mDaos = new NoteDAOImpl(mContext);
        this.mContext = mContext;
    }

}
