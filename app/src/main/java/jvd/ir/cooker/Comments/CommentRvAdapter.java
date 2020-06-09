package jvd.ir.cooker.Comments;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.R;

public class CommentRvAdapter extends RecyclerView.Adapter<CommentRvAdapter.CommentsViewHolder> {

    List<CommentsModel> commentsModels;

    setOnLikeClick onLikeClick;

    setOnReportClick onReportClick;

    setOnDisLikeClick onDisLikeClick;

    setOnDialogShowClick onDialogShowClick;

    CommentsViewModel commentsViewModel;

    Context context;

    String userName;
    
    String USER_NAME;

    public CommentRvAdapter(Context context, String userName, List<CommentsModel> commentsModels, setOnLikeClick onLikeClick, setOnDisLikeClick onDisLikeClick, setOnReportClick onReportClick) {
        this.context = context;
        commentsViewModel=new CommentsViewModel(context);
        this.userName = userName;
        this.onDisLikeClick=onDisLikeClick;
        this.commentsModels = commentsModels;
        this.onLikeClick = onLikeClick;
        this.onReportClick = onReportClick;
        USER_NAME=commentsViewModel.getUserName();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_rv_adapter, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsViewHolder holder, final int position) {
        final CommentsModel model = commentsModels.get(position);

        holder.txtUserName.setText(model.getUserName());
        holder.txtComment.setText(model.getComment());
        holder.txtLike.setText(model.getLikes());

        commentsViewModel.getIfLiked(model.getId(), userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        commentsModels.get(position).setLiked(s);
                        if (commentsModels.get(position).getLiked().equals("1")) {
                            holder.imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_like_black_24dp));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG8", "onError: "+e.toString());
                    }
                });

        holder.likeParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!USER_NAME.equals("")){
                    if (model.getLiked().equals("0")) {
                        onLikeClick.onclick(model);
                        int likes=Integer.parseInt(model.getLikes());
                        likes++;
                        holder.txtLike.setText(likes+"");
                        holder.imgLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_like_black_24dp));
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                model.setLiked("1");
                            }},500);
                    }
                    if (model.getLiked().equals("1")) {
                        Toast.makeText(context, "نظر شما قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
                        // TODO: 5/1/2020 ON DISLIKE
                    }
                } else {
                    onDialogShowClick.onclick();
                }
            }
        });



        holder.txtReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!USER_NAME.equals("")){
                    onReportClick.onClick(commentsModels.get(position));
                } else {
                    onDialogShowClick.onclick();
                }

            }
        });

    }

    public void setOnDialogShowClick(setOnDialogShowClick onDialogShowClick) {
        this.onDialogShowClick = onDialogShowClick;
    }

    @Override
    public int getItemCount() {
        return commentsModels.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName, txtComment, txtLike, txtReport;
        RelativeLayout likeParent;
        ImageView imgLike;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtComment = itemView.findViewById(R.id.txt_commentsRv_comment);
            txtUserName = itemView.findViewById(R.id.txt_commentsRv_userName);
            txtReport = itemView.findViewById(R.id.txt_commentsRv_report);
            txtLike = itemView.findViewById(R.id.txt_commentsRv_like);
            likeParent = itemView.findViewById(R.id.rel_commentsRv_like);
            imgLike = itemView.findViewById(R.id.img_commentsRv_like);
        }
    }

    public interface setOnDialogShowClick{
        void onclick();
    }

    public interface setOnLikeClick {
        void onclick(CommentsModel commentsModel);
    }

    public interface setOnDisLikeClick {
        void onclick(CommentsModel commentsModel);
    }

    public interface setOnReportClick {
        void onClick(CommentsModel commentsModel);
    }

}
