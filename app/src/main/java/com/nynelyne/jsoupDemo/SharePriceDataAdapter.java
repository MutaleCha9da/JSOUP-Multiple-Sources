package com.nynelyne.jsoupDemo;

import android.app.Activity;
import android.graphics.Color;
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
    private ArrayList<String> mPics = new ArrayList<>();
    private ArrayList<Integer> rowColor = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;

    public SharePriceDataAdapter(MainActivity activity, ArrayList<String> mStockSymbolList, ArrayList<String> mStockPriceList, ArrayList<String> mStockChangeList, ArrayList<String> mPics, ArrayList<Integer> rowColor)
    {
        this.mStockSymbolList = mStockSymbolList;
        this.mStockPriceList = mStockPriceList;
        this.mStockChangeList = mStockChangeList;
        this.mPics = mPics;
        this.rowColor = rowColor;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView_stock_symbol, textView_stock_price, textView_stock_change, textView_icon;

        public MyViewHolder(View view)
        {
            super(view);
            textView_stock_symbol = (TextView) itemView.findViewById(R.id.row_tv_stock_symbol);
            textView_stock_price = (TextView) itemView.findViewById(R.id.row_tv_stock_price);
            textView_stock_change = (TextView) itemView.findViewById(R.id.row_tv_stock_change);
            textView_icon = (TextView) itemView.findViewById(R.id.row_tv_stock_movement);
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
        /**/
        int tvColor = Color.BLACK;
        if (rowColor.get(position).equals(1)){
            tvColor = Color.BLACK;
        } else if (rowColor.get(position).equals(2)){
            tvColor = Color.GREEN;
        } else if (rowColor.get(position).equals(3)){
            tvColor = Color.RED;
        }
        /**/

        holder.textView_stock_symbol.setText(mStockSymbolList.get(position));
        holder.textView_stock_symbol.setTextColor(tvColor);

        holder.textView_stock_price.setText(mStockPriceList.get(position));
        holder.textView_stock_price.setTextColor(tvColor);

        holder.textView_stock_change.setText(mStockChangeList.get(position));
        holder.textView_stock_change.setTextColor(tvColor);

        holder.textView_icon.setText(mPics.get(position));
        holder.textView_icon.setTextColor(tvColor);
        /**/

        System.out.println("Color Num: "+rowColor.get(position));

    }

    @Override
    public int getItemCount()
    {
        return mStockSymbolList.size();
    }
}
