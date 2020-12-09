package com.hfrad.popularlibrary.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.presenter.list.IListPresenter;
import com.hfrad.popularlibrary.mvp.presenter.list.IUserListPresenter;
import com.hfrad.popularlibrary.mvp.view.UserItemView;
import com.hfrad.popularlibrary.mvp.view.image.GlideImageLoader;
import com.hfrad.popularlibrary.mvp.view.image.IImageLoader;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    IUserListPresenter presenter;
    private static IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    public UserRVAdapter(IUserListPresenter presenter) {
       this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onItemClick(holder);
            }
        });

        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements UserItemView {
        TextView textView;
        ImageView avatarView;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.tv_login);
            avatarView = (ImageView)itemView.findViewById(R.id.iv_avatar);
        }

        @Override
        public void setLogin(String text) {
            textView.setText(text);
        }

        @Override
        public void loadAvatar(String url) {
            imageLoader.loadImage(url, avatarView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
