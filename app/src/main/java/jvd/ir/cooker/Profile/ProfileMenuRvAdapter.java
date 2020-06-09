package jvd.ir.cooker.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.MainActivity.MainRvAdapter;
import jvd.ir.cooker.Model.ProfileMenuModel;
import jvd.ir.cooker.R;

public class ProfileMenuRvAdapter extends RecyclerView.Adapter<ProfileMenuRvAdapter.ProfileMenuViewHolder> {

    List<ProfileMenuModel> profileMenuModels;

    setOnMenuClick onMenuClick;

    public ProfileMenuRvAdapter(List<ProfileMenuModel> menuModels){
        this.profileMenuModels=menuModels;
    }

    @NonNull
    @Override
    public ProfileMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_menu_rv_adapter,parent,false);
        return new ProfileMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileMenuViewHolder holder, final int position) {
        ProfileMenuModel model=profileMenuModels.get(position);

        holder.imgIcon.setImageResource(model.getIcon());
        holder.txtTitle.setText(model.getTitle());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick.onClick(position);
            }
        });

    }

    public void setOnMenuClick(setOnMenuClick onMenuClick) {
        this.onMenuClick = onMenuClick;
    }

    @Override
    public int getItemCount() {
        return profileMenuModels.size();
    }

    public class ProfileMenuViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgIcon;
        RelativeLayout parent;
        public ProfileMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_profileRv_title);
            imgIcon = itemView.findViewById(R.id.img_profileRv_icon);
            parent = itemView.findViewById(R.id.rel_profileRv_parent);
        }
    }

    public interface setOnMenuClick{
        void onClick(int position);
    }
}
