package jvd.ir.cooker.CategoryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.R;

public class AllCategoryActivity extends AppCompatActivity {

    ImageView imgBack;

    RecyclerView rvAllCats;

    CategoryViewModel viewModel =new CategoryViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

        setupViews();

        getAllCats();
    }

    private void getAllCats() {

        viewModel.getAllCats().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CategoryModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CategoryModel> categoryModels) {
                        RvAllCatAdapter adapter=new RvAllCatAdapter(categoryModels);
                        rvAllCats.setAdapter(adapter);
                        adapter.setFoodCatClick(new RvAllCatAdapter.onFoodCatClick() {
                            @Override
                            public void onclick(String catTitle) {
                                Intent intent=new Intent(AllCategoryActivity.this,ActivityCategory.class);
                                intent.putExtra("position",catTitle);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        imgBack = findViewById(R.id.img_allCat_back);
        rvAllCats = findViewById(R.id.rv_allCat_rv);

        rvAllCats.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}