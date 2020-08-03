package jvd.ir.cooker.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.CategoryActivity.ActivityCategory;
import jvd.ir.cooker.CategoryActivity.AllCategoryActivity;
import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.DrawerMenuItems.AboutActivity;
import jvd.ir.cooker.LoginActivity.LoginActivity;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;
import jvd.ir.cooker.Profile.BookmarkFoods.BookmarkFoodsActivity;
import jvd.ir.cooker.Profile.LikedFoods.LikedFoodsActivity;
import jvd.ir.cooker.Profile.ProfileActivity;
import jvd.ir.cooker.R;
import ss.com.bannerslider.event.OnSlideClickListener;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    RelativeLayout relIranianFood, relBestFood, relDrinks, relDeserts, relCatTopRv, relVegan, relInstagramLink, relSplashScreen;

    ss.com.bannerslider.Slider slider;

    RecyclerView rvBestFood, rvDrinks, rvCatsRvTop, rvDeserts, rvIranianFood, rvVegan;

    TextView txtLogin, txtMainSplashSaved;

    MainViewModel viewModel;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    PicassoImageLoadingService imageLoadingService;

    NestedScrollView scrollView;

    DrawerLayout drawer;

    ImageView imgMenu, imgLogin, imgInstagramLink;

    CardView actionBar;

    View headerView;

    int action = 0;

    String USER_NAME = "";

    SpinKitView spin;

    Button btnMainSplashSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);

        setupViews();

        USER_NAME = viewModel.getUserName();

        if (!USER_NAME.equals("")) {
            txtLogin.setText(USER_NAME);
            imgLogin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_person_black_24dp));
        }

        getIranianFood();

        getBestFood();

        getSliderImage();

        getVeganFood();

        getCatTop();

        getDrinks();

        getDeserts();

        getInstagramLink();

    }

    private void getInstagramLink() {

        viewModel.getInstagramLink().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ImageModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ImageModel> imageModels) {
                        if (!imageModels.get(0).getImage().equals("")){
                            Picasso.get()
                                    .load(imageModels.get(0).getImage())
                                    .placeholder(ContextCompat.getDrawable(MainActivity.this,R.color.colorBlack))
                                    .into(imgInstagramLink);
                        }

                        relInstagramLink.setVisibility(View.VISIBLE);
                        relInstagramLink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri uri = Uri.parse("http://instagram.com/_u/javad_sheikhsagha");
                                Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                                insta.setPackage("com.instagram.android");

                                if (isIntentAvailable(MainActivity.this, insta)){
                                    startActivity(insta);
                                } else{
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/javad_sheikhsagha/")));
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void getDeserts() {

        viewModel.getDeserts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        MainRvAdapter adapter = new MainRvAdapter(MainActivity.this, foodModels, new MainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("food_img", foodModel.getImage());
                                intent.putExtra("food_id", foodModel.getId());
                                startActivity(intent);
                            }
                        });
                        rvDeserts.setAdapter(adapter);
                        relDeserts.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getDrinks() {

        viewModel.getDrinks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        MainRvAdapter adapter = new MainRvAdapter(MainActivity.this, foodModels, new MainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("food_img", foodModel.getImage());
                                intent.putExtra("food_id", foodModel.getId());
                                startActivity(intent);
                            }
                        });
                        rvDrinks.setAdapter(adapter);
                        relDrinks.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getCatTop() {

        viewModel.getCatTop()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CategoryModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<CategoryModel> categoryModels) {
                        MainCatTopAdapter adapter = new MainCatTopAdapter(categoryModels, new MainCatTopAdapter.setOnCatClick() {
                            @Override
                            public void onclick(CategoryModel categoryModel) {
                                Intent intent = new Intent(MainActivity.this, ActivityCategory.class);
                                intent.putExtra("position", categoryModel.getmPosition());
                                startActivity(intent);
                            }
                        });

                        adapter.setOnAllCatClick(new MainCatTopAdapter.setOnAllCatClick() {
                            @Override
                            public void onclick() {
                                startActivity(new Intent(MainActivity.this, AllCategoryActivity.class));
                            }
                        });

                        rvCatsRvTop.setAdapter(adapter);
                        relCatTopRv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getVeganFood() {

        viewModel.getVegan()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {

                        VeganMainRvAdapter adapter = new VeganMainRvAdapter(foodModels, new VeganMainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("food_id", foodModel.getId());
                                intent.putExtra("food_img", foodModel.getImage());
                                startActivity(intent);
                            }
                        });

                        rvVegan.setAdapter(adapter);
                        relVegan.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getSliderImage() {

        viewModel.getImages("0")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ImageModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ImageModel> imageModels) {
                        MainSliderAdapter mainSliderAdapter = new MainSliderAdapter(imageModels);
                        slider.setOnSlideClickListener(new OnSlideClickListener() {
                            @Override
                            public void onSlideClick(int position) {
                                switch (position) {
                                    case 0:
                                        Toast.makeText(MainActivity.this, "first", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Toast.makeText(MainActivity.this, "second", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Toast.makeText(MainActivity.this, "third", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });

                        slider.setAdapter(mainSliderAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getBestFood() {

        viewModel.getBestFood().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        relBestFood.setVisibility(View.VISIBLE);
                        MainRvAdapter mainRvAdapter = new MainRvAdapter(MainActivity.this, foodModels, new MainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("food_id", foodModel.getId());
                                intent.putExtra("food_img", foodModel.getImage());
                                startActivity(intent);
                            }
                        });

                        rvBestFood.setAdapter(mainRvAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getIranianFood() {

        viewModel.getIranianFood()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {

                        relIranianFood.setVisibility(View.VISIBLE);
                        MainRvAdapter mainRvAdapter = new MainRvAdapter(MainActivity.this, foodModels, new MainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("food_id", foodModel.getId());
                                intent.putExtra("food_img", foodModel.getImage());
                                startActivity(intent);
                            }
                        });
                        rvIranianFood.setAdapter(mainRvAdapter);
                        Handler handler =new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                YoYo.with(Techniques.ZoomOut)
                                        .duration(1000)
                                        .playOn(relSplashScreen);
                                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                            }
                        },1500);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG4", "onError: " + e.toString());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                YoYo.with(Techniques.FadeOut)
                                        .duration(700)
                                        .playOn(spin);
                                YoYo.with(Techniques.FadeIn)
                                        .duration(700)
                                        .playOn(txtMainSplashSaved);
                                txtMainSplashSaved.setVisibility(View.VISIBLE);
                                btnMainSplashSaved.setVisibility(View.VISIBLE);
                                YoYo.with(Techniques.FadeIn)
                                        .duration(700)
                                        .playOn(btnMainSplashSaved);
                            }
                        },1500);

                    }
                });

    }

    private void setupViews() {

        imageLoadingService = new PicassoImageLoadingService(this);

        viewModel = new MainViewModel(this);

        imgMenu = findViewById(R.id.img_mainActionBar_Menu);
        imgInstagramLink = findViewById(R.id.img_main_instagramLink);

        actionBar = findViewById(R.id.card_main_actionBar);

        scrollView = findViewById(R.id.scroll_main_nestedScrollView);

        relBestFood = findViewById(R.id.rel_main_topFood);
        relDrinks = findViewById(R.id.rel_main_topUsers);
        relCatTopRv = findViewById(R.id.rel_main_cats);
        relDeserts = findViewById(R.id.rel_main_catsRV);
        relIranianFood = findViewById(R.id.rel_main_iranianFood);
        relVegan = findViewById(R.id.rel_main_veganFood);
        relInstagramLink = findViewById(R.id.rel_main_instagramLink);
        relSplashScreen = findViewById(R.id.rel_main_splash);

        rvBestFood = findViewById(R.id.rv_main_topFood);
        rvDrinks = findViewById(R.id.rv_main_topUsers);
        rvDeserts = findViewById(R.id.rv_main_catsRV);
        rvIranianFood = findViewById(R.id.rv_main_iranianFood);
        rvVegan = findViewById(R.id.rv_main_veganFood);
        rvCatsRvTop = findViewById(R.id.rv_main_category);

        spin = findViewById(R.id.spin_kit);
        btnMainSplashSaved = findViewById(R.id.btn_mainSplash_saved);
        txtMainSplashSaved = findViewById(R.id.txt_mainSplash_saved);

        slider = findViewById(R.id.slider_main);
        // TODO: 4/27/2020 SLIDER ON CLICK

        drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_groups, R.id.nav_liked, R.id.nav_saved,
                R.id.nav_buy_list, R.id.nav_question, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        navigationView.getMenu().findItem(R.id.nav_groups).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, AllCategoryActivity.class));
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_liked).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, LikedFoodsActivity.class));
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_saved).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, BookmarkFoodsActivity.class));
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_buy_list).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "در نسخه بعد اضافه میشود.", Toast.LENGTH_SHORT).show();
                // TODO: 6/28/2020 BUY LIST
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_score).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String LINK = "https://cafebazaar.ir/app/jvd.ir.cooker";

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK));
                startActivity(browserIntent);
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_send).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String LINK = "https://cafebazaar.ir/app/jvd.ir.cooker";

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK));
                startActivity(browserIntent);
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_question).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Uri uri = Uri.parse("https://cards.bio/@javad_sheikhsagha/70");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return false;
            }
        });

        headerView = navigationView.getHeaderView(0);

        txtLogin = headerView.findViewById(R.id.drawer_txt_Header);
        imgLogin = headerView.findViewById(R.id.img_header_login);

        scrollView.setScrollY(0);
        scrollThingy();

        relBestFood.setVisibility(View.GONE);
        relDrinks.setVisibility(View.GONE);
        relDeserts.setVisibility(View.GONE);
        relIranianFood.setVisibility(View.GONE);
        relVegan.setVisibility(View.GONE);
        relCatTopRv.setVisibility(View.GONE);

        rvIranianFood.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvBestFood.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvVegan.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvCatsRvTop.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvDrinks.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvDeserts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));


        rvVegan.setFocusable(false);
        rvBestFood.setFocusable(false);
        rvIranianFood.setFocusable(false);
        slider.setFocusable(false);
        rvDrinks.setFocusable(false);
        rvCatsRvTop.setFocusable(false);
        rvDeserts.setFocusable(false);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (USER_NAME.equals("")) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("user_name", USER_NAME);
                    startActivity(intent);
                    finish();

                }
            }
        });

        btnMainSplashSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BookmarkFoodsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void scrollThingy() {

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, final int scrollY, int oldScrollX, final int oldScrollY) {

                //Log.i("LOG2", "onScrollChange: " + scrollY + " ");

                Thread thread;

                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if (scrollY > 300 && scrollY > oldScrollY && action == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    action = 1;
                                    actionBar.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FadeIn)
                                            .repeat(0)
                                            .duration(300)
                                            .playOn(actionBar);

                                }
                            });

                        } else if (scrollY < 300 && scrollY < oldScrollY && action == 1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    action = 0;

                                    actionBar.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.FadeOut)
                                            .repeat(0)
                                            .duration(300)
                                            .playOn(actionBar);
                                }
                            });
                        }

                    }
                });

                thread.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        drawer.closeDrawer(Gravity.RIGHT);
        super.onResume();
    }
}