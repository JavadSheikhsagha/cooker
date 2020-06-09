package jvd.ir.cooker.Profile.MyComments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.R;

public class MyCommentsAdapter extends RecyclerView.Adapter<MyCommentsAdapter.MyCommentsViewHolder> {

    List<CommentsModel> commentsModel;
    setOnCommentClick onCommentClick;
    Context context;

    public MyCommentsAdapter(List<CommentsModel> commentsModel, Context context) {
        this.commentsModel = commentsModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_comments_rv_adapter, parent, false);
        return new MyCommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentsViewHolder holder, int position) {
        final CommentsModel model = commentsModel.get(position);

        holder.txtLike.setText(model.getLikes());
        holder.txtComment.setText(model.getComment());
        holder.txtUserName.setText(model.getUserName());

        if (commentsModel.get(position).getConfirm().equals("0")) {
            holder.txtConfirm.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_mycomments_confirm0));
            holder.txtConfirm.setText("کامنت شما هنوز تایید نشده است.");
        }
        if (commentsModel.get(position).getConfirm().equals("2")) {
            holder.txtConfirm.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_mycomments_confirm2));
            holder.txtConfirm.setText("پیغام شما برای نمایش تایید نشد.");
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommentClick.onclick(model);
            }
        });
    }

    public void setOnCommentClick(setOnCommentClick onCommentClick) {
        this.onCommentClick = onCommentClick;
    }

    @Override
    public int getItemCount() {
        return commentsModel.size();
    }

    public class MyCommentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName, txtReport, txtComment, txtLike, txtConfirm;
        CardView parent;

        public MyCommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserName = itemView.findViewById(R.id.txt_MyCommentsRv_userName);
            txtReport = itemView.findViewById(R.id.txt_MyCommentsRv_report);
            txtComment = itemView.findViewById(R.id.txt_MyCommentsRv_comment);
            txtLike = itemView.findViewById(R.id.txt_MyCommentsRv_like);
            txtConfirm = itemView.findViewById(R.id.txt_MyCommentsRv_confirm);

            parent = itemView.findViewById(R.id.card_MyComments_parent);
        }
    }

    public interface setOnCommentClick {
        void onclick(CommentsModel model);
    }
}
