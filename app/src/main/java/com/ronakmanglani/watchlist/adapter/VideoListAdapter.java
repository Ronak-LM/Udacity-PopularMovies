package com.ronakmanglani.watchlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ronakmanglani.watchlist.R;
import com.ronakmanglani.watchlist.model.Video;
import com.ronakmanglani.watchlist.util.VolleySingleton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    public ArrayList<Video> videoList;
    private final OnVideoClickListener onVideoClickListener;

    // Constructor
    public VideoListAdapter(Context context,
                            ArrayList<Video> videoList,
                            OnVideoClickListener onVideoClickListener) {
        this.context = context;
        this.videoList = videoList;
        this.onVideoClickListener = onVideoClickListener;
    }

    // Return size of ArrayList
    @Override
    public int getItemCount() {
        return videoList.size();
    }

    // Inflate layout and fill data
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(v, onVideoClickListener);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Video video = videoList.get(position);
        VideoViewHolder holder = (VideoViewHolder) viewHolder;
        holder.videoImage.setImageUrl(video.imageURL, VolleySingleton.getInstance(context).imageLoader);
        holder.videoName.setText(video.title);
        holder.videoSubtitle.setText(video.subtitle);
    }

    // ViewHolder for Videos
    public class VideoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.video_item) View videoItem;
        @Bind(R.id.video_image) NetworkImageView videoImage;
        @Bind(R.id.video_name) TextView videoName;
        @Bind(R.id.video_subtitle) TextView videoSubtitle;

        public VideoViewHolder(final ViewGroup itemView, final OnVideoClickListener onVideoClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            videoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onVideoClickListener.onVideoClicked(getAdapterPosition());
                }
            });
        }
    }

    // Interface to respond to clicks
    public interface OnVideoClickListener {
        void onVideoClicked(final int position);
    }
}