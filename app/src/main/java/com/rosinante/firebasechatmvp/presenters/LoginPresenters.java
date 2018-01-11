package com.rosinante.firebasechatmvp.presenters;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rosinante.firebasechatmvp.views.interfaces.LoginView;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public class LoginPresenters {
    private LoginView loginView;

    public LoginPresenters(LoginView loginView) {
        this.loginView = loginView;
    }

    public void firebaseAnonymousAuth() {
        loginView.onChangeButtonText();
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    loginView.showToast("Authentication Failed");
                } else {
                    loginView.onAuthSuccess();
                }
            }
        });
    }

    public void invalidateRoom(String roomName) {
        if (roomName.trim().isEmpty()) {
            loginView.showToast("Enter a valid room name!");
        } else {
            loginView.startChatActivity(roomName);
        }
    }

    public void showRoomDialogInActivity() {
        loginView.onShowRoomDialog();
    }
}
