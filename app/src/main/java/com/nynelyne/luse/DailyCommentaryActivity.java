package com.nynelyne.luse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DailyCommentaryActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private String url = "http://www.luse.co.zm/";
    private String get_value, get_date;
    private TextView textViewCommentary, textViewDate;

    private static final String TAG = "Logging Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_commentary);

        textViewCommentary = (TextView)findViewById(R.id.tv_daily_commentary);
        textViewDate = (TextView)findViewById(R.id.tv_date_updated);

        mProgressDialog = new ProgressDialog(DailyCommentaryActivity.this);
        mProgressDialog.setTitle("Daily Commentary");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {

                    // marked for your use
                    Document doc = Jsoup.connect(url).get();
                    Element element1 = doc.select("div[class=tab-pane active]").first();
                    Element element2 = doc.select("h6[class=td-font]").first();

                    get_value = element1.text();
                    get_date = element2.text();

                    //Log.e("this log", get_value);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String finalDate = get_date.replaceFirst("Closing Prices - ", "");

                            textViewCommentary.setText(get_value);
                            textViewDate.setText(finalDate);

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
