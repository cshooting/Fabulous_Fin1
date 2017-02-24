package fabulous.a511.sports.com.fabulous_fin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.lunger.draglistview.DragListAdapter;
import com.lunger.draglistview.DragListView;

import java.util.ArrayList;

/**
 * Created by shooting on 2017/2/5.
 */

public class TrainingActivityBoy extends AppCompatActivity{
    private Chronometer chronometer1;
    private DragListView mDragListView1;
    private ArrayList<String> mDatas1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_timer_boy);

        Button btnBGM =(Button)findViewById(R.id.music_boy);
        btnBGM.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TrainingActivityBoy.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        chronometer1 = (Chronometer) findViewById(R.id.chronometer);
        //setFormat设置用于显示的格式化字符串。
        //格式化字符串:如果指定，计时器将根据这个字符串来显示，替换字符串中第一个“%s”为当前"MM:SS"或 "H:MM:SS"格式的时间显示。
        chronometer1.setFormat("计时：%s");
        findView();
        initData();
        initDragListView();
    }

    private void initDragListView() {
        mDragListView1.setDragListAdapter(new TrainingActivityBoy.MyAdapter(this, mDatas1));
        //设置点击item哪个部位可触发拖拽（可不设置，默认是item任意位置长按可拖拽）
        mDragListView1.setDragger(R.id.iv_move);
        //设置item悬浮背景色
        mDragListView1.setItemFloatColor("#A35151");
        //设置item悬浮透明度
        mDragListView1.setItemFloatAlpha(0.65f);
        //设置拖拽响应回调
        mDragListView1.setMyDragListener(new DragListView.MyDragListener() {
            @Override
            public void onDragFinish(int srcPositon, int finalPosition) {
                Toast.makeText(TrainingActivityBoy.this, "beginPosition : " + srcPositon + "...endPosition : " + finalPosition, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initData() {
        mDatas1 = new ArrayList<>();
        for(int j=0;j<1;j++){
            mDatas1.add("90°卷腹 1×15");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("仰卧交替抬腿 1×8");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("俄罗斯转体 1×15");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("腹部拉伸 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("上斜俯卧撑 1×18");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("俯卧撑 1×12");
        }
        for(int a=0;a<1;a++){
            mDatas1.add("屈膝转体热身 1×10");
        }
        for(int b=0;b<1;b++){
            mDatas1.add("体前屈出拳 1×10");
        }
        for(int c=0;c<1;c++){
            mDatas1.add("拳击站架 1×10");
        }
        for(int d=0;d<1;d++){
            mDatas1.add("前直拳 1×10");
        }
        for(int e=0;e<1;e++){
            mDatas1.add("后直拳 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("前后滑步 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("前后滑步前直拳 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("上步左右直拳 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("侧闪 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("前摆拳 1×10");
        }
        for(int f=0;f<1;f++){
            mDatas1.add("后勾拳 1×10");
        }


    }

    private void findView() {
        mDragListView1 = (DragListView) findViewById(R.id.lv);

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
            view = LayoutInflater.from(TrainingActivityBoy.this).inflate(
                    R.layout.drag_list_item, null);

            TextView textView = (TextView) view
                    .findViewById(R.id.tv_name);
            textView.setText(mDatas1.get(position));
            return view;
        }
        @Override
        public int getCount() {
            return mDatas1.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    /** 开始计时 */
    public void onStart(View view) {
        chronometer1.start();
    }

    /** 停止计时 */
    public void onStop(View view) {
        chronometer1.stop();
    }

    /** 重置 */
    public void onReset(View view) {
        //setBase 设置基准时间
        //设置参数base为SystemClock.elapsedRealtime()即表示从当前时间开始重新计时）。
        Log.e("dss", "SystemClock.elapsedRealtime()=" + SystemClock.elapsedRealtime());
        chronometer1.setBase(SystemClock.elapsedRealtime());

    }
}
