package com.example.yiyuangou.choosearea;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by liufeng on 2017/9/7.
 */

public class PickAreaActivity extends Activity implements View.OnClickListener {
    private int NUMBER_PPICKVIEW = 3;
    private TextView titleView;
    private NumberPicker[] numberPicker = new NumberPicker[NUMBER_PPICKVIEW];
    private int chooseIndex;
    private String title;
    private View viewNumber;
    String[] s1 = new String[]{"陕西", "北京", "山东"};
    String[] s11 = new String[]{"xian", "xianyang", "xunyi"};
    String[] s21 = new String[]{"chanping", "chaoyang", "xicheng"};
    String[] s32 = new String[]{"jinan", "qingdao", "yantai"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_pick_area);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initView();
        initPicker();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.sure).setOnClickListener(this);
        titleView = (TextView) findViewById(R.id.title);
        numberPicker[0] = (NumberPicker) findViewById(R.id.number_choose1);
        numberPicker[1] = (NumberPicker) findViewById(R.id.number_choose2);
        numberPicker[2] = (NumberPicker) findViewById(R.id.number_choose);
        viewNumber = findViewById(R.id.number_view);
        setNumberPickerDividerColorAndHeight(numberPicker);
        setNumberPickerTextColor(numberPicker, Color.RED);
        viewNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }


    public void initPicker() {
        for (int i = 0; i < NUMBER_PPICKVIEW; i++) {
            numberPicker[i].setMinValue(0);
            numberPicker[i].setMaxValue(s1.length - 1);
            //  numberPicker[i].setValue(chooseIndex);
            numberPicker[i].setWrapSelectorWheel(true);//是否循环滚动
            numberPicker[i].setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            numberPicker[i].setDisplayedValues(s1);
            numberPicker[i].setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                }
            });
        }
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.back||v.getId()==R.id.number_view) {
//            Intent intent = new Intent(NumberPickerActivity.this, ProductDetailActivity.class);
//            setResult(2, intent);
//            this.finish();
//        } else if (v.getId() == R.id.sure) {
//            Intent intent = new Intent(NumberPickerActivity.this, ProductDetailActivity.class);
//            intent.putExtra(IntentConstants.PARAM_OPTION_SELECT_INDEX, chooseIndex);
//            setResult(2, intent);
//            finish();
//        }
//    }

    private void setNumberPickerDividerColorAndHeight(NumberPicker[] numberPicker) {
        for (int i = 0; i < NUMBER_PPICKVIEW; i++) {
            NumberPicker picker = numberPicker[i];
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        //设置分割线的颜色值
                        pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.ground_color)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
            for (Field pf2 : pickerFields) {
                if (pf2.getName().equals("mSelectionDividerHeight")) {
                    pf2.setAccessible(true);
                    try {
                        int result = 1;
                        pf2.set(picker, result);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
        }
    }

    public boolean setNumberPickerTextColor(NumberPicker[] numberPicker, int color) {
        for (int i = 0; i < NUMBER_PPICKVIEW; i++) {
            final int count = numberPicker[i].getChildCount();
            for (int index = 0; index < count; index++) {
                View child = numberPicker[i].getChildAt(index);
                if (child instanceof EditText) {
                    try {
                        Field selectorWheelPaintField = numberPicker.getClass()
                                .getDeclaredField("mSelectorWheelPaint");
                        selectorWheelPaintField.setAccessible(true);
                        ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                        ((EditText) child).setTextColor(color);
                        numberPicker[i].invalidate();
                        return true;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }


}