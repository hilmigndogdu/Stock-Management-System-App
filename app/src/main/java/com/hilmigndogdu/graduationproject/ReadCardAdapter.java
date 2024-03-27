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

public class ReadCardAdapter extends RecyclerView.Adapter<ReadCardAdapter.ObjectHolder> {

    private Context context;

    private List<Cards> cardsList;

    public ReadCardAdapter(Context context, List<Cards> cardsList){

        this.context = context;
        this.cardsList = cardsList;
    }

    public class ObjectHolder extends RecyclerView.ViewHolder{

        public ImageView cardImg;
        public TextView productName;
        public TextView categoryName;
        public TextView brandName;
        public Button selectBtn;
        public ObjectHolder(@NonNull View itemView) {
            super(itemView);

            cardImg = itemView.findViewById(R.id.productimg);
            productName = itemView.findViewById(R.id.productTxt);
            categoryName = itemView.findViewById(R.id.categoryTxt);
            brandName = itemView.findViewById(R.id.brandTxt);
            selectBtn = itemView.findViewById(R.id.selectBtn);
        }
    }

    @NonNull
    @Override
    public ObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);

        return new ObjectHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ObjectHolder holder, int position) {
        Cards card = cardsList.get(position);

        holder.productName.setText(card.getProductName());
        holder.categoryName.setText(card.getCategoryName());
        holder.brandName.setText(card.getBrandName());
        holder.cardImg.setImageResource(context.getResources()
                .getIdentifier(card.getImageName(),"drawable", context.getPackageName()));


        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "SELECTED Product : " + card.getProductName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {

        return cardsList.size();

    }

}
