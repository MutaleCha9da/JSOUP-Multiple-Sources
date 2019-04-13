package com.nynelyne.luse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
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

    String htmlPage = "<p>\n" +
            "            The stock market is a crucial financial institution, which fulfills the need of capital that businesses are looking for, while letting investors profit as shareholders in companies, creating a win-win platform for both, investors and businesses. However, making money on the stock market is another ball-game altogether. Trading stocks requires a substantial amount of study and understanding, before you put your hard-earned money on the line and begin making profits. The following information attempts to give an insight into the working of the stock market and what stock investing entails.\n" +
            "        </p>\n" +
            "        <h3>What are Stocks?</h3>\n" +
            "\n" +
            "        <p>Rather than giving a detailed definition of this basic unit of trading, let’s take a look at a small example of how stocks are created. It will also be a lesson on what they are and how they function.</p>\n" +
            "\n" +
            "        <p>An entrepreneur finds an unexploited niche of the economy, an area where there is latent demand and can be fulfilled by launching certain products. To go about this, he needs to establish a company which will produce, market and sell this product. However, before he realizes his dream of becoming the next billionaire, he must answer a very basic question- Does he have the money to do these things? The answer, in most cases, will be in the negative. How then does one go about raising money, or more formally, raising capital? The easiest, safest and quickest way to do this is to offer the public a stake in his enterprise, a small portion of holding for which they shall pay a particular sum. This holding is known as stock, the unit of which is a share. One may hold stocks in IBM, but buy a hundred shares of Exxon. The entrepreneur now has sufficient money to buy equipment, rent space, hire workers, run an assembly line and market his revolutionary product. The shareholders – the people who purchased stock in his company – have limited exposure to risk, only to the extent of their holdings, if the venture suddenly folds.\n" +
            "        </p>\n" +
            "        <p>\n" +
            "            A company goes public or becomes a joint-stock company, when it sells a part of its equity holding to raise capital, through an IPO (Initial Public Offering). The stocks are sold at a fixed value in this initial sale, after which they can be traded on the secondary market, which is the stock market. The price of shares depends upon several things, the company’s profitability being one of the prime factors. If our entrepreneur can make decent profits from the word go, the price of his stock shall increase as people compete to purchase the same number of shares, however, if he runs into losses, the price will go down.\n" +
            "        </p>\n" +
            "        <p>Stocks can be of the following types:</p>\n" +
            "\n" +
            "        <h3>Preferred Stock:</h3>\n" +
            "\n" +
            "        <p>Preference stockholders are granted privileges over and above that of common stockholders. They are distributed dividend before the common stockholders, and also hold a higher claim when it comes to asset distribution, in event of the liquidation of the company.</p>\n" +
            "\n" +
            "        <h3>Common Stock:</h3>\n" +
            "\n" +
            "        <p>This is the equity that a company offers its stockholders as ownership. The common stockholders have voting rights and are invited to the annual general meetings of the corporation. They can vote for selection of management and receive dividends from the payouts of the company’s profits.</p>\n" +
            "\n" +
            "        <h3>Other investment vehicles</h3>\n" +
            "\n" +
            "        <h3>Stock derivatives:</h3>\n" +
            "\n" +
            "        <p>When a financial instrument derives its value from the price of the stocks that support it, it’s called a stock derivative. Most often the underlying stock is an index, which fluctuates with time, changing the values of the derivatives. The most common types of derivatives are futures and options.</p>\n" +
            "\n" +
            "        <h3>Bonds:</h3>\n" +
            "\n" +
            "        <p>Companies and governments often issue bonds to meet their working capital requirements. These bonds are issued as a form of loan, the purchaser is the lender and the issuer is the borrower. There is a fixed rate of interest to be paid on bonds and the full amount is redeemed at the end of the maturity period.</p>\n" +
            "\n" +
            "        <h3>Stock Trading</h3>\n" +
            "\n" +
            "        <p>After an IPO has been made, a company’s stocks can trade on the stock exchange. A stock exchange is like a market for stocks and traders. It is a place where people willing to buy stocks meet those willing to sell them, and speculation in future prices and profits is what drives the trade. Stock trading these days is usually undertaken by stockbrokers on behalf of traders, who buy and sell shares according to market conditions. With the advent of the Internet in the business sphere, the virtual trading terminal has become the accepted norm of stock investing. Here, traders working on computer terminals bid through computers within a network, and provide investors with online accounts to buy and sell stocks. One does not need to go to the stock market to know the stock prices and quotes, it can be done from the home or the office with the help of a laptop and an Internet connection.</p>\n" +
            "\n" +
            "        <p>Every stock market has an Index, which is based on statistical calculations and gives an idea about how listed stocks are performing. Examples include the Lusaka Securities Exchange All Share Index (LASI). Stock exchanges may have specific requirements for companies who want to list their stocks on them, refer to page <a href=\" http://www.luse.co.zm/listing_requirements/\">http://www.luse.co.zm/listing_requirements/</a> on our website.</p>\n" +
            "\n" +
            "        <h3>Buying</h3>\n" +
            "\n" +
            "        <p>Stocks can be bought on the stock exchange with the help of a broker, or they may be purchased directly from the company. Most stockbrokers are listed with the stock exchange, giving them the authority to buy stocks on behalf of a trader.  Stocks can be purchased when their prices are low, so as to make a profit when prices rise, or they can be purchased at a premium when there is speculation that the company, or even the economy in general is experiencing phenomenal growth and the prices will rise further.</p>\n" +
            "\n" +
            "        <h3>Selling</h3>\n" +
            "\n" +
            "        <p>Selling is much the same as purchasing, except that one may have to pay the capital gains taxes on the proceeds earned by the way of sales, if these come within the limits of the taxation law. Selling can be done when the seller is anticipating further losses or maybe when the prices have reached a peak, from which there is a chance of a decline, and the seller wants to short-sell to make a good profit.</p>\n" +
            "\n" +
            "        <h3>Bidding</h3>\n" +
            "\n" +
            "        <p>Traders gather and communicate their individual stock quotes on the exchange floor, a process called bidding where the stock price changes with every bid and stops only when a bid is singled out as the highest. Traders aim to Buy stock cheap and sell to make profits for their investors and themselves.</p>\n" +
            "\n" +
            "        <h3>Stock prices</h3>\n" +
            "\n" +
            "        <p>Another question most beginners come up with is, how are these stock prices determined? A stock exchange is the perfect example of the Law of Demand and Supply in action. At any given point in time, the total number of shares on offer, called float, is in equilibrium with the total demand, the number of shares traders wish to buy. This is what the term market capitalization refers to, the demand and supply equilibrium of any corporation listed on the stock exchange, the total value of the company’s stock at that point in time.</p>\n" +
            "\n" +
            "        <h3>Investing for Beginners: Planning your Investments</h3>\n" +
            "\n" +
            "        <p>Now that you have a basic idea of what the stock market is all about, we can move on to the next step, knowing how to go about investing. Once you have decided the amount of money you are willing to invest, it is time to make a comparative analysis of some popular stock options. It is important to identify companies that have a reliable consumer base and are market leaders, they face healthy competition yet innovate to stay on top of their game. Whatever your ultimate decision may be, it can be helpful to take a look for the following characteristics, in a company you have targeted for investment.</p>\n" +
            "\n" +
            "        <h3>Differentiating between price and value</h3>\n" +
            "\n" +
            "        <p>This is probably the most important factor when one considers investing in stocks. There is often a marked difference between the price at which a share might trade and the value that is actually has. Stock value is an amalgamation of several factors which are more micro-economic and related to the company rather than to outside variables. They can be along these lines:</p>\n" +
            "\n" +
            "        <ul>\n" +
            "            <li>Profits over time, past present and speculated</li>\n" +
            "            <li>Sales volume</li>\n" +
            "            <li>Credit ratings</li>\n" +
            "            <li>Current competitors and future probables</li>\n" +
            "        </ul>\n" +
            "        <p>The price of stocks, on the other hand, are influenced by the law of demand and supply operating on the exchange floor, during the session of trading. A number of buyers and sellers come together and agree on a specific price, which in turn may be hugely inflated or deflated depending on the current economic conditions and general mood of the investing community. This may be due to several macro factors such as:</p>\n" +
            "\n" +
            "        <ul>\n" +
            "            <li>National and international news</li>\n" +
            "            <li>Economic forecasts</li>\n" +
            "        </ul>\n" +
            "        <p>One must be careful while buying or selling stocks and take into account not just the price of the stocks but also their value, which may often be completely at odds with each other. Trading sense is often overtaken by herd behavior, when a rumor or irrational speculation pushes or lowers prices of the stocks in a drastic manner.</p>\n" +
            "\n" +
            "\n" +
            "        <h3>Some Important Tips</h3>\n" +
            "\n" +
            "        <p>Before you take investment decisions, ask yourself the following questions:</p>\n" +
            "        <ul>\n" +
            "            <li>Am I investing from disposable income, or rainy-day savings?</li>\n" +
            "            <li>Do I have additional money to invest if another opportunity arises or am I locked into one stock?</li>\n" +
            "            <li>Should I consider investing in something that has less volatile price movements?</li>\n" +
            "            <li>Is my current portfolio the optimum utilization of my finances?</li>\n" +
            "        </ul>\n" +
            "        <p>\n" +
            "            These introspective questions are necessary to keep you on the right track and focused on market movements. Intelligent investing is about picking solid stocks with earning potential. Let logic and pure technical analysis of a stock guide you.</p>\n" +
            "\n" +
            "        <p>Trading in stocks gives solid returns in the long term, do not expect to blaze a trail in your first week, the stock market is not a flick, no matter how exciting the connection seems. Quick trading is fun, you can buy and sell on the go, study economic trends and change your portfolio accordingly and also make a profit. However, it may not always be the way to go with stocks of large cap companies with strong businesses.</p>\n" +
            "\n" +
            "        <h3>1. Don’t fear fluctuations</h3>\n" +
            "\n" +
            "        <p>The market moves in mysterious ways, and price fluctuations can be numerous and sudden. Although the pricing mechanism in stock markets works extremely well, it is often prey to sentiment and investor emotion. Bearish trends can deflate stocks very quickly, just as bullish sentiment can take them to pinnacles of over-valuation. It is best not to panic in such circumstances but make an informed decision. Buying undervalued stocks requires a good judgment of future stock movements, similarly knowing when to sell has its own benefits, as over-valued stocks soon return to earth and its clever to book profits when one has the chance.</p>\n" +
            "\n" +
            "        <h3>2. Keep a margin of safety</h3>\n" +
            "\n" +
            "        <p>Keeping a margin of safety helps you reduce potential losses in the long run. Despite all the research you may have done, the future is uncertain and it’s always prudent to keep a margin of 15 – 20 % on the stock price. This buffer will help you tide over sudden corrections the market may experience.</p>\n" +
            "\n" +
            "        <h3>3. Stay true to your plans</h3>\n" +
            "\n" +
            "        <p>Once you have chartered an investment route for yourself, by deciding the amount you will invest, choosing your stocks and the time you plan to hold on to them, its best to stick to it as you begin investing. Think independently, do your own research and make informed decisions, rather than trading on impulse and risking your investment.</p>\n" +
            "\n" +
            "        <p>Stock investing for beginners can seem a challenge, but with a clear investment strategy in mind, one can go about it in a profitable manner. Patience and a cool-headed approach toward investment decisions will definitely return profits in due time. The working of the stock market, its pulse, is something one can grasp only after spending some time being part of the great machine of stock trading. There will be failures in the beginning, hasty investments which may incur losses, but it’s important to remain focused and to keep working on improving your portfolio of stocks. With time and experience one can make stock trading a profitable enterprise.</p>\n" +
            "\n" +
            "\n" +
            "\n" +
            "        <h3 style=\"text-align:center\"><u>STEP BY STEP PROCESS TO INVESTING ON THE LuSE</u></h3>\n" +
            "\n" +
            "        <p>Every investor with the intention of investing on the Lusaka Securities Exchange is required to follow the criteria below in order to complete the process of investing.</p>\n" +
            "\n" +
            "        <h3><u>STEP ONE</u> – Open a Brokerage Account</h3>\n" +
            "\n" +
            "        <p>Open a brokerage account with any of our member Stockbrokers by providing the following information</p>\n" +
            "        <ul>\n" +
            "            <li>Proof of Residence</li>\n" +
            "            <li>Proof of Identity</li>\n" +
            "            <li>Completed Brokerage Account Opening Form</li>\n" +
            "            <li>Investment Amount (a fixed commission rate of 1.375% per transaction is charged)</li>\n" +
            "        </ul>\n" +
            "\n" +
            "        <h3><u>STEP TWO</u> – Buy Shares</h3>\n" +
            "\n" +
            "        <p>Purchase of shares through any of the registered and licensed Stockbrokers of your choice. These are professionals trained to give you the necessary information in order to make an informed decision. Click on link to see list of Stockbrokers <a href=\"http://www.luse.co.zm/brokerage_firms/\"></a>http://www.luse.co.zm/brokerage_firms/</p>\n" +
            "\n" +
            "        <h3><u>STEP THREE</u> – Monitor your investment by;</h3>\n" +
            "\n" +
            "        <ul>\n" +
            "            <li>Staying in touch with your Stockbroker</li>\n" +
            "            <li>Observing corporate actions</li>\n" +
            "            <li>Attend Annual General Meetings</li>\n" +
            "            <li>Looking out for prices published in Daily papers</li>\n" +
            "            <li>Looking out for prices scrolled during ZNBC main news</li>\n" +
            "\n" +
            "            <li>Visit the LuSE website regularly and like us on Facebook (Lusaka Securities Exchange) and follow us on Twitter (@LuSE_PLC) Read more by downloading our Frequently Asked Questions here</li>\n" +
            "        </ul>\n" +
            "        <p>For more information, kindly refer to the Brokerage Firms Page and/ or email us at <a href=\"#\">info@luse.co.zm/ phil@luse.co.zm/ kennedy@luse.co.zm</a></p></p>\n" +
            "    ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_for_beginners);

        textViewCommentary = (TextView)findViewById(R.id.tv_investment_for_beginner);
        textViewCommentary.setText(Html.fromHtml(htmlPage));
        textViewCommentary.setMovementMethod(new ScrollingMovementMethod());

        /*mProgressDialog = new ProgressDialog(InvestmentForBeginnersActivity.this);
        mProgressDialog.setTitle("Investment for Beginners");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
*/


       /* new Thread( new Runnable() {
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
        }).start();*/
    }

}
