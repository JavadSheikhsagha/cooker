package jvd.ir.cooker.MainActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lid.lib.LabelImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.R;

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.RvViewHolder> {

    List<FoodModel> foods;
    setOnFoodClick onFoodClick;
    Context context;

    public MainRvAdapter(Context context, List<FoodModel> foods, setOnFoodClick onFoodClick){
        this.foods=foods;
        this.onFoodClick=onFoodClick;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_adapter,parent,false);
        return new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        final FoodModel foodModel=foods.get(position);

        holder.txtUser.setText(foodModel.getCheefId());
        holder.txtTitle.setText(foodModel.getTitle());
        holder.txtLikes.setText(foodModel.getLikes());
        Picasso.get().load(foodModel.getImage()).into(holder.image);

        if (foodModel.getmNew().equals("0")){
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

    public class RvViewHolder extends RecyclerView.ViewHolder{

        ImageView imgLike;
        TextView txtTitle,txtUser,txtLikes;
        CardView parent;
        LabelImageView image;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image1);
            imgLike = itemView.findViewById(R.id.img_mainRv_like);
            txtLikes = itemView.findViewById(R.id.txt_mainRv_likes);
            txtTitle = itemView.findViewById(R.id.txt_mainRv_title);
            txtUser = itemView.findViewById(R.id.txt_mainRv_user);
            parent = itemView.findViewById(R.id.card_mainRv_parent);

        }
    }

    public interface setOnFoodClick{
        void onClick(FoodModel foodModel);
    }
}
