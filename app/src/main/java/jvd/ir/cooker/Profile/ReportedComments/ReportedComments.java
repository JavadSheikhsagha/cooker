package jvd.ir.cooker.Profile.ReportedComments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

public class ReportedComments extends AppCompatActivity {

    ImageView imgBack;

    RecyclerView recyclerView;

    ProfileViewModel viewModel;

    String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_comments);

        setupViews();

        USER_NAME = viewModel.getUserName();

        getReportedComments();
    }

    private void getReportedComments() {

        viewModel.getReportedComments(USER_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CommentsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CommentsModel> commentsModels) {
                        ReportCommentsRvAdapter adapter=new ReportCommentsRvAdapter(commentsModels,ReportedComments.this);
                        adapter.setOnCardClick(new ReportCommentsRvAdapter.setOnCardClick() {
                            @Override
                            public void onClick(CommentsModel commentsModel) {
                                Intent intent=new Intent(ReportedComments.this, DetailActivity.class);
                                intent.putExtra("food_id",commentsModel.getFoodId());
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

        viewModel = new ProfileViewModel(this);

        imgBack = findViewById(R.id.img_reportComments_back);

        recyclerView=findViewById(R.id.rv_reportComments_rv);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
