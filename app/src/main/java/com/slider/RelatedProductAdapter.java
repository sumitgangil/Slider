package com.slider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sumit on 6/2/2017.
 */

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.MyViewHolder> {

    private ArrayList<DataModelRelatedProducts> datamodel = new ArrayList<DataModelRelatedProducts>();
    Context ctx;
    ProgressDialog mProgressDialog;

    public RelatedProductAdapter(ArrayList<DataModelRelatedProducts> datamodel, Context ctx) {
        this.datamodel = datamodel;
        this.ctx = ctx;

    }

    @Override
    public RelatedProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_relatedwork_products, parent, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final RelatedProductAdapter.MyViewHolder myViewHolder = new RelatedProductAdapter.MyViewHolder(view, ctx, datamodel);

        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Toast.makeText(ctx, "Welcome", Toast.LENGTH_SHORT).show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RelatedProductAdapter.MyViewHolder holder, final int listPosition) {

        DataModelRelatedProducts data = datamodel.get(listPosition);


        mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setMessage("Loading........");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);


        Picasso.with(ctx)
                .load(data.getImageUrl())
                .placeholder(R.drawable.back_image)
                .error(R.drawable.adscend_profile_icon)
                .fit()
                .into(holder.ivProduct);

        holder.tvProductName.setText(data.getProductname());


    }

    @Override
    public int getItemCount() {
        return datamodel.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName;

        ImageView ivProduct;

        ArrayList<DataModelRelatedProducts> datamodel = new ArrayList<DataModelRelatedProducts>();
        Context ctx;

        public MyViewHolder(View itemView, Context ctx, ArrayList<DataModelRelatedProducts> datamodel) {
            super(itemView);
            this.datamodel = datamodel;
            this.ctx = ctx;

            this.ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            this.tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);

        }


    }

}
