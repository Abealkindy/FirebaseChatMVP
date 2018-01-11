package com.rosinante.firebasechatmvp.views.interfaces;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public interface LoginView {
    void showToast(String message);

    void onAuthSuccess();

    void onShowRoomDialog();

    void onChangeButtonText();

    void startChatActivity(String roomName);
}
