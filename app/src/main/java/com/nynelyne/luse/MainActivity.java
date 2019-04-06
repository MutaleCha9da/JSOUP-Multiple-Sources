package com.nynelyne.luse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.nynelyne.luse.R.*;

public class MainActivity extends AppCompatActivity
{
    Toolbar toolbar;

    private ProgressDialog mProgressDialog;
    private String url = "http://www.luse.co.zm/";
    private ArrayList<String> mStockSymbolList = new ArrayList<>();
    private ArrayList<String> mStockPriceList = new ArrayList<>();
    private ArrayList<String> mStockChangeList = new ArrayList<>();
    private ArrayList<Integer> mPics = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        toolbar = (Toolbar)findViewById(id.toolBar);
        setSupportActionBar(toolbar);

        new Description().execute();
    }

    public class Description extends AsyncTask<Void, Void, Void>
    {
        String desc;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Today's Stock Price");
            mProgressDialog.setMessage("Updating Prices...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try {
                //Connect to the website
                Document mStockDocument = Jsoup.connect(url).get();
                //Using Elements to get the Meta data
                Elements mElementDataSize1 = mStockDocument.select("span[class=stoke_none]");
                Elements mElementDataSize2 = mStockDocument.select("span[class=stoke_up]");
                Elements mElementDataSize3 = mStockDocument.select("span[class=stoke_down]");
                //locate the content attribute
                int mElementSize = mElementDataSize1.size() + mElementDataSize2.size() + mElementDataSize3.size();

                for (int i = 0; i < mElementSize; i++)
                {
                    Elements mElementStockSymbol = mStockDocument.select("span[class=stoke_none], span[class=stoke_up], span[class=stoke_down]").eq(i);
                    String mStockSymbol = mElementStockSymbol.text();

                    Elements mElementStockPrice = mStockDocument.select("span[class=stoke_none_price], span[class=stoke_padd_up], span[class=stoke_down_padd]").eq(i);
                    String mStockPrice = mElementStockPrice.text();

                    Elements mElementStockChange = mStockDocument.select("span[class=stoke_none_change], span[class=stoke_none_up], span[class=stoke_none_down]").eq(i);
                    String mStockChange = mElementStockChange.text();

                    if (mStockDocument.select("span.stoke_down").size() > 0)
                    {
                        mStockSymbolList.add(mStockSymbol);
                        mStockPriceList.add(mStockPrice);
                        mStockChangeList.add(mStockChange);
                        mPics.add(drawable.downs);
                        System.out.println("Down is here");
                        break;
                    }
                    else if ((mStockDocument.select("span.stoke_up").size() > 0))
                    {
                        mStockSymbolList.add(mStockSymbol);
                        mStockPriceList.add(mStockPrice);
                        mStockChangeList.add(mStockChange);
                        mPics.add(drawable.ups);
                        System.out.println("Else if");
                        break;
                    }
                    else
                    {
                        mStockSymbolList.add(mStockSymbol);
                        mStockPriceList.add(mStockPrice);
                        mStockChangeList.add(mStockChange);
                        mPics.add(drawable.design_password_eye);
                        System.out.println("Else if 2");
                        break;
                    }


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
            RecyclerView mRecyclerView = (RecyclerView)findViewById(id.act_recyclerView);

            SharePriceDataAdapter mStockDataAdapter = new SharePriceDataAdapter(MainActivity.this, mStockSymbolList,mStockPriceList, mStockChangeList,mPics);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mStockDataAdapter);

            mProgressDialog.dismiss();
        }
    }
}
