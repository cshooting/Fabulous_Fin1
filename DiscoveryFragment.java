package fabulous.a511.sports.com.fabulous_fin;

/**
 * Created by shooting on 2017/2/1.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class DiscoveryFragment extends Fragment {
    private ImageButton mbtn_dis;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mtab_discovery, container, false);
        mbtn_dis = (ImageButton) v.findViewById(R.id.btn_dis);
        mbtn_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlayVideo.class);
                startActivity(intent);
            }
        });
        return v;

    }
}