package com.ankit.glideamazonimageload.glide;

import android.content.Context;
import android.widget.ImageView;

import com.ankit.glideamazonimageload.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

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

        ImageModel imageModel = new ImageModel();
        imageModel.setId(uniqueId);
        imageModel.setLocalPath(PHOTO_TEMP_PATH);
        imageModel.setBucketName(BUCKET_NAME);

        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                .load(imageModel)
                .into(imageView);
    }

}
