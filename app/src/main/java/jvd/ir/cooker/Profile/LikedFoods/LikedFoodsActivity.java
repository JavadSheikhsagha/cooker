package jvd.ir.cooker.Profile.LikedFoods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.CategoryActivity.CategoryListItemAdapter;
import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Profile.ProfileViewModel;
import jvd.ir.cooker.R;

public class LikedFoodsActivity extends AppCompatActivity {

    ImageView imgBack;

    RecyclerView recyclerView;

    ProfileViewModel viewModel;

    String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_foods);

        setupViews();

        USER_NAME=viewModel.getUserName();

        getLikedFoods();
    }

    private void getLikedFoods() {

        viewModel.getLikedFoods(USER_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        CategoryListItemAdapter adapter=new CategoryListItemAdapter(foodModels, new CategoryListItemAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent=new Intent(LikedFoodsActivity.this, DetailActivity.class);
                                intent.putExtra("food_img",foodModel.getImage());
                                intent.putExtra("food_id",foodModel.getId());
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        viewModel = new ProfileViewModel(this);

        imgBack = findViewById(R.id.img_LikedFood_back);

        recyclerView = findViewById(R.id.rv_likedFood_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
