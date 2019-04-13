package com.nynelyne.luse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.nynelyne.luse.R.*;

public class MainActivity extends AppCompatActivity
{
    Toolbar toolbar;
    TextView tv_lastUpdated;
    String get_value;

    private ProgressDialog mProgressDialog;
    private String url = "http://www.luse.co.zm/";
    private ArrayList<String> mStockSymbolList = new ArrayList<>();
    private ArrayList<String> mStockPriceList = new ArrayList<>();
    private ArrayList<String> mStockChangeList = new ArrayList<>();
    private ArrayList<String> mPics = new ArrayList<>();
    private ArrayList<Integer> rowColor = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        toolbar = (Toolbar)findViewById(id.toolBar);
        setSupportActionBar(toolbar);

        tv_lastUpdated = (TextView)findViewById(id.lastUpdated);
        lastUpdated();

        new Description().execute();
    }

    private void lastUpdated() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    // marked for your use
                    Document doc = Jsoup.connect(url).get();
                    Element element = doc.select("h6[class=td-font]").first();

                    get_value = element.text();

                    //Log.e("this log", get_value);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tv_lastUpdated.setText(get_value);
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
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

                    if (mElementStockSymbol.attr("class").equals("stoke_down"))
                    {
                        mPics.add(getResources().getString(string.fa_icon_stock_down));
                        rowColor.add(3);
                    }
                    else if (mElementStockSymbol.attr("class").equals("stoke_up"))
                    {
                        mPics.add(getResources().getString(string.fa_icon_stock_up));
                        rowColor.add(2);
                    }
                    else
                    {
                        mPics.add(getResources().getString(string.fa_icon_stock_none));
                        rowColor.add(1);
                    }
                    mStockSymbolList.add(mStockSymbol);
                    mStockPriceList.add(mStockPrice);
                    mStockChangeList.add(mStockChange);


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

            SharePriceDataAdapter mStockDataAdapter = new SharePriceDataAdapter(MainActivity.this, mStockSymbolList,mStockPriceList, mStockChangeList,mPics,rowColor);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mStockDataAdapter);

            mProgressDialog.dismiss();
        }
    }
}
