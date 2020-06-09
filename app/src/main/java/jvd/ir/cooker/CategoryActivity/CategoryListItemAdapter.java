package jvd.ir.cooker.CategoryActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.R;

public class CategoryListItemAdapter extends RecyclerView.Adapter<CategoryListItemAdapter.CategoryViewModel> {

    List<FoodModel> foodModels;

    setOnFoodClick onFoodClick;

    public CategoryListItemAdapter(List<FoodModel> foodModels,setOnFoodClick onFoodClick){
        this.onFoodClick=onFoodClick;
        this.foodModels=foodModels;
    }

    @NonNull
    @Override
    public CategoryViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_adapter,parent,false);
        return new CategoryViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewModel holder, int position) {

        final FoodModel model=foodModels.get(position);

        holder.txtTitle.setText(model.getTitle());
        holder.txtLike.setText(model.getLikes());
        holder.txtChefId.setText(model.getCheefId());
        holder.txtCatFrom.setText(model.getCatFrom());
        Picasso.get().load(model.getImage()).into(holder.image);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFoodClick.onClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class CategoryViewModel extends RecyclerView.ViewHolder{
        TextView txtTitle,txtChefId,txtLike,txtCatFrom;
        ImageView imgLike;
        CardView parent;
        ImageView image;

        public CategoryViewModel(@NonNull View itemView) {
            super(itemView);
            txtChefId = itemView.findViewById(R.id.txt_catItem_chefId);
            txtLike = itemView.findViewById(R.id.txt_catItem_like);
            txtTitle = itemView.findViewById(R.id.txt_catItem_title);
            imgLike = itemView.findViewById(R.id.img_catItem_like);
            image = itemView.findViewById(R.id.img_catItem_image);
            parent = itemView.findViewById(R.id.card_catItem_parent);
            txtCatFrom = itemView.findViewById(R.id.txt_catItem_catFrom);
        }
    }

    public interface setOnFoodClick{
        void onClick(FoodModel foodModel);
    }
}
