package jvd.ir.cooker.MainActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.R;

public class MainCatTopAdapter extends RecyclerView.Adapter<MainCatTopAdapter.MainCatViewHolder> {

    List<CategoryModel> category;

    setOnCatClick onCatClick;

    setOnAllCatClick onAllCatClick;

    public MainCatTopAdapter(List<CategoryModel> category,setOnCatClick onCatClick){
        this.category=category;
        this.onCatClick=onCatClick;
    }

    @NonNull
    @Override
    public MainCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_cat_rv_adapter,parent,false);
        return new MainCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCatViewHolder holder, int position) {
        final CategoryModel model=category.get(position);

        if (position==0){
            model.setCategory("دسته بندی غذا ها");
            model.setId("0");
            model.setImage("https://img5.downloadha.com/AliGh/IMG/Retro-Posters-with-Food-2.jpg");
        }

        holder.txtTitle.setText(model.getCategory());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getId().equals("0")){
                    onAllCatClick.onclick();
                } if (!model.getId().equals("0")){
                    onCatClick.onclick(model);
                }
            }
        });

    }

    public void setOnAllCatClick(setOnAllCatClick onAllCatClick) {
        this.onAllCatClick = onAllCatClick;
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MainCatViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        RelativeLayout parent;

        public MainCatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_category_title);
            parent = itemView.findViewById(R.id.rel_category_parent);
        }
    }

    public interface setOnCatClick{
        void onclick(CategoryModel categoryModel);
    }

    public interface setOnAllCatClick{
        void onclick();
    }

}
