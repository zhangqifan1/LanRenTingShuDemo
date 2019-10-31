package com.as.lanrentingshudemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ocnyang.pagetransformerhelp.cardtransformer.AlphaAndScalePageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private boolean isInit = true;
    private List<ColorInfo> colorList;

    private String path1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=3769695217e3424f18c3d23966ecd4dc&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F04%2F19%2F70e2846ebc02ae10161f25bf7f5461a1.jpg";
    private String path2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532665664&di=9ead9eb8a9fe2af9a01b0dd39f3e41f4&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F05%2F37%2F28%2F475a43591370453.jpg";
    private String path3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613934&di=0be1c6bbf0441bd19ef6d4e3ce799263&imgtype=0&src=http%3A%2F%2Fpic96.nipic.com%2Ffile%2F20160430%2F7036970_215739900000_2.jpg";
    private String path4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=4dd453940f49d9801826e6b820490957&imgtype=0&src=http%3A%2F%2Fpic161.nipic.com%2Ffile%2F20180410%2F26429156_154754410034_2.jpg";
    private String path5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613935&di=39c387012e3d8fa2eef90129eaf83c5c&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121211%2F7031681_170238437383_2.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vp = findViewById(R.id.vp);
        final View viewbg = findViewById(R.id.viewbg);


        colorList = new ArrayList<ColorInfo>() {
            {
                add(new ColorInfo(path1));
                add(new ColorInfo(path2));
                add(new ColorInfo(path3));
                add(new ColorInfo(path4));
                add(new ColorInfo(path5));
            }
        };

        final MyAdapter myAdapter = new MyAdapter(colorList, this);
        vp.setAdapter(myAdapter);

        vp.setPageMargin(40);
//        vp.setPageTransformer(false, new AlphaAndScalePageTransformer());

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return;
                }

                int pos = position % colorList.size();
                int pos1 = (position + 1) % colorList.size();

                int vibrantColor = ColorUtils.blendARGB(myAdapter.getVibrantColor(pos), myAdapter.getVibrantColor(pos1), positionOffset);
                viewbg.setBackgroundColor(vibrantColor);
                System.out.println( " vibrantColor 222`:"+vibrantColor);
            }

            @Override
            public void onPageSelected(final int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setCurrentItem(0);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                int vibrantColor = myAdapter.getVibrantColor(0);
                viewbg.setBackgroundColor(vibrantColor);
            }
        }.execute();


    }
}
