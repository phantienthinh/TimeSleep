package com.example.tienthinh.timesleep;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.tienthinh.timesleep.model.Sound;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    public static TextView txtNhacNho, txtSound;
    private SeekBar seekBar;
    private AudioManager audioManager;
    private boolean aBooleanCheckSleep = false;
    private boolean aBooleanCheck1Hour = false;
    private boolean aBooleanCheck15P = false;
    private boolean aBooleanCheck30P = false;
    private boolean aBooleanCheck5P = false;
    private TextView txtComeBack, txt1Hour, txt30P, txt15P, txt5P, txtSleep, txt_Save;
    private boolean doubleBackToExitPressedOnce = false;
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

                Toast.makeText(SettingActivity.this, "Media Volume : " + i, Toast.LENGTH_SHORT).show();
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                editor.putInt("volume", i);
                editor.commit();
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
                                txtSound.setText("Little Comfort");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                txtSound.setText("Annie's Wonderland");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                txtSound.setText("Havana");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                txtSound.setText("This Game");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                txtSound.setText("Grief And Sorrow");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                txtSound.setText("Chúng ta không giống nhau");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.putInt("position", position);
                                editor.commit();
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
                                editor.putInt("position", position);
                                txtSound.setText("Beautiful Girl");
                                editor.putString("BH1", txtSound.getText().toString());
                                editor.commit();
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

                Array_List_sounds.get(preferences.getInt("position", 0)).setRadioButton(true);

                soundAdapter = new SoundAdapter(SettingActivity.this, R.layout.item_listview_sound, Array_List_sounds, onItemClickListenner);

                lvSound.setAdapter(soundAdapter);

                dialogSound.show();

                dialogSound.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mediaPlayer.stop();

                        btn_Save_Sound = (Button)dialogSound.findViewById(R.id.btn_Save_Sound) ;

                        btn_Save_Sound.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onclickSaveSound();
                            }
                        });
                    }
                });

            }
        });
    }

    private void onclickSaveSound() {

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
        if (preferences.getBoolean("aBooleanCheckSleep", false) == true) {
            imv_checkSleep.setVisibility(View.VISIBLE);
        } else {
            if (preferences.getBoolean("aBooleanCheck1Hour", false) == true) {
                imv_check1Hour.setVisibility(View.VISIBLE);
            } else {
                if (preferences.getBoolean("aBooleanCheck15P", false) == true) {
                    imv_check15P.setVisibility(View.VISIBLE);
                } else {
                    if (preferences.getBoolean("aBooleanCheck5P", false) == true) {
                        imv_check5P.setVisibility(View.VISIBLE);
                    } else {
                        if (preferences.getBoolean("aBooleanCheck30P", false) == true) {
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
            aBooleanCheckSleep = true;
            aBooleanCheck1Hour = false;
            aBooleanCheck15P = false;
            aBooleanCheck5P = false;
            aBooleanCheck30P = false;
            editor.putString("NhacNho", txtNhacNho.getText().toString());
            editor.putBoolean("aBooleanCheckSleep", aBooleanCheckSleep);
            editor.putBoolean("aBooleanCheck30P", aBooleanCheck30P);
            editor.putBoolean("aBooleanCheck5P", aBooleanCheck5P);
            editor.putBoolean("aBooleanCheck15P", aBooleanCheck15P);
            editor.putBoolean("aBooleanCheck1Hour", aBooleanCheck1Hour);
            editor.commit();
            dialog.cancel();
        } else {
            if (imv_check5P.getVisibility() == View.VISIBLE) {
                txtNhacNho.setText("5 Phút");
                aBooleanCheckSleep = false;
                aBooleanCheck1Hour = false;
                aBooleanCheck15P = false;
                aBooleanCheck5P = true;
                aBooleanCheck30P = false;
                editor.putString("NhacNho", txtNhacNho.getText().toString());
                editor.putBoolean("aBooleanCheckSleep", aBooleanCheckSleep);
                editor.putBoolean("aBooleanCheck30P", aBooleanCheck30P);
                editor.putBoolean("aBooleanCheck5P", aBooleanCheck5P);
                editor.putBoolean("aBooleanCheck15P", aBooleanCheck15P);
                editor.putBoolean("aBooleanCheck1Hour", aBooleanCheck1Hour);
                editor.commit();
                dialog.cancel();
            } else {
                if (imv_check15P.getVisibility() == View.VISIBLE) {
                    txtNhacNho.setText("15 Phút");
                    editor.putString("NhacNho", txtNhacNho.getText().toString());
                    aBooleanCheckSleep = false;
                    aBooleanCheck1Hour = false;
                    aBooleanCheck15P = true;
                    aBooleanCheck5P = false;
                    aBooleanCheck30P = false;
                    editor.putBoolean("aBooleanCheckSleep", aBooleanCheckSleep);
                    editor.putBoolean("aBooleanCheck30P", aBooleanCheck30P);
                    editor.putBoolean("aBooleanCheck5P", aBooleanCheck5P);
                    editor.putBoolean("aBooleanCheck15P", aBooleanCheck15P);
                    editor.putBoolean("aBooleanCheck1Hour", aBooleanCheck1Hour);
                    editor.commit();
                    dialog.cancel();
                } else {
                    if (imv_check30P.getVisibility() == View.VISIBLE) {
                        txtNhacNho.setText("30 Phút");
                        editor.putString("NhacNho", txtNhacNho.getText().toString());
                        aBooleanCheckSleep = false;
                        aBooleanCheck1Hour = false;
                        aBooleanCheck15P = false;
                        aBooleanCheck5P = false;
                        aBooleanCheck30P = true;
                        editor.putBoolean("aBooleanCheckSleep", aBooleanCheckSleep);
                        editor.putBoolean("aBooleanCheck30P", aBooleanCheck30P);
                        editor.putBoolean("aBooleanCheck5P", aBooleanCheck5P);
                        editor.putBoolean("aBooleanCheck15P", aBooleanCheck15P);
                        editor.putBoolean("aBooleanCheck1Hour", aBooleanCheck1Hour);
                        editor.commit();
                        dialog.cancel();
                    } else {
                        if (imv_check1Hour.getVisibility() == View.VISIBLE) {
                            txtNhacNho.setText("1 Giờ");
                            editor.putString("NhacNho", txtNhacNho.getText().toString());
                            aBooleanCheckSleep = false;
                            aBooleanCheck1Hour = true;
                            aBooleanCheck15P = false;
                            aBooleanCheck5P = false;
                            aBooleanCheck30P = false;
                            editor.putBoolean("aBooleanCheckSleep", aBooleanCheckSleep);
                            editor.putBoolean("aBooleanCheck30P", aBooleanCheck30P);
                            editor.putBoolean("aBooleanCheck5P", aBooleanCheck5P);
                            editor.putBoolean("aBooleanCheck15P", aBooleanCheck15P);
                            editor.putBoolean("aBooleanCheck1Hour", aBooleanCheck1Hour);
                            editor.commit();
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
        txtComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        txtComeBack = (TextView) findViewById(R.id.comeback);
        txtNhacNho = (TextView) findViewById(R.id.textViewNhacNho);
        txtSound = (TextView) findViewById(R.id.textView_music);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("pause", "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("resume", "onResume: ");
        sharedPeferences();
    }

    private void sharedPeferences() {
        seekBar.setProgress(preferences.getInt("volume", 0));
        txtSound.setText(preferences.getString("BH1", ""));
        txtNhacNho.setText(preferences.getString("NhacNho",""));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("res", "onRestart: ");
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn Lần Nữa Để Thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
