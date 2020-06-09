package jvd.ir.cooker.CategoryActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.cooker.Model.CategoryModel;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    List<String > titles;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        titles=new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CatFragment(titles.get(position));
    }

    public void addFragment(String title){
        titles.add(title);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}