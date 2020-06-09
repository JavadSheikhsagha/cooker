package jvd.ir.cooker.CategoryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.R;

public class CatFragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    FrameLayout frameLayout;

    CategoryViewModel viewModel=new CategoryViewModel();

    String title;

    public CatFragment(String title){
        this.title=title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view=getLayoutInflater().inflate(R.layout.fragment_viewpager,container,false);
        }

        setupViews();

        getCatListItem();
        return view;
    }

    private void getCatListItem() {

        viewModel.getCatListItems(title)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        frameLayout.setVisibility(View.GONE);
                        CategoryListItemAdapter adapter=new CategoryListItemAdapter(foodModels, new CategoryListItemAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {

                                Intent intent=new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra("food_id", foodModel.getId());
                                intent.putExtra("food_img", foodModel.getImage());
                                startActivity(intent);

                            }
                        });
                        YoYo.with(Techniques.FadeIn)
                                .duration(700)
                                .repeat(0)
                                .playOn(recyclerView);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        frameLayout = view.findViewById(R.id.fl_catFragment_frameLayout);
        recyclerView = view.findViewById(R.id.rv_catFragment_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}
