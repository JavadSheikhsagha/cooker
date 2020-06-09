package jvd.ir.cooker.Profile.LikedComments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lid.lib.LabelView;

import java.util.List;

import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.R;

public class LikedCommentsAdapter extends RecyclerView.Adapter<LikedCommentsAdapter.LikedCommentsViewHolder> {

    List<CommentsModel> commentsModels;

    setOnCommentClick onCommentClick;

    public LikedCommentsAdapter(List<CommentsModel> commentsModels){
        this.commentsModels=commentsModels;
    }

    @NonNull
    @Override
    public LikedCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.likedcomments_rv_adapter,parent,false);
        return new LikedCommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikedCommentsViewHolder holder, int position) {
        final CommentsModel model=commentsModels.get(position);

        holder.txtLike.setText(model.getLikes());
        holder.txtComment.setText(model.getComment());
        holder.txtUserName.setText(model.getUserName());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommentClick.onClick(model);
            }
        });

    }

    public void setOnCommentClick(setOnCommentClick onCommentClick) {
        this.onCommentClick = onCommentClick;
    }

    @Override
    public int getItemCount() {
        return commentsModels.size();
    }

    public class LikedCommentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName,txtComment,txtLike;

        CardView parent;

        public LikedCommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtComment = itemView.findViewById(R.id.txt_LikedCommentsRv_comment);
            txtUserName = itemView.findViewById(R.id.txt_LikedCommentsRv_userName);
            txtLike = itemView.findViewById(R.id.txt_LikedCommentsRv_like);
            parent = itemView.findViewById(R.id.card_LikedComments_parent);
        }
    }

    public interface setOnCommentClick{
        void onClick(CommentsModel model);
    }
}
