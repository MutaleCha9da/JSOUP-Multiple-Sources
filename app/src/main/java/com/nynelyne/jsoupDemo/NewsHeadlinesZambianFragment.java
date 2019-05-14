package com.nynelyne.jsoupDemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mutale Chanda on 09/03/2019.
 */
public class NewsHeadlinesZambianFragment extends Fragment {

    View view;

    private ProgressDialog mProgressDialog;
    private String url = "http://www.luse.co.zm/";
    private ArrayList<String> mNewsHeadlineList = new ArrayList<>();

    public NewsHeadlinesZambianFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_zambian, container, false);


        new Description().execute();

        return view;
    }

    private class Description extends AsyncTask<Void, Void, Void>
    {
        String desc;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(NewsHeadlinesZambianFragment.super.getContext());
            mProgressDialog.setTitle("Business News Headlines");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {

            try {
                mNewsHeadlineList.clear();
                //Connect to the website
                Document mNewsHeadlineDocument = Jsoup.connect(url).get();
                //Using Elements to get the Meta data
                Elements mElementDataSize = mNewsHeadlineDocument.select("div.sidebar-box li");
                //locate the content attribute
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++)
                {
                    Elements mElementNewsHeadline = mNewsHeadlineDocument.select("div.sidebar-box li").eq(i);
                    String mNewsHeadline = mElementNewsHeadline.text();

                    mNewsHeadlineList.add(mNewsHeadline);
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void results)
        {
            // Set description into TextView
            RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.zambian_recyclerView);

            NewsHeadlinesZambianDataAdapter mNewsHeadlinesDataAdapter = new NewsHeadlinesZambianDataAdapter(NewsHeadlinesZambianFragment.super.getContext(), mNewsHeadlineList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mNewsHeadlinesDataAdapter);

            mProgressDialog.dismiss();
        }
    }

}
