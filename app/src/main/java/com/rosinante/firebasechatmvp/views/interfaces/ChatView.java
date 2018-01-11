package com.rosinante.firebasechatmvp.views.interfaces;

import com.rosinante.firebasechatmvp.models.ChatModel;

import java.util.ArrayList;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public interface ChatView {
    void updateList(ArrayList<ChatModel> chatModels);

    void clearEditText();
}
