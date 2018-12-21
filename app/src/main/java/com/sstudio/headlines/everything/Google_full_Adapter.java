package com.sstudio.headlines.everything;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sstudio.headlines.Models.heads.Article;
import com.sstudio.headlines.Models.heads.Heads;
import com.sstudio.headlines.R;
import com.sstudio.headlines.everything.Google_full.OnListFragmentInteractionListener;
import com.sstudio.headlines.everything.dummy.DummyContent.DummyItem;

import org.json.JSONObject;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class Google_full_Adapter extends RecyclerView.Adapter<Google_full_Adapter.ViewHolder> {

    private final List<Article> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context context;
    int page=1;
    public Google_full_Adapter(List<Article> items, OnListFragmentInteractionListener listener,Context context1) {
        mValues = items;
        mListener = listener;
        context=context1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.google_full_item, parent, false);
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
        if (position>=(getItemCount()-3)){
            loadMore();
        }
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
            title = (TextView) view.findViewById(R.id.title_google_full);
            author = (TextView) view.findViewById(R.id.author_google_full);
            description = (TextView) view.findViewById(R.id.description_google_full);
            imageView=view.findViewById(R.id.image_google_full);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
    public void loadMore(){
        page++;
        Log.d("loading more : ","page count-"+page);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v2/everything?q=bitcoin&page="+page
                        +"&language=en&apiKey=43e9d4a391154130ae1593d97fa81d3f",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson=new Gson();
                Heads heads=gson.fromJson(String.valueOf(response),Heads.class);
                List<Article> articleList = heads.getArticles();
                mValues.addAll(articleList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
