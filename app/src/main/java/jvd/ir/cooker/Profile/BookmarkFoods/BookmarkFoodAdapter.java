package jvd.ir.cooker.Profile.BookmarkFoods;

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

import jvd.ir.cooker.R;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;

public class BookmarkFoodAdapter extends RecyclerView.Adapter<BookmarkFoodAdapter.BookmarkFoodViewHolder> {

    List<FoodModel2> foods;

    setOnFoodClick onFoodClick;

    public BookmarkFoodAdapter(List<FoodModel2> foods){
        this.foods=foods;
    }
    @NonNull
    @Override
    public BookmarkFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_food_adapter,parent,false);
        return new BookmarkFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkFoodViewHolder holder, int position) {
        final FoodModel2 model=foods.get(position);

        holder.txtTitle.setText(model.getTitle());
        holder.txtCatFrom.setText(model.getCategory());
        holder.txtChefId.setText(model.getChefId());

        Picasso.get().load(model.getImage()).placeholder(R.drawable.applogo).into(holder.image);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFoodClick.onclick(model);
            }
        });

    }

    public void setOnFoodClick(setOnFoodClick onFoodClick) {
        this.onFoodClick = onFoodClick;
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class BookmarkFoodViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
        ImageView image;
        TextView txtTitle,txtChefId,txtCatFrom;
        public BookmarkFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.card_bookmark_parent);
            image = itemView.findViewById(R.id.img_bookmark_image);
            txtTitle = itemView.findViewById(R.id.txt_bookmark_title);
            txtChefId = itemView.findViewById(R.id.txt_bookmark_chefId);
            txtCatFrom = itemView.findViewById(R.id.txt_bookmark_catFrom);
        }
    }

    public interface setOnFoodClick{
        void onclick(FoodModel2 model);
    }
}
