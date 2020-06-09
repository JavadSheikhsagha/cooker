package jvd.ir.cooker.Profile.MyComments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.Model.CommentsModel;
import jvd.ir.cooker.Profile.ProfileViewModel;
import jvd.ir.cooker.R;

public class MyCommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ImageView imgBack;

    ProfileViewModel viewModel;

    String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comments);

        setupViews();

        USER_NAME=viewModel.getUserName();

        getMyComments();


    }

    private void getMyComments() {

        viewModel.getMyComments(USER_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CommentsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CommentsModel> commentsModels) {
                        MyCommentsAdapter adapter=new MyCommentsAdapter(commentsModels,MyCommentsActivity.this);
                        adapter.setOnCommentClick(new MyCommentsAdapter.setOnCommentClick() {
                            @Override
                            public void onclick(CommentsModel model) {
                                Intent intent=new Intent(MyCommentsActivity.this, DetailActivity.class);
                                intent.putExtra("food_id",model.getFoodId());
                                startActivity(intent);

                            }
                        });

                        recyclerView.setAdapter(adapter);

                        YoYo.with(Techniques.FadeInRight)
                                .repeat(0)
                                .duration(700)
                                .playOn(recyclerView);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        viewModel=new ProfileViewModel(this);

        imgBack = findViewById(R.id.img_myComments_back);

        recyclerView = findViewById(R.id.rv_myComments_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
