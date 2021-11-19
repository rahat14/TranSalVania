package com.metacoder.transalvania.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacoder.transalvania.R;
import com.metacoder.transalvania.models.BikeModel;
import com.metacoder.transalvania.models.HotelModel;


public class viewholderForRentalList extends RecyclerView.ViewHolder {

    private static viewholderForRentalList.Clicklistener mclicklistener;
    View mview;

    public viewholderForRentalList(@NonNull View itemView) {
        super(itemView);

        mview = itemView;


        //item click
        itemView.setOnClickListener(v -> mclicklistener.onItemClick(v, getAbsoluteAdapterPosition()));


    }

    public static void setOnClickListener(Clicklistener clickListener) {


        mclicklistener = clickListener;


    }

    public void setDataToView(Context context, BikeModel model) {

        TextView title = mview.findViewById(R.id.nameTv);
        TextView desc = mview.findViewById(R.id.descTv);
        ImageView tripImage = mview.findViewById(R.id.image);
        RatingBar ratingBar = mview.findViewById(R.id.rateTv);

        ratingBar.setVisibility(View.GONE);


        title.setText(model.getName());

        desc.setText(model.getDesc());



        Glide.with(context)
                .load(model.getImage())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.placeholder)
                .into(tripImage);

    }

    public interface Clicklistener {

        void onItemClick(View view, int postion);

    }

}