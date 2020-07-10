package jvd.ir.cooker.Comments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.Comments.AddComment.AddCommentDialog;
import jvd.ir.cooker.LoginActivity.LoginActivity;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.R;

public class ActivityComments extends AppCompatActivity {

    TextView txtFoodTitle;

    ImageView imgBack;

    RecyclerView rvComments;

    FloatingActionButton fab;

    String FOOD_ID, FOOD_NAME, USER_ID, USER_NAME;

    int LIKED = 0;

    CommentsViewModel viewModel;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        FOOD_ID = getIntent().getStringExtra("food_id");
        FOOD_NAME = getIntent().getStringExtra("food_name");

        setupViews();

        USER_NAME = viewModel.getUserName();

        getComments();

    }


    private void getComments() {

        viewModel.getComments(FOOD_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CommentsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<CommentsModel> commentsModels) {
                        CommentRvAdapter adapter = new CommentRvAdapter(ActivityComments.this, USER_NAME, commentsModels, new CommentRvAdapter.setOnLikeClick() {
                            @Override
                            public void onclick(final CommentsModel commentsModel) {


                                    viewModel.likeComment(commentsModel.getId(), USER_NAME)
                                            .subscribeOn(Schedulers.newThread())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new SingleObserver<MessageModel>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {
                                                    compositeDisposable.add(d);
                                                }

                                                @Override
                                                public void onSuccess(MessageModel messageModel) {
                                                    Toast.makeText(ActivityComments.this, "ثبت شد", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });



                            }
                        }, new CommentRvAdapter.setOnDisLikeClick() {
                            @Override
                            public void onclick(CommentsModel commentsModel) {
                                // TODO: 5/1/2020 DISLIKE COMMENT
                            }
                        }, new CommentRvAdapter.setOnReportClick() {
                            @Override
                            public void onClick(final CommentsModel commentsModel) {
                                final CommentReportDialog dialog=new CommentReportDialog();
                                dialog.setReportClick(new CommentReportDialog.SetOnReportClick() {
                                    @Override
                                    public void onClick(String reason) {
                                        viewModel.reportComment(USER_NAME,commentsModel.getId(),reason)
                                                .subscribeOn(Schedulers.newThread())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new SingleObserver<MessageModel>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {
                                                        compositeDisposable.add(d);
                                                    }

                                                    @Override
                                                    public void onSuccess(MessageModel messageModel) {
                                                        Toast.makeText(ActivityComments.this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        Log.e("LOG9", "onError: "+e.toString() );
                                                    }
                                                });
                                    }
                                });

                                if (USER_NAME.equals("")){
                                    Toast.makeText(ActivityComments.this, "لطفا اول ثبت نام کنید", Toast.LENGTH_SHORT).show();
                                } if (!USER_NAME.equals("")){
                                    dialog.show(getSupportFragmentManager(),null);
                                }

                            }
                        });

                        adapter.setOnDialogShowClick(new CommentRvAdapter.setOnDialogShowClick() {
                            @Override
                            public void onclick() {
                                SignInDialog dialog=new SignInDialog();
                                dialog.setOnLoginClick(new SignInDialog.setOnLoginClick() {
                                    @Override
                                    public void onClick() {
                                        startActivity(new Intent(ActivityComments.this,LoginActivity.class));
                                        finish();
                                    }
                                });
                                dialog.show(getSupportFragmentManager(),null);
                            }
                        });

                        rvComments.setAdapter(adapter);
                        YoYo.with(Techniques.FadeInRight)
                                .repeat(0)
                                .duration(500)
                                .playOn(rvComments);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        viewModel = new CommentsViewModel(this);

        txtFoodTitle = findViewById(R.id.txt_comments_foodTitle);
        imgBack = findViewById(R.id.img_comments_back);
        rvComments = findViewById(R.id.rv_comments_commentsRv);
        fab = findViewById(R.id.fab_comments_fab);

        rvComments.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtFoodTitle.setText(FOOD_NAME);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AddCommentDialog commentDialog = new AddCommentDialog(USER_NAME);
                commentDialog.setOnSubmitClick(new AddCommentDialog.setOnSubmitClick() {
                    @Override
                    public void onClick(String comment, String UserName) {

                        viewModel.addComment(FOOD_ID, comment, USER_NAME)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<MessageModel>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        compositeDisposable.add(d);
                                    }

                                    @Override
                                    public void onSuccess(MessageModel messageModel) {
                                        Toast.makeText(ActivityComments.this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("LOG5", "onError: " + e.toString());
                                    }
                                });

                        commentDialog.dismiss();

                    }
                });
                if (!USER_NAME.equals("")){
                    commentDialog.show(getSupportFragmentManager(), null);
                } if (USER_NAME.equals("")){
                    SignInDialog dialog=new SignInDialog();
                    dialog.setOnLoginClick(new SignInDialog.setOnLoginClick() {
                        @Override
                        public void onClick() {
                            startActivity(new Intent(ActivityComments.this,LoginActivity.class));
                            finish();
                        }
                    });
                    dialog.show(getSupportFragmentManager(),null);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
