
package com.example.james.myapplication.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.myapplication.R;


public class ShareFragment2 extends Fragment {

    String TAG = "ShareFragment2";
    private SeekBar seek_bar;
    private TextView text_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting.");
        View view = inflater.inflate(R.layout.fragment_share2, container, false);
        //ButterKnife.bind(this, view);
        seek_bar = view.findViewById(R.id.newpost_seekbar);
        text_view =view.findViewById(R.id.textView);
        //seekbar(seek_bar, text_view);



        return view;
    }

//   @Override
//   public void onViewCreated (View view, Bundle savedInstanceState)
//   {
//      seek_bar = (SeekBar)view.findViewById(R.id.newpost_seekbar);
//      text_view =(TextView)view.findViewById(R.id.textView);
//   }

    public void seekbar( final SeekBar seek_bar, final TextView text_view){
//      seek_bar = (SeekBar)view.findViewById(R.id.newpost_seekbar);
//      text_view =(TextView)view.findViewById(R.id.textView);
//      text_view.setText("Covered : " + seek_bar.getProgress() + " / " +seek_bar.getMax());

        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Covered : " + progress + " / " +seek_bar.getMax());
                        Toast.makeText(getContext(),"SeekBar in progress",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(getContext(),"SeekBar in StartTracking",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_view.setText("Covered : " + progress_value + " / " +seek_bar.getMax());
                        Toast.makeText(getContext(),"SeekBar in StopTracking",Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

}
