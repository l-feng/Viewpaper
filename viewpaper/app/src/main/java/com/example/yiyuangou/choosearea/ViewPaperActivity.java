package com.example.yiyuangou.choosearea;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yiyuangou.choosearea.adapter.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufeng on 2017/9/7.
 */

public class ViewPaperActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView TextHomepage;
    private TextView TextDetails;
    private TextView TextGoodsCar;
    private TextView TextMine;
    private ViewPager viewPager;
    private List<View> views = new ArrayList<View>();
    private TextView viewFirstText;
    private TextView viewSecondText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpaper_activity);
        initView();
    }

    private void initView() {
        TextHomepage = (TextView) findViewById(R.id.tv_textview1);
        TextDetails = (TextView) findViewById(R.id.tv_textview2);
        TextGoodsCar = (TextView) findViewById(R.id.tv_textview3);
        TextMine = (TextView) findViewById(R.id.tv_textview4);
        viewPager = (ViewPager) findViewById(R.id.vp);
        addView();
        ViewPagerAdapter adapter = new ViewPagerAdapter(views, this);
        viewPager.setAdapter(adapter);
        initListenner();
        TextHomepage.setOnClickListener(this);
        TextDetails.setOnClickListener(this);
        TextGoodsCar.setOnClickListener(this);
        TextMine.setOnClickListener(this);
        viewPager.setCurrentItem(0);
        initController();

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_textview1) {
            viewPager.setCurrentItem(0);
        } else if (view.getId() == R.id.tv_textview2) {
            viewPager.setCurrentItem(1);
        } else if (view.getId() == R.id.tv_textview3) {
            viewPager.setCurrentItem(2);
        } else if (view.getId() == R.id.tv_textview4) {
            viewPager.setCurrentItem(3);
        }
    }

    private void addView() {
        View view1 = View.inflate(this, R.layout.view_1, null);
        viewFirstText= (TextView) view1.findViewById(R.id.view_1_tv);
        View view2 = View.inflate(this, R.layout.view_2, null);
        viewSecondText= (TextView) view2.findViewById(R.id.view_2_tv);
        View view3 = View.inflate(this, R.layout.view_3, null);
        View view4 = View.inflate(this, R.layout.view_4, null);
        //把view布局添加到集合
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

    }
     private void initListenner(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        TextHomepage.setBackgroundColor(Color.RED);
                        TextDetails.setBackgroundColor(Color.WHITE);
                        TextGoodsCar.setBackgroundColor(Color.WHITE);
                        TextMine.setBackgroundColor(Color.WHITE);
                        initController();
                        break;
                    case 1:
                        TextHomepage.setBackgroundColor(Color.WHITE);
                        TextDetails.setBackgroundColor(Color.RED);
                        TextGoodsCar.setBackgroundColor(Color.WHITE);
                        TextMine.setBackgroundColor(Color.WHITE);
                        initController();
                        break;
                    case 2:
                        TextHomepage.setBackgroundColor(Color.WHITE);
                        TextDetails.setBackgroundColor(Color.WHITE);
                        TextGoodsCar.setBackgroundColor(Color.RED);
                        TextMine.setBackgroundColor(Color.WHITE);
                        initController();
                        break;
                    case 3:
                        TextHomepage.setBackgroundColor(Color.WHITE);
                        TextDetails.setBackgroundColor(Color.WHITE);
                        TextGoodsCar.setBackgroundColor(Color.WHITE);
                        TextMine.setBackgroundColor(Color.RED);
                        initController();
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
     }


    private void initController(){
        if(viewPager.getCurrentItem()==0){
            Toast.makeText(ViewPaperActivity.this,"lunatic_0",Toast.LENGTH_SHORT).show();
            viewFirstText.setText("change01_01");
        }else if(viewPager.getCurrentItem()==1){
            Toast.makeText(ViewPaperActivity.this,"lunatic_1",Toast.LENGTH_SHORT).show();
            viewSecondText.setText("change02_02");
        }else if(viewPager.getCurrentItem()==2){
            Toast.makeText(ViewPaperActivity.this,"lunatic_2",Toast.LENGTH_SHORT).show();
        }else if(viewPager.getCurrentItem()==3){
            Toast.makeText(ViewPaperActivity.this,"lunatic_3",Toast.LENGTH_SHORT).show();
        }
    }



}
