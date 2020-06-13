package jvd.ir.cooker.RoomDatabase.FoodIngredients;

import android.content.Context;
import android.view.LayoutInflater;
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

public class IngsRvAdapter extends RecyclerView.Adapter<IngsRvAdapter.IngsViewHolder> {


    List<IngsModel2> ingredients;
    Context context;
    int number;

    public IngsRvAdapter(Context context, List<IngsModel2> ingredients, int number) {
        this.ingredients = ingredients;
        this.number = number;
        this.context = context;
    }

    @NonNull
    @Override
    public IngsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_rv_adapter_room,parent,false);
        return new IngsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngsViewHolder holder, int position) {
        IngsModel2 food = ingredients.get(position);

        holder.txtTitle.setText(food.getTitle());
        String newAmount = food.getAmount() + " " + food.getUnit();
        holder.txtAmount.setText(newAmount);

        if (position % 2 > 0) {
            holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite1));
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngsViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtAmount;
        RelativeLayout parent;

        public IngsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_ingRvRoom_title);
            txtAmount = itemView.findViewById(R.id.txt_ingRvRoom_amount);
            parent = itemView.findViewById(R.id.rel_ingRvRoom_parent);
        }
    }
}
