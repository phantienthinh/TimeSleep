package com.example.tienthinh.timesleep.models;

public class Sound {
    private String txt_name_sound;
    private boolean radioButton;

    public Sound(String txt_name_sound, boolean radioButton) {
        this.txt_name_sound = txt_name_sound;
        this.radioButton = radioButton;
    }

    public Sound() {
    }

    public String getTxt_name_sound() {
        return txt_name_sound;
    }

    public void setTxt_name_sound(String txt_name_sound) {
        this.txt_name_sound = txt_name_sound;
    }

    public boolean isRadioButton() {
        return radioButton;
    }

    public void setRadioButton(boolean radioButton) {
        this.radioButton = radioButton;
    }
}