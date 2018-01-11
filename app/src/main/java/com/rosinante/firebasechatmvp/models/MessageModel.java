package com.rosinante.firebasechatmvp.models;

import com.google.firebase.database.DataSnapshot;
import com.rosinante.firebasechatmvp.callbackinterfaces.ModelCallBack;

import java.util.ArrayList;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public class MessageModel {
    private ArrayList<ChatModel> chatModels;

    public void addMessages(DataSnapshot dataSnapshot, ModelCallBack modelCallBack) {
        if (chatModels == null) {
            chatModels = new ArrayList<>();
        }
        ChatModel chatModel = new ChatModel(dataSnapshot);
        chatModels.add(chatModel);
        modelCallBack.onModelUpdated(chatModels);
    }
}
