package jvd.ir.cooker.DetailActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.Model.FoodIngredients;
import jvd.ir.cooker.R;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    List<FoodIngredients> ingredients;
    Context context;
    int number;

    public IngredientsAdapter(Context context, List<FoodIngredients> ingredients, int number) {
        this.ingredients = ingredients;
        this.number = number;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ings_rv_adapter, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientsViewHolder holder, int position) {
        FoodIngredients food = ingredients.get(position);

        holder.txtTitle.setText(food.getIngTitle());
        int amount = Integer.parseInt(food.getAmount());
        amount = amount * number;
        String newAmount = amount + " " + food.getUnit();
        holder.txtAmount.setText(newAmount);

        if (position % 2 > 0) {
            holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite1));
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parent;
        TextView txtTitle, txtAmount;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.rel_ingRv_parent);
            txtAmount = itemView.findViewById(R.id.txt_ingRv_amount);
            txtTitle = itemView.findViewById(R.id.txt_ingRv_title);

        }
    }
}
