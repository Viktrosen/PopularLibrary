package com.hfrad.popularlibrary.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.presenter.list.IRepositoryListPresenter;
import com.hfrad.popularlibrary.mvp.view.list.RepositoryItemView;

public class ReposotoriesRVAdapter extends RecyclerView.Adapter<ReposotoriesRVAdapter.ViewHolder> {
    private IRepositoryListPresenter mPresenter;

    public ReposotoriesRVAdapter(IRepositoryListPresenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ReposotoriesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repoView = inflater.inflate(R.layout.item_repository, parent, false);

        ReposotoriesRVAdapter.ViewHolder viewHolder = new ReposotoriesRVAdapter.ViewHolder(repoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReposotoriesRVAdapter.ViewHolder holder, int position) {
        holder.position = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onItemClick(holder);
            }
        });

        mPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements RepositoryItemView {
        TextView name;
        int position = -1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.tv_name);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setName(String text) {
            name.setText(text);
        }
    }
}
