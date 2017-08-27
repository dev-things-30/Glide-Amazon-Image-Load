package com.ankit.glideamazonimageload.glide;

import android.content.Context;
import android.widget.ImageView;

import com.ankit.glideamazonimageload.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static com.ankit.glideamazonimageload.Constants.BUCKET_NAME;
import static com.ankit.glideamazonimageload.Constants.PHOTO_TEMP_PATH;

/**
 * Created by ankit on 26-08-2017.
 */

public class DisplayImage {

    private static DisplayImage displayImage = new DisplayImage();

    private DisplayImage() {}

    public static DisplayImage getInstance () { return displayImage;}

    public void displayImageForUser (Context context, ImageView imageView, String uniqueId) {

        ImageModel imageModel = new ImageModel(uniqueId);
        imageModel.setLocalPath(PHOTO_TEMP_PATH);
        imageModel.setBucketName(BUCKET_NAME);

        Glide.with(context)
                .from(ImageModel.class)
                .fitCenter().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher)
                .load(imageModel)
                .into(imageView);
    }

}
