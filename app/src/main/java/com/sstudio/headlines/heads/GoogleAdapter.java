package com.sstudio.headlines.heads;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sstudio.headlines.Models.heads.Article;
import com.sstudio.headlines.R;
import com.sstudio.headlines.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link Google.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class GoogleAdapter extends RecyclerView.Adapter<GoogleAdapter.ViewHolder> {

    private final List<Article> mValues;
    private final Google.OnListFragmentInteractionListener mListener;
    Context context;
    public GoogleAdapter(List<Article> items, Google.OnListFragmentInteractionListener listener, Context context1) {
        mValues = items;
        mListener = listener;
        context=context1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.google_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).getTitle());
        holder.author.setText(mValues.get(position).getAuthor());
        holder.description.setText(mValues.get(position).getDescription());
        Picasso.with(context).load(mValues.get(position).getUrlToImage())
                .into(holder.imageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView author;
        public final TextView description;
        ImageView imageView;
        public Article mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.title_google);
            author = (TextView) view.findViewById(R.id.author_google);
            description = (TextView) view.findViewById(R.id.description_google);
            imageView= view.findViewById(R.id.image_google);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
