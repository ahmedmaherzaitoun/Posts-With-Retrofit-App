package com.example.jsondemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CommentListAdaptor extends RecyclerView.Adapter<CommentListAdaptor.CommentviewHolder> {


    private List<Comment> commentsArr = new ArrayList<>();
    @NonNull
    @Override
    public CommentviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentviewHolder holder, int position) {
        holder.userName.setText(commentsArr.get(position).getName());
        holder.body.setText(commentsArr.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return commentsArr.size();
    }
    public void setCommentsArr(List<Comment> commentsArr) {
        this.commentsArr = commentsArr;
        notifyDataSetChanged();

    }
    public class CommentviewHolder extends RecyclerView.ViewHolder {
        private TextView userName , body ;
        public CommentviewHolder(@NonNull View itemView){
            super(itemView);
            userName = itemView.findViewById(R.id.comment_username_tv);
            body = itemView.findViewById(R.id.comment_body_tv);
        }
    }
}
