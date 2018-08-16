package com.example.tienthinh.timesleep;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tienthinh.timesleep.adapters.SoundAdapter;
import com.example.tienthinh.timesleep.models.SharedPreferencesManager;
import com.example.tienthinh.timesleep.models.Sound;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private int positionSound;
    private int i;
    boolean dkT2 = false;
    boolean dkT3 = false;
    boolean dkT4 = false;
    boolean dkT5 = false;
    boolean dkT6 = false;
    boolean dkT7 = false;
    boolean dkCN = false;
    // private TextView txt_xong;
    public static boolean aBooleanT2, aBooleanT3, aBooleanT4, aBooleanT5, aBooleanT6, aBooleanT7, aBooleanCn;
    private Button btn_t2, btn_t3, btn_t4, btn_t5, btn_t6, btn_t7, btn_cn;
    public static TextView txtNhacNho, txtSound;
    private SeekBar seekBar;
    private AudioManager audioManager;
    private boolean aBooleanCheckSleep = false;
    private boolean aBooleanCheck1Hour = false;
    private boolean aBooleanCheck15P = false;
    private boolean aBooleanCheck30P = false;
    private boolean aBooleanCheck5P = false;
    private TextView txt1Hour, txt30P, txt15P, txt5P, txtSleep, txt_Save;
    private boolean doubleBackToExitPressedOnce = false;
    private ImageView iv_comback;
    private Dialog dialog;
    private Dialog dialogSound;
    private LinearLayout ln_nhac_nho,ln_sound;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String SHARED_PREFERENCES_NAME = "sharePreferences";
    private ListView lvSound;
    private ArrayList<Sound> Array_List_sounds;
    private SoundAdapter soundAdapter;
    private MediaPlayer mediaPlayer;
    private Button btn_Save_Sound;
    private TextView tv_title_header;
    private Typeface typeface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textstyle();
        mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.littlecomfort);
        preferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        initView();
        onClickHuy();
        onClickTimeNhacNho();
        onClickTextViewShound();
        onClickSeekBar();
    }

    private void textstyle() {
        typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium_0.ttf");
    }

    private void onClickSeekBar() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                SharedPreferencesManager.setVolume(SettingActivity.this, i);
                if (b==true){
                    playMusic();
                }else {}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void playMusic() {
        positionSound = SharedPreferencesManager.getPositionSound(SettingActivity.this);
        switch (positionSound) {
            case 0:
                createMediaPlayer(R.raw.littlecomfort);
                break;
            case 1:
                createMediaPlayer(R.raw.annieswonderland);
                break;
            case 2:
                createMediaPlayer(R.raw.havana);
                break;
            case 3:
                createMediaPlayer(R.raw.thisgame);
                break;
            case 4:
                createMediaPlayer(R.raw.griefandsorrow);
                break;
            case 5:
                createMediaPlayer(R.raw.frenchkiss);
                break;
            case 6:
                createMediaPlayer(R.raw.beautifulgirl);
                break;
        }
    }

    private void createMediaPlayer(int uri) {
        if (mediaPlayer.isPlaying() == true) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(SettingActivity.this, uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(SettingActivity.this, uri);
            mediaPlayer.start();
        }
    }

    private void onClickTextViewShound() {
        ln_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSound = new Dialog(SettingActivity.this);
                dialogSound.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSound.setCancelable(true);
                dialogSound.setContentView(R.layout.custom_dialog_amthanh);
                dialogSound.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                lvSound = (ListView) dialogSound.findViewById(R.id.listView_AmThanh);
                TextView tv_sound = dialogSound.findViewById(R.id.tv_sound);
                Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium_0.ttf");
                tv_sound.setTypeface(typeface);
                tv_sound.setTextColor(Color.WHITE);
                Array_List_sounds = new ArrayList<Sound>();
                Array_List_sounds.add(new Sound("Little Comfort", false));
                Array_List_sounds.add(new Sound("Annie's Wonderland", false));
                Array_List_sounds.add(new Sound("Havana", false));
                Array_List_sounds.add(new Sound("This Game", false));
                Array_List_sounds.add(new Sound("Grief And Sorrow", false));
                Array_List_sounds.add(new Sound("French Kiss", false));
                Array_List_sounds.add(new Sound("Beautiful Girl", false));
                final SoundAdapter.OnItemClickListenner onItemClickListenner = new SoundAdapter.OnItemClickListenner() {
                    @Override
                    public void onItemClickImage(int position) {
                        switch (position) {
                            case 0:
                                i = position;
                                //SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                //SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.littlecomfort);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.littlecomfort);
                                    mediaPlayer.start();
                                }
                                break;
                            case 1:
                                i = position;
                                // SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                // SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.annieswonderland);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.annieswonderland);
                                    mediaPlayer.start();
                                }

                                break;
                            case 2:
                                i = position;
                                // SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                //  SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.havana);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.havana);
                                    mediaPlayer.start();
                                }

                                break;
                            case 3:
                                i = position;
                                //  SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                // SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.thisgame);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.thisgame);
                                    mediaPlayer.start();
                                }

                                break;
                            case 4:
                                i = position;
                                // SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                // SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.griefandsorrow);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.griefandsorrow);
                                    mediaPlayer.start();
                                }

                                break;
                            case 5:
                                i = position;
                                //SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                // SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.frenchkiss);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.frenchkiss);
                                    mediaPlayer.start();
                                }

                                break;
                            case 6:
                                i = position;
                                //  SharedPreferencesManager.setTenBaiHat(SettingActivity.this, txtSound.getText().toString().trim());
                                //  SharedPreferencesManager.setPosition(SettingActivity.this, position);
                                for (int i = 0; i < Array_List_sounds.size(); i++) {
                                    if (Array_List_sounds.get(i).isRadioButton()) {
                                        Array_List_sounds.get(i).setRadioButton(false);
                                    }
                                }
                                Array_List_sounds.get(position).setRadioButton(true);
                                soundAdapter.notifyDataSetChanged();

                                if (mediaPlayer.isPlaying() == true) {
                                    mediaPlayer.stop();
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.beautifulgirl);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.beautifulgirl);
                                    mediaPlayer.start();
                                }

                                break;
                        }
                    }
                };

                Array_List_sounds.get(SharedPreferencesManager.getPositionSound(SettingActivity.this)).setRadioButton(true);

                soundAdapter = new SoundAdapter(SettingActivity.this, R.layout.item_listview_sound, Array_List_sounds, onItemClickListenner);

                lvSound.setAdapter(soundAdapter);

                dialogSound.show();

                btn_Save_Sound = (Button) dialogSound.findViewById(R.id.btn_Save_Sound);

                btn_Save_Sound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onclickSaveSound();
                    }
                });

                dialogSound.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mediaPlayer.stop();


                    }
                });

            }
        });
    }

    private void onclickSaveSound() {
        SharedPreferencesManager.setPositionSound(SettingActivity.this, i);
        switch (i) {
            case 0:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Little Comfort");
                txtSound.setText("Little Comfort");
                break;
            case 1:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Annie's Wonderland");
                txtSound.setText("Annie's Wonderland");
                break;
            case 2:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Havana");
                txtSound.setText("Havana");
                break;
            case 3:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "This Game");
                txtSound.setText("This Game");
                break;
            case 4:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Grief And Sorrow");
                txtSound.setText("Grief And Sorrow");
                break;
            case 5:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "French Kiss");
                txtSound.setText("French Kiss");
                break;
            case 6:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Beautiful Girl");
                txtSound.setText("Beautiful Girl");
                break;
        }
        dialogSound.cancel();
    }

    private void onClickTimeNhacNho() {
        ln_nhac_nho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(SettingActivity.this);
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.customdialog_nhacnho);
                dialog.show();
                initViewDiaLog();
                DocLaiSharePreferences();
                onClickTxt1Hour();
                onClickTxt5P();
                onClickTxt30P();
                onClickTxt15P();
                onClickTxtSleep();
                onClickSave();


            }
        });

    }

    private void DocLaiSharePreferences() {
        if (SharedPreferencesManager.getSleepNow(SettingActivity.this) == true) {
            txtSleep.setTextColor(getResources().getColor(R.color.textWakeUp));
        } else {
            if (SharedPreferencesManager.get1hour(SettingActivity.this) == true) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textWakeUp));
            } else {
                if (SharedPreferencesManager.get15p(SettingActivity.this) == true) {
                    txt15P.setTextColor(getResources().getColor(R.color.textWakeUp));
                } else {
                    if (SharedPreferencesManager.get5p(SettingActivity.this) == true) {
                        txt5P.setTextColor(getResources().getColor(R.color.textWakeUp));
                    } else {
                        if (SharedPreferencesManager.get30p(SettingActivity.this) == true) {
                            txt30P.setTextColor(getResources().getColor(R.color.textWakeUp));
                        } else {

                        }
                    }
                }
            }

        }
    }

    private void onClickSave() {
        txt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DieuKienSave();
            }
        });
    }

    private void DieuKienSave() {
        if (txtSleep.getCurrentTextColor() == getResources().getColor(R.color.textWakeUp)) {
            txtNhacNho.setText(R.string.at_sleeping);
            SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
            SharedPreferencesManager.setSleepNow(SettingActivity.this, true);
            SharedPreferencesManager.set30p(SettingActivity.this, false);
            SharedPreferencesManager.set5p(SettingActivity.this, false);
            SharedPreferencesManager.set1hour(SettingActivity.this, false);
            SharedPreferencesManager.set15p(SettingActivity.this, false);

            checkTime();

            dialog.cancel();
        } else {
            if (txt5P.getCurrentTextColor() == getResources().getColor(R.color.textWakeUp)) {
                txtNhacNho.setText(R.string.five_minute);
                SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                SharedPreferencesManager.set30p(SettingActivity.this, false);
                SharedPreferencesManager.set5p(SettingActivity.this, true);
                SharedPreferencesManager.set1hour(SettingActivity.this, false);
                SharedPreferencesManager.set15p(SettingActivity.this, false);
                checkTime();
                dialog.cancel();
            } else {
                if (txt15P.getCurrentTextColor() == getResources().getColor(R.color.textWakeUp)){
                    txtNhacNho.setText(R.string.fifteen_minute);
                    SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                    SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                    SharedPreferencesManager.set30p(SettingActivity.this, false);
                    SharedPreferencesManager.set5p(SettingActivity.this, false);
                    SharedPreferencesManager.set1hour(SettingActivity.this, false);
                    SharedPreferencesManager.set15p(SettingActivity.this, true);
                    checkTime();
                    dialog.cancel();
                } else {
                    if (txt30P.getCurrentTextColor() == getResources().getColor(R.color.textWakeUp)) {
                        txtNhacNho.setText(R.string.thirty_minute);
                        SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                        SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                        SharedPreferencesManager.set30p(SettingActivity.this, true);
                        SharedPreferencesManager.set5p(SettingActivity.this, false);
                        SharedPreferencesManager.set1hour(SettingActivity.this, false);
                        SharedPreferencesManager.set15p(SettingActivity.this, false);
                        checkTime();
                        dialog.cancel();
                    } else {
                        if (txt1Hour.getCurrentTextColor() == getResources().getColor(R.color.textWakeUp)) {
                            txtNhacNho.setText(R.string.one_hour);
                            SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                            SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                            SharedPreferencesManager.set30p(SettingActivity.this, false);
                            SharedPreferencesManager.set5p(SettingActivity.this, false);
                            SharedPreferencesManager.set1hour(SettingActivity.this, true);
                            SharedPreferencesManager.set15p(SettingActivity.this, false);
                            checkTime();
                            dialog.cancel();
                        } else {
                            Toast.makeText(this, "Bạn Chưa Chọn Thời Gian", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }


        }
    }

    private void checkTime() {
        int hS = SharedPreferencesManager.getHourSleep(this);
        int mS = SharedPreferencesManager.getMinuteSleep(this);
        int hW = SharedPreferencesManager.getHourWakeUp(this);
        int mW = SharedPreferencesManager.getMinuteWakeUp(this);

        if (SharedPreferencesManager.getToggleOnOff(this) == true) {
            if (hS == hW) {
                if (mS == mW) {
                    //Toast.makeText(this, R.string.invalid_data, Toast.LENGTH_SHORT).show();
                } else {
                    sendBroadcastReceiver();
                }
            } else {
                sendBroadcastReceiver();
            }
        }
    }

    private void onClickTxtSleep() {
        txtSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textColor));
                txtSleep.setTextColor(getResources().getColor(R.color.textWakeUp));
                txt30P  .setTextColor(getResources().getColor(R.color.textColor));
                txt15P  .setTextColor(getResources().getColor(R.color.textColor));
                txt5P   .setTextColor(getResources().getColor(R.color.textColor));

            }
        });

    }

    private void onClickTxt15P() {
        txt15P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textColor));
                txtSleep.setTextColor(getResources().getColor(R.color.textColor));
                txt30P  .setTextColor(getResources().getColor(R.color.textColor));
                txt15P  .setTextColor(getResources().getColor(R.color.textWakeUp));
                txt5P   .setTextColor(getResources().getColor(R.color.textColor));
            }
        });

    }

    private void onClickTxt30P() {
        txt30P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textColor));
                txtSleep.setTextColor(getResources().getColor(R.color.textColor));
                txt30P  .setTextColor(getResources().getColor(R.color.textWakeUp));
                txt15P  .setTextColor(getResources().getColor(R.color.textColor));
                txt5P   .setTextColor(getResources().getColor(R.color.textColor));

            }
        });

    }

    private void onClickTxt5P() {
        txt5P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textColor));
                txtSleep.setTextColor(getResources().getColor(R.color.textColor));
                txt30P  .setTextColor(getResources().getColor(R.color.textColor));
                txt15P  .setTextColor(getResources().getColor(R.color.textColor));
                txt5P   .setTextColor(getResources().getColor(R.color.textWakeUp));

            }
        });

    }

    private void onClickTxt1Hour() {
        txt1Hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1Hour.setTextColor(getResources().getColor(R.color.textWakeUp));
                txtSleep.setTextColor(getResources().getColor(R.color.textColor));
                txt30P  .setTextColor(getResources().getColor(R.color.textColor));
                txt15P  .setTextColor(getResources().getColor(R.color.textColor));
                txt5P   .setTextColor(getResources().getColor(R.color.textColor));

            }
        });

    }

    private void initViewDiaLog() {
        TextView tv = dialog.findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium_0.ttf");
        tv.setTypeface(typeface);
        txt_Save = (TextView) dialog.findViewById(R.id.textSave);
        txt1Hour = (TextView) dialog.findViewById(R.id.text1Hour);
        txt5P = (TextView) dialog.findViewById(R.id.textView5P);
        txt15P = (TextView) dialog.findViewById(R.id.textView15P);
        txt30P = (TextView) dialog.findViewById(R.id.textView30P);
        txtSleep = (TextView) dialog.findViewById(R.id.textSleep);


    }

    private void onClickHuy() {
        iv_comback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
                if (mediaPlayer.isPlaying()==true){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });
    }

    private void initView() {
        //txt_xong = findViewById(R.id.txt_xong);

        ln_nhac_nho = findViewById(R.id.ln_nhac_nho);
        ln_sound = findViewById(R.id.ln_sound);
        tv_title_header = findViewById(R.id.title_header);
        tv_title_header.setTypeface(typeface);
        iv_comback = (ImageView) findViewById(R.id.comeback);
        txtNhacNho = (TextView) findViewById(R.id.textViewNhacNho);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Italic.ttf");
        txtNhacNho.setTypeface(typeface);
        txtSound = (TextView) findViewById(R.id.textView_music);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Italic.ttf");
        txtSound.setTypeface(typeface1);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
//        seekBar.setback
        btn_t2 = findViewById(R.id.btn_t2);
        btn_t3 = findViewById(R.id.btn_t3);
        btn_t4 = findViewById(R.id.btn_t4);
        btn_t5 = findViewById(R.id.btn_t5);
        btn_t6 = findViewById(R.id.btn_t6);
        btn_t7 = findViewById(R.id.btn_t7);
        btn_cn = findViewById(R.id.btn_cn);


        btn_t2.setOnClickListener(this);
        btn_t3.setOnClickListener(this);
        btn_t4.setOnClickListener(this);
        btn_t5.setOnClickListener(this);
        btn_t6.setOnClickListener(this);
        btn_t7.setOnClickListener(this);
        btn_cn.setOnClickListener(this);
        // txt_xong.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("pause", "onPause: ");
    }

    @Override
    protected void onResume() {
        sharedPeferences();
        showNgayThang();

        super.onResume();
        Log.e("resume", "onResume: ");
    }

    private void showNgayThang() {
        int colorCam = getResources().getColor(R.color.textWakeUp);
        int colorDen = getResources().getColor(R.color.Black);

        if (SharedPreferencesManager.getMonday(SettingActivity.this) == true) {
            btn_t2.setBackgroundResource(R.drawable.custom_toggle);
            btn_t2.setTextColor(colorDen);
        } else {
            btn_t2.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t2.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getTuesday(SettingActivity.this) == true) {
            btn_t3.setBackgroundResource(R.drawable.custom_toggle);
            btn_t3.setTextColor(colorDen);
        } else {
            btn_t3.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t3.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getWednesday(SettingActivity.this) == true) {
            btn_t4.setBackgroundResource(R.drawable.custom_toggle);
            btn_t4.setTextColor(colorDen);
        } else {
            btn_t4.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t4.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getThursday(SettingActivity.this) == true) {
            btn_t5.setBackgroundResource(R.drawable.custom_toggle);
            btn_t5.setTextColor(colorDen);
        } else {
            btn_t5.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t5.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getFriday(SettingActivity.this) == true) {
            btn_t6.setBackgroundResource(R.drawable.custom_toggle);
            btn_t6.setTextColor(colorDen);
        } else {
            btn_t6.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t6.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getSaturday(SettingActivity.this) == true) {
            btn_t7.setBackgroundResource(R.drawable.custom_toggle);
            btn_t7.setTextColor(colorDen);
        } else {
            btn_t7.setBackgroundResource(R.drawable.custom_toggle1);
            btn_t7.setTextColor(colorCam);
        }
        if (SharedPreferencesManager.getSunday(SettingActivity.this) == true) {
            btn_cn.setBackgroundResource(R.drawable.custom_toggle);
            btn_cn.setTextColor(colorDen);
        } else {
            btn_cn.setBackgroundResource(R.drawable.custom_toggle1);
            btn_cn.setTextColor(colorCam);
        }
    }

    private void sharedPeferences() {
        int a = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, a, 0);
        seekBar.setProgress(a);
        txtSound.setText(SharedPreferencesManager.getTenBaiHat(SettingActivity.this));
        txtNhacNho.setText(SharedPreferencesManager.getNhacNho(SettingActivity.this));
//
//        if (SharedPreferencesManager.get1hour(SettingActivity.this)==true){
//            imv_check1Hour.setVisibility(View.VISIBLE);
//            imv_checkSleep.setVisibility(View.GONE);
//            imv_check30P.setVisibility(View.GONE);
//            imv_check15P.setVisibility(View.GONE);
//            imv_check5P.setVisibility(View.GONE);
//        }else {
//            if (SharedPreferencesManager.get5p(SettingActivity.this)==true){
//                imv_check1Hour.setVisibility(View.GONE);
//                imv_checkSleep.setVisibility(View.GONE);
//                imv_check30P.setVisibility(View.GONE);
//                imv_check15P.setVisibility(View.GONE);
//                imv_check5P.setVisibility(View.VISIBLE);
//            }else {
//                if (SharedPreferencesManager.get15p(SettingActivity.this)==true){
//                    imv_check1Hour.setVisibility(View.GONE);
//                    imv_checkSleep.setVisibility(View.GONE);
//                    imv_check30P.setVisibility(View.GONE);
//                    imv_check15P.setVisibility(View.VISIBLE);
//                    imv_check5P.setVisibility(View.GONE);
//                }else {
//                    if (SharedPreferencesManager.get30p(SettingActivity.this)==true){
//                        imv_check1Hour.setVisibility(View.GONE);
//                        imv_checkSleep.setVisibility(View.GONE);
//                        imv_check30P.setVisibility(View.VISIBLE);
//                        imv_check15P.setVisibility(View.GONE);
//                        imv_check5P.setVisibility(View.GONE);
//                    }else {
//                        if (SharedPreferencesManager.getSleepNow(SettingActivity.this)==true){
//                            imv_check1Hour.setVisibility(View.GONE);
//                            imv_checkSleep.setVisibility(View.VISIBLE);
//                            imv_check30P.setVisibility(View.GONE);
//                            imv_check15P.setVisibility(View.GONE);
//                            imv_check5P.setVisibility(View.GONE);
//                        }
//                    }
//                }
//            }
//        }
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        SettingActivity.this.finish();
        if (mediaPlayer.isPlaying()==true){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        int colorCam = getResources().getColor(R.color.textWakeUp);
        int colorDen = getResources().getColor(R.color.Black);
        switch (view.getId()) {
            case R.id.btn_t2:
                if (dkT2 == false) {
                    dkT2 = true;
                    aBooleanT2 = true;
                    btn_t2.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t2.setTextColor(colorDen);
                    SharedPreferencesManager.setMonday(this, true);
                } else {
                    dkT2 = false;
                    aBooleanT2 = false;
                    btn_t2.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t2.setTextColor(colorCam);
                    SharedPreferencesManager.setMonday(this, false);
                }
                break;
            case R.id.btn_t3:
                if (dkT3 == false) {
                    dkT3 = true;
                    aBooleanT3 = true;
                    btn_t3.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t3.setTextColor(colorDen);
                    SharedPreferencesManager.setTuesday(this, true);
                } else {
                    dkT3 = false;
                    aBooleanT3 = false;
                    btn_t3.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t3.setTextColor(colorCam);
                    SharedPreferencesManager.setTuesday(this, false);
                }
                break;
            case R.id.btn_t4:
                if (dkT4 == false) {
                    dkT4 = true;
                    aBooleanT4 = true;
                    btn_t4.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t4.setTextColor(colorDen);
                    SharedPreferencesManager.setWednesday(this, true);
                } else {
                    dkT4 = false;
                    aBooleanT4 = false;
                    btn_t4.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t4.setTextColor(colorCam);
                    SharedPreferencesManager.setWednesday(this, false);
                }
                break;
            case R.id.btn_t5:
                if (dkT5 == false) {
                    dkT5 = true;
                    aBooleanT5 = true;
                    btn_t5.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t5.setTextColor(colorDen);
                    SharedPreferencesManager.setThursday(this, true);
                } else {
                    dkT5 = false;
                    aBooleanT5 = false;
                    btn_t5.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t5.setTextColor(colorCam);
                    SharedPreferencesManager.setThursday(this, false);
                }
                break;
            case R.id.btn_t6:
                if (dkT6 == false) {
                    dkT6 = true;
                    aBooleanT6 = true;
                    btn_t6.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t6.setTextColor(colorDen);
                    SharedPreferencesManager.setFriday(this, true);
                } else {
                    dkT6 = false;
                    aBooleanT6 = false;
                    btn_t6.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t6.setTextColor(colorCam);
                    SharedPreferencesManager.setFriday(this, false);
                }
                break;
            case R.id.btn_t7:
                if (dkT7 == false) {
                    dkT7 = true;
                    aBooleanT7 = true;
                    btn_t7.setBackgroundResource(R.drawable.custom_toggle);
                    btn_t7.setTextColor(colorDen);
                    SharedPreferencesManager.setSaturday(this, true);
                } else {
                    dkT7 = false;
                    aBooleanT7 = false;
                    btn_t7.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_t7.setTextColor(colorCam);
                    SharedPreferencesManager.setSaturday(this, false);
                }
                break;
            case R.id.btn_cn:
                if (dkCN == false) {
                    dkCN = true;
                    aBooleanCn = true;
                    btn_cn.setBackgroundResource(R.drawable.custom_toggle);
                    btn_cn.setTextColor(colorDen);
                    SharedPreferencesManager.setSunday(this, true);
                } else {
                    dkCN = false;
                    aBooleanCn = false;
                    btn_cn.setBackgroundResource(R.drawable.custom_toggle1);
                    btn_cn.setTextColor(colorCam);
                    SharedPreferencesManager.setSunday(this, false);
                }
                break;
        }
    }

    private void sendBroadcastReceiver() {
        sendBroadcast(new Intent("TimeSleepActivity"));
        sendBroadcast(new Intent("TimeWakeUpActivity"));
    }

}
