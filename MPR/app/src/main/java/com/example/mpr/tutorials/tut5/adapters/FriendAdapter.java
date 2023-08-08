package com.example.mpr.tutorials.tut5.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut5.models.Friend;

import java.io.Serializable;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> implements Serializable {
    // dataset
    private List<Friend> friends;

    public FriendAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate xml -> view into parent
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.tut5_item_friend, parent, false);
        // return view holder (manage inflated item view)
        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        //get dataset item at position
        Friend friend = friends.get(position);

        //binf into itemView
        holder.bind(friend);
    }

    public class FriendHolder extends RecyclerView.ViewHolder {
        public FriendHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Friend friend) {
            TextView textView = itemView.findViewById(R.id.friend);
            textView.setText(friend.getName());
            ImageButton call = itemView.findViewById(R.id.call);
            call.setContentDescription(friend.getPhoneNo());
            call.setTag(friend);
            ImageButton mail = itemView.findViewById(R.id.mail);
            mail.setContentDescription(friend.getEmail());
            mail.setTag(friend);
            ImageButton chat = itemView.findViewById(R.id.chat);
            chat.setContentDescription(friend.getPhoneNo());
            chat.setTag(friend);
            ImageButton del = itemView.findViewById(R.id.del);
            del.setTag(friend);
            ImageButton edit = itemView.findViewById(R.id.edit);
            edit.setTag(friend);
        }
    }
}
