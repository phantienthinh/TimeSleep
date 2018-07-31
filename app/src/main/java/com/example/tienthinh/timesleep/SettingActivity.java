package com.example.tienthinh.timesleep;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tienthinh.timesleep.Adapter.SoundAdapter;
import com.example.tienthinh.timesleep.model.SharedPreferencesManager;
import com.example.tienthinh.timesleep.model.Sound;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
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
    private ImageView imv_checkSleep, imv_check1Hour, imv_check30P, imv_check15P, imv_check5P;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String SHARED_PREFERENCES_NAME = "sharePreferences";
    private ListView lvSound;
    private ArrayList<Sound> Array_List_sounds;
    private SoundAdapter soundAdapter;
    private MediaPlayer mediaPlayer;
    private Button btn_Save_Sound;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.littlecomfort);
        preferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        initView();
        onClickHuy();
        onClickTimeNhacNho();
        onClickTextViewShound();
        onClickSeekBar();
    }

    private void onClickSeekBar() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                SharedPreferencesManager.setVolume(SettingActivity.this, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onClickTextViewShound() {
        txtSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSound = new Dialog(SettingActivity.this);
                dialogSound.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSound.setCancelable(true);
                dialogSound.setContentView(R.layout.custom_dialog_amthanh);

                lvSound = (ListView) dialogSound.findViewById(R.id.listView_AmThanh);
                Array_List_sounds = new ArrayList<Sound>();
                Array_List_sounds.add(new Sound("Little Comfort", false));
                Array_List_sounds.add(new Sound("Annie's Wonderland", false));
                Array_List_sounds.add(new Sound("Havana", false));
                Array_List_sounds.add(new Sound("This Game", false));
                Array_List_sounds.add(new Sound("Grief And Sorrow", false));
                Array_List_sounds.add(new Sound("Chúng ta không giống nhau", false));
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
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.chungtakhonggiongnhau);
                                    mediaPlayer.start();
                                } else {
                                    mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.chungtakhonggiongnhau);
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
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Chúng ta không giống nhau");
                txtSound.setText("Chúng ta không giống nhau");
                break;
            case 6:
                SharedPreferencesManager.setTenBaiHat(SettingActivity.this, "Beautiful Girl");
                txtSound.setText("Beautiful Girl");
                break;
        }
        dialogSound.cancel();
    }

    private void onClickTimeNhacNho() {
        txtNhacNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(SettingActivity.this);
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        if (SharedPreferencesManager.getSleepNow(SettingActivity.this)==true) {
            imv_checkSleep.setVisibility(View.VISIBLE);
        } else {
            if (SharedPreferencesManager.get1hour(SettingActivity.this)==true){
                imv_check1Hour.setVisibility(View.VISIBLE);
            } else {
                if (SharedPreferencesManager.get15p(SettingActivity.this)==true) {
                    imv_check15P.setVisibility(View.VISIBLE);
                } else {
                    if (SharedPreferencesManager.get5p(SettingActivity.this)==true) {
                        imv_check5P.setVisibility(View.VISIBLE);
                    } else {
                        if (SharedPreferencesManager.get30p(SettingActivity.this)==true) {
                            imv_check30P.setVisibility(View.VISIBLE);
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
        if (imv_checkSleep.getVisibility() == View.VISIBLE) {
            txtNhacNho.setText("Lúc Đi Ngủ");
            SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
            SharedPreferencesManager.setSleepNow(SettingActivity.this, true);
            SharedPreferencesManager.set30p(SettingActivity.this, false);
            SharedPreferencesManager.set5p(SettingActivity.this, false);
            SharedPreferencesManager.set1hour(SettingActivity.this, false);
            SharedPreferencesManager.set15p(SettingActivity.this, false);
            dialog.cancel();
        } else {
            if (imv_check5P.getVisibility() == View.VISIBLE) {
                txtNhacNho.setText("5 Phút");
                SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                SharedPreferencesManager.set30p(SettingActivity.this, false);
                SharedPreferencesManager.set5p(SettingActivity.this, true);
                SharedPreferencesManager.set1hour(SettingActivity.this, false);
                SharedPreferencesManager.set15p(SettingActivity.this, false);
                dialog.cancel();
            } else {
                if (imv_check15P.getVisibility() == View.VISIBLE) {
                    txtNhacNho.setText("15 Phút");
                    SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                    SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                    SharedPreferencesManager.set30p(SettingActivity.this, false);
                    SharedPreferencesManager.set5p(SettingActivity.this, false);
                    SharedPreferencesManager.set1hour(SettingActivity.this, false);
                    SharedPreferencesManager.set15p(SettingActivity.this, true);
                    dialog.cancel();
                } else {
                    if (imv_check30P.getVisibility() == View.VISIBLE) {
                        txtNhacNho.setText("30 Phút");
                        SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                        SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                        SharedPreferencesManager.set30p(SettingActivity.this, true);
                        SharedPreferencesManager.set5p(SettingActivity.this, false);
                        SharedPreferencesManager.set1hour(SettingActivity.this, false);
                        SharedPreferencesManager.set15p(SettingActivity.this, false);
                        dialog.cancel();
                    } else {
                        if (imv_check1Hour.getVisibility() == View.VISIBLE) {
                            txtNhacNho.setText("1 Giờ");
                            SharedPreferencesManager.setNhacNho(SettingActivity.this, txtNhacNho.getText().toString().trim());
                            SharedPreferencesManager.setSleepNow(SettingActivity.this, false);
                            SharedPreferencesManager.set30p(SettingActivity.this, false);
                            SharedPreferencesManager.set5p(SettingActivity.this, false);
                            SharedPreferencesManager.set1hour(SettingActivity.this, true);
                            SharedPreferencesManager.set15p(SettingActivity.this, false);
                            dialog.cancel();
                        } else {
                            Toast.makeText(this, "Bạn Chưa Chọn Thời Gian", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }


        }
    }

    private void onClickTxtSleep() {
        txtSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv_check1Hour.setVisibility(View.GONE);
                imv_checkSleep.setVisibility(View.VISIBLE);
                imv_check30P.setVisibility(View.GONE);
                imv_check15P.setVisibility(View.GONE);
                imv_check5P.setVisibility(View.GONE);
            }
        });

    }

    private void onClickTxt15P() {
        txt15P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv_check1Hour.setVisibility(View.GONE);
                imv_checkSleep.setVisibility(View.GONE);
                imv_check30P.setVisibility(View.GONE);
                imv_check15P.setVisibility(View.VISIBLE);
                imv_check5P.setVisibility(View.GONE);
            }
        });

    }

    private void onClickTxt30P() {
        txt30P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv_check1Hour.setVisibility(View.GONE);
                imv_checkSleep.setVisibility(View.GONE);
                imv_check30P.setVisibility(View.VISIBLE);
                imv_check15P.setVisibility(View.GONE);
                imv_check5P.setVisibility(View.GONE);
            }
        });

    }

    private void onClickTxt5P() {
        txt5P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv_check1Hour.setVisibility(View.GONE);
                imv_checkSleep.setVisibility(View.GONE);
                imv_check30P.setVisibility(View.GONE);
                imv_check15P.setVisibility(View.GONE);
                imv_check5P.setVisibility(View.VISIBLE);
            }
        });

    }

    private void onClickTxt1Hour() {
        txt1Hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv_check1Hour.setVisibility(View.VISIBLE);
                imv_checkSleep.setVisibility(View.GONE);
                imv_check30P.setVisibility(View.GONE);
                imv_check15P.setVisibility(View.GONE);
                imv_check5P.setVisibility(View.GONE);
            }
        });

    }

    private void initViewDiaLog() {
        txt_Save = (TextView) dialog.findViewById(R.id.textSave);
        txt1Hour = (TextView) dialog.findViewById(R.id.text1Hour);
        txt5P = (TextView) dialog.findViewById(R.id.textView5P);
        txt15P = (TextView) dialog.findViewById(R.id.textView15P);
        txt30P = (TextView) dialog.findViewById(R.id.textView30P);
        txtSleep = (TextView) dialog.findViewById(R.id.textSleep);

        imv_check1Hour = (ImageView) dialog.findViewById(R.id.check1Hour);
        imv_check5P = (ImageView) dialog.findViewById(R.id.check5P);
        imv_check15P = (ImageView) dialog.findViewById(R.id.check15P);
        imv_check30P = (ImageView) dialog.findViewById(R.id.check30P);
        imv_checkSleep = (ImageView) dialog.findViewById(R.id.checkSleep);
    }

    private void onClickHuy() {
        iv_comback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                SettingActivity.this.finish();
            }
        });
    }

    private void initView() {
        //txt_xong = findViewById(R.id.txt_xong);
        iv_comback = (ImageView) findViewById(R.id.comeback);
        txtNhacNho = (TextView) findViewById(R.id.textViewNhacNho);
        txtSound = (TextView) findViewById(R.id.textView_music);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
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
        if (SharedPreferencesManager.getMonday(SettingActivity.this) == true) {
            btn_t2.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t2.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getTuesday(SettingActivity.this) == true) {
            btn_t3.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t3.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getWednesday(SettingActivity.this) == true) {
            btn_t4.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t4.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getThursday(SettingActivity.this) == true) {
            btn_t5.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t5.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getFriday(SettingActivity.this) == true) {
            btn_t6.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t6.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getSaturday(SettingActivity.this) == true) {
            btn_t7.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_t7.setBackgroundResource(R.drawable.custom_toggle);
        }
        if (SharedPreferencesManager.getSunday(SettingActivity.this) == true) {
            btn_cn.setBackgroundResource(R.drawable.custom_toggle1);
        } else {
            btn_cn.setBackgroundResource(R.drawable.custom_toggle);
        }
    }

    private void sharedPeferences() {
        seekBar.setProgress(SharedPreferencesManager.getVolume(SettingActivity.this));
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SettingActivity.this.finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_t2:
                if (dkT2 == false) {
                    dkT2 = true;
                    aBooleanT2 = true;
                    btn_t2.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setMonday(this, true);
                } else {
                    dkT2 = false;
                    aBooleanT2 = false;
                    btn_t2.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setMonday(this, false);
                }
                break;
            case R.id.btn_t3:
                if (dkT3 == false) {
                    dkT3 = true;
                    aBooleanT3 = true;
                    btn_t3.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setTuesday(this, true);
                } else {
                    dkT3 = false;
                    aBooleanT3 = false;
                    btn_t3.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setTuesday(this, false);
                }
                break;
            case R.id.btn_t4:
                if (dkT4 == false) {
                    dkT4 = true;
                    aBooleanT4 = true;
                    btn_t4.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setWednesday(this, true);
                } else {
                    dkT4 = false;
                    aBooleanT4 = false;
                    btn_t4.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setWednesday(this, false);
                }
                break;
            case R.id.btn_t5:
                if (dkT5 == false) {
                    dkT5 = true;
                    aBooleanT5 = true;
                    btn_t5.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setThursday(this, true);
                } else {
                    dkT5 = false;
                    aBooleanT5 = false;
                    btn_t5.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setThursday(this, false);
                }
                break;
            case R.id.btn_t6:
                if (dkT6 == false) {
                    dkT6 = true;
                    aBooleanT6 = true;
                    btn_t6.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setFriday(this, true);
                } else {
                    dkT6 = false;
                    aBooleanT6 = false;
                    btn_t6.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setFriday(this, false);
                }
                break;
            case R.id.btn_t7:
                if (dkT7 == false) {
                    dkT7 = true;
                    aBooleanT7 = true;
                    btn_t7.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setSaturday(this, true);
                } else {
                    dkT7 = false;
                    aBooleanT7 = false;
                    btn_t7.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setSaturday(this, false);
                }
                break;
            case R.id.btn_cn:
                if (dkCN == false) {
                    dkCN = true;
                    aBooleanCn = true;
                    btn_cn.setBackgroundResource(R.drawable.custom_toggle1);
                    SharedPreferencesManager.setSunday(this, true);
                } else {
                    dkCN = false;
                    aBooleanCn = false;
                    btn_cn.setBackgroundResource(R.drawable.custom_toggle);
                    SharedPreferencesManager.setSunday(this, false);
                }
                break;
//            case R.id.txt_xong:
//
//
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                SettingActivity.this.finish();
//
//                break;
        }
    }

}
