package com.rosinante.firebasechatmvp.presenters;

import com.google.firebase.database.DataSnapshot;
import com.rosinante.firebasechatmvp.callbackinterfaces.FirebaseCallBack;
import com.rosinante.firebasechatmvp.callbackinterfaces.ModelCallBack;
import com.rosinante.firebasechatmvp.models.ChatModel;
import com.rosinante.firebasechatmvp.models.MessageModel;
import com.rosinante.firebasechatmvp.utils.FirebaseManager;
import com.rosinante.firebasechatmvp.views.interfaces.ChatView;

import java.util.ArrayList;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public class ChatPresenters implements FirebaseCallBack, ModelCallBack {
    private ChatView chatView;
    private MessageModel messageModel;

    public ChatPresenters(ChatView chatView) {
        this.chatView = chatView;
        this.messageModel = new MessageModel();
    }

    public void sendMessageToFirebase(String roomName, String message) {
        if (!message.trim().equals("")) {
            FirebaseManager.getInstance(roomName, this).sendMessageToFirebase(message);
        }
        chatView.clearEditText();
    }

    public void setListener(String roomname) {
        FirebaseManager.getInstance(roomname, this).addMessageListeners();
    }

    public void onDestroy(String roomName) {
        FirebaseManager.getInstance(roomName, this).removeListener();
        FirebaseManager.getInstance(roomName, this).destroy();
        chatView = null;
    }

    @Override
    public void onNewMessage(DataSnapshot dataSnapshot) {
        messageModel.addMessages(dataSnapshot, this);
    }

    @Override
    public void onModelUpdated(ArrayList<ChatModel> messages) {
        if (messages.size() > 0) {
            chatView.updateList(messages);
        }
    }
}
