package com.rosinante.firebasechatmvp.callbackinterfaces;

import com.rosinante.firebasechatmvp.models.ChatModel;

import java.util.ArrayList;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public interface ModelCallBack {
    void onModelUpdated(ArrayList<ChatModel> messages);
}
