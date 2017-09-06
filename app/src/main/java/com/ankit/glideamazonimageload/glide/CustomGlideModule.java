package com.ankit.glideamazonimageload.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;
import java.lang.annotation.Annotation;

/**
 * Created by ankit on 26-08-2017.
 */
@Deprecated
public class CustomGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(ImageModel.class, InputStream.class, new ImageLoader.Factory());
    }
}
