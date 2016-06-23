package com.madelynw.nytimessearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by madelynw on 6/20/16.
 */
public class ArticleArrayAdapter extends RecyclerView.Adapter<ArticleArrayAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);

        }
    }

    private List<Article> articles;
    private Context mContext;

    public ArticleArrayAdapter(Context context, List<Article> article) {
        articles = article;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ArticleArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_article_result, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ArticleArrayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = articles.get(position);

        TextView tvTitle = viewHolder.tvTitle;
        tvTitle.setText(article.getHeadline());

        // Find the image view
        ImageView imageView = viewHolder.ivImage;
        //DynamicHeightImageView imageView = (DynamicHeightImageView) viewHolder.ivImage;

        // Clear out recycled image from convertView from last time
        imageView.setImageResource(0);

        // Populate the thumbnail image

        String thumbnail = article.getThumbnail();

        //imageView.setHeightRatio(((double)thumbnail.getHeight())/thumbnail.getWidth());
        //imageView.setHeightRatio(2.0);

        if (!TextUtils.isEmpty(thumbnail)) {
            Glide.with(getContext()).load(thumbnail)
                    .placeholder(R.drawable.ic_photo)
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 100, 100))
                    .fitCenter()
                    //.thumbnail(0.5f)
                    .error(R.drawable.ic_photo)
                    .into(imageView);
        } else {
            Glide.with(getContext()).load(R.drawable.ic_photo)
                    .into(imageView);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return articles.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        articles.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Article> list) {
        articles.addAll(list);
        notifyDataSetChanged();
    }

}