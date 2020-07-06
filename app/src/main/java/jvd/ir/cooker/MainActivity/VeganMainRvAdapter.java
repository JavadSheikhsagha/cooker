package jvd.ir.cooker.MainActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lid.lib.LabelImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.R;

public class VeganMainRvAdapter extends RecyclerView.Adapter<VeganMainRvAdapter.VeganViewHolder> {

    List<FoodModel> foods;

    setOnFoodClick onFoodClick;

    public VeganMainRvAdapter(List<FoodModel> food,setOnFoodClick onFoodClick){
        this.foods=food;
        this.onFoodClick=onFoodClick;
    }

    @NonNull
    @Override
    public VeganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vegan_rv_adapter,parent,false);
        return new VeganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeganViewHolder holder, int position) {
        final FoodModel foodModel=foods.get(position);

        holder.txtTitle.setText(foodModel.getTitle());
        holder.txtLikes.setText(foodModel.getLikes());
        holder.txtUser.setText(foodModel.getCheefId());
        Picasso.get().load(foodModel.getImage()).placeholder(R.drawable.applogosplash).into(holder.image);

        if (!foodModel.getmNew().equals("1")){
            holder.image.setLabelVisual(false);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFoodClick.onClick(foodModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class VeganViewHolder extends RecyclerView.ViewHolder{

        ImageView imgLike;
        TextView txtTitle,txtUser,txtLikes;
        CardView parent;
        LabelImageView image;

        public VeganViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_veganRv_image);
            imgLike = itemView.findViewById(R.id.img_veganRv_like);
            txtLikes = itemView.findViewById(R.id.txt_veganRv_likes);
            txtTitle = itemView.findViewById(R.id.txt_veganRv_title);
            txtUser = itemView.findViewById(R.id.txt_veganRv_user);
            parent = itemView.findViewById(R.id.card_veganRv_parent);

        }
    }

    public interface setOnFoodClick{
        void onClick(FoodModel foodModel);
    }
}
