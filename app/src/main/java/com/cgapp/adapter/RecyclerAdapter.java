package com.cgapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cgapp.R;
import com.cgapp.fragment.SendFragment;

import java.util.List;

/**
 * Created by asus on 2017/3/28.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> items;
    //private final SendFragment context;
    //private final LayoutInflater inflater;

    public RecyclerAdapter(SendFragment context, List<String> items)
    {
        //this.context = context;
        this.items = items;
    }

    /**
     * ViewHolder布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.card_text);
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}
