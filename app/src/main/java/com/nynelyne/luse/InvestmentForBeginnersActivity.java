package com.nynelyne.luse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class InvestmentForBeginnersActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    String url = "http://www.luse.co.zm/investing_for_beginners/";
    private String get_value, get_date;
    private TextView textViewCommentary, textViewDate;

    private static final String TAG = "Logging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_for_beginners);

        textViewCommentary = (TextView)findViewById(R.id.tv_investment_for_beginner);
        textViewCommentary.setMovementMethod(new ScrollingMovementMethod());

        mProgressDialog = new ProgressDialog(InvestmentForBeginnersActivity.this);
        mProgressDialog.setTitle("Investment for Beginners");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {

                    // marked for your use
                    Document doc = Jsoup.connect(url).get();
                    Element element1 = doc.select("div[class=col-md-9 margin20]").first();
                    //Element element2 = doc.select("h6[class=td-font]").first();

                    get_value = element1.text();
                    //get_date = element2.text();

                    //Log.e("this log", get_value);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //String finalDate = get_date.replaceFirst("Closing Prices - ", "");

                            textViewCommentary.setText(get_value);
                            //textViewDate.setText(finalDate);

                            mProgressDialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    //Log.e(TAG, e.toString());
                }
            }
        }).start();
    }

}
