package com.rosinante.firebasechatmvp.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.firebasechatmvp.R;
import com.rosinante.firebasechatmvp.presenters.LoginPresenters;
import com.rosinante.firebasechatmvp.utils.Utils;
import com.rosinante.firebasechatmvp.views.interfaces.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.button_auth)
    Button buttonAuth;
    @BindView(R.id.text_status_auth)
    TextView textStatusAuth;
    @BindView(R.id.button_create_room)
    Button buttonCreateRoom;
    @BindView(R.id.button_enter_room)
    Button buttonEnterRoom;
    @BindView(R.id.linear_button_room)
    LinearLayout linearButtonRoom;

    private Dialog chatRoomDialog;
    private LoginPresenters loginPresenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenters = new LoginPresenters(this);
    }

    @OnClick({R.id.button_auth, R.id.button_create_room, R.id.button_enter_room})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_auth:
                loginPresenters.firebaseAnonymousAuth();
                break;
            case R.id.button_create_room:
                loginPresenters.showRoomDialogInActivity();
                break;
            case R.id.button_enter_room:
                loginPresenters.showRoomDialogInActivity();
                break;
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthSuccess() {
        buttonAuth.setVisibility(View.GONE);
        textStatusAuth.setVisibility(View.VISIBLE);
        linearButtonRoom.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowRoomDialog() {
        chatRoomDialog = new Dialog(this);
        chatRoomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_room, null);
        Button button = view.findViewById(R.id.button_submit);
        final EditText editText = view.findViewById(R.id.edit_text_room_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenters.invalidateRoom(editText.getText().toString());
            }
        });
        chatRoomDialog.setContentView(view);
        chatRoomDialog.show();
    }

    @Override
    public void onChangeButtonText() {
        buttonAuth.setText(R.string.auth_process_text);
    }

    @Override
    public void startChatActivity(String roomName) {
        chatRoomDialog.dismiss();
        chatRoomDialog = null;
        Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
        intent.putExtra(Utils.EXTRA_ROOM_NAME, roomName);
        startActivity(intent);
    }

}
