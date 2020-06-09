package jvd.ir.cooker.RoomDatabase.FoodHowTo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.Model.FoodHowTo;
import jvd.ir.cooker.R;

public class HowToRvAdapter extends RecyclerView.Adapter<HowToRvAdapter.HowToViewHolder> {

    List<HowToModel2> howTo;
    Context context;

    public HowToRvAdapter(Context context,List<HowToModel2> foodHowTos){
        this.howTo = foodHowTos;
        this.context=context;
    }

    @NonNull
    @Override
    public HowToViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_howto_rv_adapter,parent,false);
        return new HowToViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HowToViewHolder holder, int position) {

        holder.txtNumber.setText(position+1+"");
        holder.txtDesc.setText(howTo.get(position).getTodo());


        if (position%2==0){
            holder.parentDesc.setBackgroundColor(ContextCompat.getColor(context,R.color.colorGrey100));
        } else {
            holder.parentDesc.setBackgroundColor(ContextCompat.getColor(context,R.color.colorGrey300));
        }
    }

    @Override
    public int getItemCount() {
        return howTo.size();
    }

    public class HowToViewHolder extends RecyclerView.ViewHolder {

        TextView txtDesc, txtNumber,txtWarning;
        RelativeLayout parentDesc, parentNumber;
        ImageView imgWarn;

        public HowToViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumber = itemView.findViewById(R.id.txt_howToRoom_number);
            txtDesc = itemView.findViewById(R.id.txt_howToRoom_desc);
            parentNumber = itemView.findViewById(R.id.rel_howToRoom_number);
            parentDesc = itemView.findViewById(R.id.rel_howToRoom_title);
            txtWarning = itemView.findViewById(R.id.txt_howToRoom_warning);
            imgWarn = itemView.findViewById(R.id.img_howToRoom_warn);

        }
    }
}
