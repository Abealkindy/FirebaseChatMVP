package com.rosinante.firebasechatmvp.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.rosinante.firebasechatmvp.R;
import com.rosinante.firebasechatmvp.models.ChatModel;
import com.rosinante.firebasechatmvp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<ChatModel> chatModels;
    private Context context;

    public ChatAdapter(ArrayList<ChatModel> chatModels, Context context) {
        this.chatModels = chatModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (chatModels.get(position).getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            holder.layoutMessageLeft.setVisibility(View.GONE);
            holder.layoutMessageRight.setVisibility(View.VISIBLE);
            holder.textMessageRight.setText(chatModels.get(position).getMessage());
            holder.textTimeMessagesRight.setText(Utils.convertTime(chatModels.get(position).getTimeStamp()));
        } else {
            holder.layoutMessageLeft.setVisibility(View.VISIBLE);
            holder.layoutMessageRight.setVisibility(View.GONE);
            holder.textMessageLeft.setText(chatModels.get(position).getMessage());
            holder.textTimeMessagesLeft.setText(Utils.convertTime(chatModels.get(position).getTimeStamp()));
        }
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_message_left)
        TextView textMessageLeft;
        @BindView(R.id.text_time_messages_left)
        TextView textTimeMessagesLeft;
        @BindView(R.id.layout_message_left)
        LinearLayout layoutMessageLeft;
        @BindView(R.id.text_time_messages_right)
        TextView textTimeMessagesRight;
        @BindView(R.id.text_message_right)
        TextView textMessageRight;
        @BindView(R.id.layout_message_right)
        LinearLayout layoutMessageRight;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
