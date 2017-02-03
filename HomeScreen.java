package fabulous.a511.sports.com.fabulous_fin;

import android.app.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HomeScreen extends FragmentActivity implements View.OnClickListener{

    private LinearLayout mTab_Training;
    private LinearLayout mTab_Discovery;
    private LinearLayout mTab_Me;

    private ImageButton ImageBtn_Training;
    private ImageButton ImageBtn_Discovery;
    private ImageButton ImageBtn_Me;

    private Fragment mtab_traninig;
    private Fragment mtab_discovery;
    private Fragment mtab_me;



    private Handler handler  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_home_screen);
        initView();
        initEvent();
        setSelect(0);
    }




    private void initEvent() {
        mTab_Training.setOnClickListener(this);
        mTab_Discovery.setOnClickListener(this);
        mTab_Me.setOnClickListener(this);
    }

    private void initView() {
        mTab_Training = (LinearLayout) findViewById(R.id.tab_training);
        mTab_Discovery = (LinearLayout) findViewById(R.id.tab_discovery);
        mTab_Me = (LinearLayout) findViewById(R.id.tab_me);

        ImageBtn_Training = (ImageButton) findViewById(R.id.ImageBtn_Training);
        ImageBtn_Discovery = (ImageButton) findViewById(R.id.ImageBtn_Discovery);
        ImageBtn_Me = (ImageButton) findViewById(R.id.ImageBtn_Me);

    }

    private void setSelect(int i){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        switch (i){
            case 0:
                if(mtab_traninig == null)
                {
                    mtab_traninig = new TrainingFragment();
                    transaction.add(R.id.id_content,mtab_traninig);
                }
                else
                {
                    transaction.show(mtab_traninig);
                }
                ImageBtn_Training.setImageResource(R.drawable.ico_training_dark);
                break;
            case 1:
                if(mtab_discovery == null)
                {
                    mtab_discovery = new DiscoveryFragment();
                    transaction.add(R.id.id_content,mtab_discovery);
                }
                else
                {
                    transaction.show(mtab_discovery);
                }
                ImageBtn_Discovery.setImageResource(R.drawable.ico_discovery_dark);
                break;
            case 2:
                if(mtab_me == null)
                {
                    mtab_me = new MeFragment();
                    transaction.add(R.id.id_content,mtab_me);
                }
                else
                {
                    transaction.show(mtab_me);
                }
                ImageBtn_Me.setImageResource(R.drawable.ico_me_dark);
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mtab_traninig != null){
            transaction.hide(mtab_traninig);
        }
        if(mtab_discovery != null){
            transaction.hide(mtab_discovery);
        }
        if(mtab_me != null){
            transaction.hide(mtab_me);
        }

    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId())
        {
            case R.id.tab_training:
                setSelect(0);
                break;
            case R.id.tab_discovery:
                setSelect(1);
                break;
            case R.id.tab_me:
                setSelect(2);
                break;
            default:
                break;
        }

    }

    private void resetImgs() {
        ImageBtn_Training.setImageResource(R.drawable.ico_training);
        ImageBtn_Discovery.setImageResource(R.drawable.ico_discovery);
        ImageBtn_Me.setImageResource(R.drawable.ico_me);
    }


}