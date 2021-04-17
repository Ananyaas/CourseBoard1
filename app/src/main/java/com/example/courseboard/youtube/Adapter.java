package com.example.courseboard.youtube;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseboard.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    YouTubePlayerView imageView;
    Context context;
    List<Item> articles;

    public Adapter(Context context, List<Item> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubeitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item a = articles.get(position);

        String imageUrl = a.getSnippet().getThumbnails().toString();
        String url = a.getId().getVideoId();

        //Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.tvTitle.setText(a.getSnippet().getTitle());
        holder.tvSource.setText(a.getSnippet().getDescription());
        holder.tvDate.setText(a.getSnippet().getPublishedAt()+" "+a.getSnippet().getPublishTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playvideo(url);
            }
        });
    }

    private void playvideo(String url) {
        //getLifecycle().addObserver(imageView);
        imageView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = url;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvSource,tvDate;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.youtubename);
            tvSource = itemView.findViewById(R.id.youtubedesc);
            tvDate = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.youtubeimg);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}
