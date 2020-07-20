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
import java.util.ArrayList;
import java.util.List;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.R;

public class RvAllCatAdapter extends RecyclerView.Adapter<RvAllCatAdapter.AllCatViewHolder> {

    List<CategoryModel> cats=new ArrayList<>();

    onFoodCatClick foodCatClick;

    public RvAllCatAdapter(List<CategoryModel> list){
        this.cats=list;
    }

    @NonNull
    @Override
    public AllCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_allcats_item,parent,false);
        return new AllCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCatViewHolder holder, int position) {
        final CategoryModel model=cats.get(position);

        holder.txtTitle.setText(model.getCategory());
        if (!model.getImage().isEmpty()){
            Picasso.get().load(model.getImage()).into(holder.image);
            holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodCatClick.onclick(model.getmPosition());
            }
        });

    }

    public void setFoodCatClick(onFoodCatClick foodCatClick) {
        this.foodCatClick = foodCatClick;
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class AllCatViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView txtTitle;
        CardView parent;

        public AllCatViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.card_allCatsItem_parent);
            image = itemView.findViewById(R.id.img_allCatsItem_image);
            txtTitle= itemView.findViewById(R.id.txt_allCatsItem_title);
        }
    }

    public interface onFoodCatClick{
        void onclick(String position);
    }
}
