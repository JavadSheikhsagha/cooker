package jvd.ir.cooker.MainActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import jvd.ir.cooker.Model.ImageModel;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter {

    List<ImageModel> images;

    sliderOnclick onclick;

    public MainSliderAdapter(List<ImageModel> images){
        this.images=images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindImageSlide(final int position, ImageSlideViewHolder imageSlideViewHolder) {
        Picasso.get().load(images.get(position).getImage()).into(imageSlideViewHolder.imageView);

        imageSlideViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(position,images.get(position).getInfo());
                Log.i("LOG12", "onClick: "+"hey");
            }
        });
    }

    public void setOnclick(sliderOnclick onclick) {
        this.onclick = onclick;
    }

    public interface sliderOnclick{
        void onclick(int position,String info);
    }
}
