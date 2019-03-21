package com.nynelyne.luse;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mutale Chanda on 23/02/2019.
 */
public class SharePriceDataAdapter extends RecyclerView.Adapter<SharePriceDataAdapter.MyViewHolder>
{
    private ArrayList<String> mStockSymbolList = new ArrayList<>();
    private ArrayList<String> mStockPriceList = new ArrayList<>();
    private ArrayList<String> mStockChangeList = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;

    public SharePriceDataAdapter(MainActivity activity, ArrayList<String> mStockSymbolList, ArrayList<String> mStockPriceList, ArrayList<String> mStockChangeList)
    {
        this.mStockSymbolList = mStockSymbolList;
        this.mStockPriceList = mStockPriceList;
        this.mStockChangeList = mStockChangeList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView_stock_symbol, textView_stock_price, textView_stock_change;

        public MyViewHolder(View view)
        {
            super(view);
            textView_stock_symbol = (TextView) itemView.findViewById(R.id.row_tv_stock_symbol);
            textView_stock_price = (TextView) itemView.findViewById(R.id.row_tv_stock_price);
            textView_stock_change = (TextView) itemView.findViewById(R.id.row_tv_stock_change);
            //textView_stock_change.setBackgroundResource(R.drawable.ups);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_stock_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.textView_stock_symbol.setText(mStockSymbolList.get(position));
        holder.textView_stock_price.setText(mStockPriceList.get(position));
        holder.textView_stock_change.setText(mStockChangeList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mStockSymbolList.size();
    }
}
