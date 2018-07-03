package com.example.tienthinh.timesleep.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tienthinh.timesleep.R;
import com.example.tienthinh.timesleep.listener.OnitemClickImageListenner;
import com.example.tienthinh.timesleep.model.Sound;

import java.util.ArrayList;
import java.util.List;

public class SoundAdapter extends BaseAdapter{

    private RadioButton selected =null;
    private MediaPlayer mediaPlayer;

    private OnItemClickListenner listenner;
    private Context context;
    int  layout;
    private List<Sound> soundsList;

    public SoundAdapter(Context context, int layout, List<Sound> soundsList, OnItemClickListenner listenner) {
        this.context = context;
        this.layout = layout;
        this.soundsList = soundsList;
        this.listenner = listenner;
    }

    @Override
    public int getCount() {
        return soundsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview_sound, parent, false);

            holder.txt_name = (TextView) convertView.findViewById(R.id.textView_Name_Sound);
            holder.radioButton = (RadioButton) convertView.findViewById(R.id.radioButton);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onItemClickImage(position);
            }
        });


        holder.txt_name.setText(soundsList.get(position).getTxt_name_sound());
        holder.radioButton.setChecked(soundsList.get(position).isRadioButton());

        holder.radioButton.setClickable(false);

        return convertView;

    }

    public static class ViewHolder {
        public TextView txt_name;
        public RadioButton radioButton;
//        public ImageView img_check;
    }

    public interface OnItemClickListenner {

        void onItemClickImage(int position);
    }

}
