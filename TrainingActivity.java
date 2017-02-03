package fabulous.a511.sports.com.fabulous_fin;

/**
 * Created by shooting on 2017/2/1.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lunger.draglistview.DragListAdapter;
import com.lunger.draglistview.DragListView;

import java.util.ArrayList;

/**
 * Created by shooting on 2017/1/9.
 */

public class TrainingActivity extends AppCompatActivity{
    private Chronometer chronometer;
    private DragListView mDragListView;
    private ArrayList<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_timer);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        //setFormat设置用于显示的格式化字符串。
        //格式化字符串:如果指定，计时器将根据这个字符串来显示，替换字符串中第一个“%s”为当前"MM:SS"或 "H:MM:SS"格式的时间显示。
        chronometer.setFormat("计时：%s");
        findView();
        initData();
        initDragListView();
    }

    private void initDragListView() {
        mDragListView.setDragListAdapter(new MyAdapter(this, mDatas));
        //设置点击item哪个部位可触发拖拽（可不设置，默认是item任意位置长按可拖拽）
        mDragListView.setDragger(R.id.iv_move);
        //设置item悬浮背景色
        mDragListView.setItemFloatColor("#A35151");
        //设置item悬浮透明度
        mDragListView.setItemFloatAlpha(0.65f);
        //设置拖拽响应回调
        mDragListView.setMyDragListener(new DragListView.MyDragListener() {
            @Override
            public void onDragFinish(int srcPositon, int finalPosition) {
                Toast.makeText(TrainingActivity.this, "beginPosition : " + srcPositon + "...endPosition : " + finalPosition, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            mDatas.add("前绕肩×10");
        }
        for(int j=0;j<1;j++){
            mDatas.add("后绕肩×10");
        }
        for(int a=0;a<1;a++){
            mDatas.add("屈膝转体热身×10");
        }
        for(int b=0;b<1;b++){
            mDatas.add("体前屈出拳×10");
        }
        for(int c=0;c<1;c++){
            mDatas.add("拳击站架×10");
        }
        for(int d=0;d<1;d++){
            mDatas.add("前直拳×10");
        }
        for(int e=0;e<1;e++){
            mDatas.add("后直拳×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("前后滑步×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("前后滑步前直拳×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("上步左右直拳×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("侧闪×10");
        }for(int f=0;f<1;f++){
            mDatas.add("前摆拳×10");
        }for(int f=0;f<1;f++){
            mDatas.add("后勾拳×10");
        }

    }

    private void findView() {
        mDragListView = (DragListView) findViewById(R.id.lv);

    }
    class MyAdapter extends DragListAdapter {

        public MyAdapter(Context context, ArrayList<String> arrayTitles) {
            super(context, arrayTitles);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            /***
             * 在这里尽可能每次都进行实例化新的，这样在拖拽ListView的时候不会出现错乱.
             * 具体原因不明，不过这样经过测试，目前没有发现错乱。虽说效率不高，但是做拖拽LisView足够了。
             */
            view = LayoutInflater.from(TrainingActivity.this).inflate(
                    R.layout.drag_list_item, null);

            TextView textView = (TextView) view
                    .findViewById(R.id.tv_name);
            textView.setText(mDatas.get(position));
            return view;
        }
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    /** 开始计时 */
    public void onStart(View view) {
        chronometer.start();
    }

    /** 停止计时 */
    public void onStop(View view) {
        chronometer.stop();
    }

    /** 重置 */
    public void onReset(View view) {
        //setBase 设置基准时间
        //设置参数base为SystemClock.elapsedRealtime()即表示从当前时间开始重新计时）。
        Log.e("dss", "SystemClock.elapsedRealtime()=" + SystemClock.elapsedRealtime());
        chronometer.setBase(SystemClock.elapsedRealtime());

    }
}