package com.nynelyne.jsoupDemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mutale Chanda on 08/03/2019.
 */
public class NewsHeadlinesZambianDataAdapter extends RecyclerView.Adapter<NewsHeadlinesZambianDataAdapter.MyViewHolder>
{
    private ArrayList<String> mNewsHeadlineList = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;

    public NewsHeadlinesZambianDataAdapter(Context activity, ArrayList<String> mNewsHeadlineList)
    {
        this.mNewsHeadlineList = mNewsHeadlineList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView_news_headlines;

        public MyViewHolder(View view)
        {
            super(view);
            textView_news_headlines = (TextView) itemView.findViewById(R.id.row_tv_headline);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_news_headline_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.textView_news_headlines.setText(mNewsHeadlineList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mNewsHeadlineList.size();
    }
}
