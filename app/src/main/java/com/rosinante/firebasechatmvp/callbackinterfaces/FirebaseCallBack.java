package com.rosinante.firebasechatmvp.callbackinterfaces;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public interface FirebaseCallBack {
    void onNewMessage(DataSnapshot dataSnapshot);
}
