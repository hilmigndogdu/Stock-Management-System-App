package com.hilmigndogdu.graduationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewObjectHolder> {

    private Context mContext;
    private List<Cards> cardsList;

    public CardAdapter(Context mContext, List<Cards> cardsList) {
        this.mContext = mContext;
        this.cardsList = cardsList;
    }

    public class CardViewObjectHolder extends RecyclerView.ViewHolder{

        public ImageView productImg;
        public TextView productName;
        public TextView categoryName;
        public TextView brandName;
        public Button selectBtn;

        public CardViewObjectHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productimg);
            productName = itemView.findViewById(R.id.productTxt);
            categoryName = itemView.findViewById(R.id.categoryTxt);
            brandName = itemView.findViewById(R.id.brandTxt);
            selectBtn = itemView.findViewById(R.id.selectBtn);
        }
    }

    @NonNull
    @Override
    public CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
        return new CardViewObjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjectHolder holder, int position) {

        Cards card = cardsList.get(position);

        holder.productImg.setImageResource(mContext.getResources().getIdentifier(card.getImageName(),"drawable",mContext.getPackageName()));
        holder.productName.setText(card.getProductName());
        holder.categoryName.setText(card.getCategoryName());
        holder.brandName.setText(card.getCategoryName());
        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }
}
