package fabulous.a511.sports.com.fabulous_fin;

/**
 * Created by shooting on 2017/2/1.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import java.util.HashMap;

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

        Button btnBGM =(Button)findViewById(R.id.music);
        btnBGM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TrainingActivity.this, MusicActivity.class);
                startActivity(intent);
//                finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
            }
        });
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
        for(int j=0;j<1;j++){
            mDatas.add("箱式深蹲 1×20");
        }
        for(int a=0;a<1;a++){
            mDatas.add("臀桥 1×15");
        }
        for(int b=0;b<1;b++){
            mDatas.add("跪姿左侧后踢腿 1×20");
        }
        for(int c=0;c<1;c++){
            mDatas.add("跪姿右侧后踢腿 1×20");
        }
        for(int d=0;d<1;d++){
            mDatas.add("跪姿右侧抬膝 1×20");
        }
        for(int e=0;e<1;e++){
            mDatas.add("跪姿左侧抬膝 1×20");
        }
        for(int f=0;f<1;f++){
            mDatas.add("猫式伸展 1×16");
        }
        for(int f=0;f<1;f++){
            mDatas.add("弓步转体 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("俯卧挺身 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("背部拉伸 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas.add("腹部拉伸 1×8");
        }
        for(int f=0;f<1;f++){
            mDatas.add("左腿根部拉伸 1×8");
        }
        for(int f=0;f<1;f++){
            mDatas.add("右腿根部拉伸 1×8");
        }
        for(int f=0;f<1;f++){
            mDatas.add("单腿仰卧起坐 1×12");
        }
        for(int f=0;f<1;f++){
            mDatas.add("屈膝收腹 1×15");
        }
        for(int f=0;f<1;f++){
            mDatas.add("平板支撑 2分钟");
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