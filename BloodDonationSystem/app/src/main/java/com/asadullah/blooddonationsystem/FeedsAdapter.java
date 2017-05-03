package com.asadullah.blooddonationsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asadullah.blooddonationsystem.Screens.PostDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by MASS-2016 on 2/26/2017.
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder>{

    ArrayList<BloodPost> list;
    Context ctx;

    public FeedsAdapter(Context c, ArrayList<BloodPost> data){
        list = data;
        ctx = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BloodPost post = list.get(position);
        Log.v("testing data adapter", list.get(0).bloodGroup);
        holder.tvName.setText(post.bloodGroup);
        holder.tvCurrentRequirement.setText(post.numOfUnits);
        holder.tvNumOfUnitsRequired.setText(post.city);
        holder.tvAddress.setText(post.country);
        holder.tvUrgency.setText(post.hospital);
        holder.tvExtraInfo.setText(post.urgency);
        holder.tvContact.setText(post.contact);
        holder.tvVolunteers.setText(post.relation);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvNumOfUnitsRequired;
        TextView tvAddress;
        TextView tvUrgency;
        TextView tvExtraInfo;
        TextView tvContact;
        TextView tvVolunteers;
        TextView tvCurrentRequirement;
        TextView tvbVolunteer;
        TextView tvbComment;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvNumOfUnitsRequired = (TextView) v.findViewById(R.id.tvRequiredUnits);
            tvAddress = (TextView) v.findViewById(R.id.tvAddress);
            tvUrgency = (TextView) v.findViewById(R.id.tvUrgency);
            tvExtraInfo = (TextView) v.findViewById(R.id.tvExtras);
            tvContact = (TextView)v.findViewById(R.id.tvContact);
            tvVolunteers = (TextView) v.findViewById(R.id.tvVolunteers);
            tvCurrentRequirement = (TextView) v.findViewById(R.id.tvCurrentRequirement);
            tvbVolunteer = (TextView)v.findViewById(R.id.tvbVolunteer);
            tvbComment = (TextView) v.findViewById(R.id.tvbComment);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(ctx, PostDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("post", list.get(getAdapterPosition()));
                    i.putExtras(bundle);
                    ctx.startActivity(i);
                }
            });

        }
    }
}
