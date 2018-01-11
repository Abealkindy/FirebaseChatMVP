package com.rosinante.firebasechatmvp.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rosinante.firebasechatmvp.R;
import com.rosinante.firebasechatmvp.models.ChatModel;
import com.rosinante.firebasechatmvp.presenters.ChatPresenters;
import com.rosinante.firebasechatmvp.utils.Utils;
import com.rosinante.firebasechatmvp.views.adapter.ChatAdapter;
import com.rosinante.firebasechatmvp.views.interfaces.ChatView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.recycler_chat)
    RecyclerView recyclerChat;
    @BindView(R.id.edit_text_message)
    EditText editTextMessage;
    @BindView(R.id.button_send_message)
    Button buttonSendMessage;
    @BindView(R.id.linear_send_chat)
    LinearLayout linearSendChat;
    private String roomName;
    private ChatPresenters chatPresenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        roomName = getIntent().getStringExtra(Utils.EXTRA_ROOM_NAME);
        chatPresenters = new ChatPresenters(this);
        chatPresenters.setListener(roomName);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.button_send_message)
    public void onClick() {
        chatPresenters.sendMessageToFirebase(roomName, editTextMessage.getText().toString());
    }

    @Override
    public void updateList(ArrayList<ChatModel> chatModels) {
        ChatAdapter chatAdapter = new ChatAdapter(chatModels, this);
        recyclerChat.setAdapter(chatAdapter);
        recyclerChat.scrollToPosition(chatModels.size() - 1);
    }

    @Override
    public void clearEditText() {
        editTextMessage.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatPresenters.onDestroy(roomName);
    }
}
