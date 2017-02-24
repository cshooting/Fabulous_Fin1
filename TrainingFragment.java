package fabulous.a511.sports.com.fabulous_fin;

/**
 * Created by shooting on 2017/2/1.
 */

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

//import static com.sports.a511.fabulous_fin.R.id.btn_start;
//import static com.sports.a511.fabulous_fin.R.id.chronometer;

/**
 * Created by keith on 2016/11/19.
 */

public class TrainingFragment extends Fragment {
    private ImageButton mbtn_girl;
    private ImageButton mbtn_boy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mtab_training, container, false);
        mbtn_girl = (ImageButton) v.findViewById(R.id.btn_girl);
        mbtn_boy = (ImageButton) v.findViewById(R.id.btn_boy);
        mbtn_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrainingActivity.class);
                startActivity(intent);
            }
        });
        mbtn_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrainingActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}