package com.example.egyapolley.github;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.egyapolley.github.model.RepoDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    private List<RepoDetails> mRepoDetails;

    public MyAdaptor(List<RepoDetails> repoDetails) {
        mRepoDetails = repoDetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.list_item_recycle, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RepoDetails repoDetails = mRepoDetails.get(position);
        holder.bind(repoDetails);

    }

    @Override
    public int getItemCount() {
        return mRepoDetails.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView repoNameTextView;
        private TextView repoDescTextView;
        private TextView repoLangTextview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            repoNameTextView =itemView.findViewById(R.id.repoNameTextView);
            repoDescTextView = itemView.findViewById(R.id.repoDescTextView);
            repoLangTextview = itemView.findViewById(R.id.repoLangTextView);
        }

        public void bind(RepoDetails details){
            repoNameTextView.setText(details.getName());
            repoDescTextView.setText(details.getDescription());
            repoLangTextview.setText(details.getLanguage());
        }
    }
}
