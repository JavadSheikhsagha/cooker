package jvd.ir.cooker.DetailActivity;

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

public class InstructionRvAdapter extends RecyclerView.Adapter<InstructionRvAdapter.InstructionViewHolder> {

    List<FoodHowTo> howTo;
    Context context;

    public InstructionRvAdapter(Context context,List<FoodHowTo> foodHowTos) {
        this.howTo = foodHowTos;
        this.context=context;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.howto_rv_adapter, parent, false);
        return new InstructionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {

        holder.txtNumber.setText(position+1+"");
        holder.txtDesc.setText(howTo.get(position).getTodo());
        if (!howTo.get(position).getWarn().equals("")){
            holder.txtWarning.setText("نکته : "+ howTo.get(position).getWarn());
            holder.txtWarning.setVisibility(View.VISIBLE);
            holder.imgWarn.setVisibility(View.VISIBLE);
        }


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

    public class InstructionViewHolder extends RecyclerView.ViewHolder {

        TextView txtDesc, txtNumber,txtWarning;
        RelativeLayout parentDesc, parentNumber;
        ImageView imgWarn;

        public InstructionViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumber = itemView.findViewById(R.id.txt_howTo_number);
            txtDesc = itemView.findViewById(R.id.txt_howTo_desc);
            parentNumber = itemView.findViewById(R.id.rel_howTo_number);
            parentDesc = itemView.findViewById(R.id.rel_howTo_title);
            txtWarning = itemView.findViewById(R.id.txt_howTo_warning);
            imgWarn = itemView.findViewById(R.id.img_howTo_warn);

        }
    }
}
