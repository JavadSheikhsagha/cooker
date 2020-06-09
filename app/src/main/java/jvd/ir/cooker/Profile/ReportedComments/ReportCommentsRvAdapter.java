package jvd.ir.cooker.Profile.ReportedComments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.R;

public class ReportCommentsRvAdapter extends RecyclerView.Adapter<ReportCommentsRvAdapter.ReportCommentsViewHolder> {

    List<CommentsModel> commentsModels;

    setOnCardClick onCardClick;

    Context context;

    public ReportCommentsRvAdapter(List<CommentsModel> commentsModels, Context context) {
        this.commentsModels = commentsModels;
        this.context = context;

    }

    @NonNull
    @Override
    public ReportCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reportedcomments_rv_adapter, parent, false);
        return new ReportCommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportCommentsViewHolder holder, int position) {
        final CommentsModel model = commentsModels.get(position);

        holder.txtLike.setText(model.getLikes());
        holder.txtUserName.setText(model.getUserName());
        holder.txtComment.setText(model.getComment());
        holder.txtReason.setText("دلیل : " + model.getReason());

        if (model.getStatus().equals("0")) {
            holder.txtStatus.setText("در انتظار بازدید مدیر");
        }
        if (model.getStatus().equals("1")) {
            holder.txtStatus.setText("کامنت حذف شد.");
            holder.txtStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_mycomments_confirm2));
        }
        if (model.getStatus().equals("2")) {
            holder.txtStatus.setText("کامنت مشکلی ندارد.");
            holder.txtStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_mycomments_confirm1));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCardClick.onClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsModels.size();
    }

    public void setOnCardClick(setOnCardClick onCardClick) {
        this.onCardClick = onCardClick;
    }

    public class ReportCommentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName, txtComment, txtLike, txtReason, txtStatus;
        CardView parent;

        public ReportCommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtComment = itemView.findViewById(R.id.txt_reportComments_comment);
            txtUserName = itemView.findViewById(R.id.txt_reportComments_userName);
            txtLike = itemView.findViewById(R.id.txt_reportComments_like);
            txtStatus = itemView.findViewById(R.id.txt_reportComments_status);
            txtReason = itemView.findViewById(R.id.txt_reportComments_reason);

            parent = itemView.findViewById(R.id.card_reportComments_parent);
        }
    }

    public interface setOnCardClick {
        void onClick(CommentsModel commentsModel);
    }
}
