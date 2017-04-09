package com.cgapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgapp.R;
import com.cgapp.fragment.SendFragment;

import java.util.List;

/**
 * Created by asus on 2017/3/28.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> titles;
    private List<String> images;
    private final SendFragment context;
    //private final LayoutInflater inflater;

    public RecyclerAdapter(SendFragment context, List<String> titles,List<String> images)
    {
        this.context = context;
        this.titles = titles;
        this.images = images;
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

    /**
     * 很明显是操作
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
        //holder.imageView.setImageResource(images.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.card_text);
            imageView = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }



    @Override
    public int getItemCount() {
        return titles.size();
    }
}
