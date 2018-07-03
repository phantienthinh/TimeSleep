package com.example.tienthinh.timesleep.listener;

/**
 * Created by Tienthinh on 1/23/2018.
 */
//b1 tạo ra 1 interface để chuyển sự kiện  click từ bên adapter sang nội dung no
public interface OnitemClickImageListenner {

    /**
     * được gọi khi có sự kiện click vào image
     * @param position
     */
    void onItemClickImage(int position);
}
