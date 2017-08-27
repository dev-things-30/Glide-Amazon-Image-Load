package com.ankit.glideamazonimageload.glide;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

/**
 * Created by ankit on 26-08-2017.
 */

public class ImageLoader implements ModelLoader<ImageModel, InputStream> {

    private final ModelCache<ImageModel, ImageModel> mModelCache;

    public ImageLoader(ModelCache<ImageModel, ImageModel> mModelCache) {
        this.mModelCache = mModelCache;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(ImageModel model, int width, int height) {
        ImageModel imageModel = model;
        if (mModelCache != null) {
            imageModel = mModelCache.get(model, 0, 0);
            if (imageModel == null) {
                mModelCache.put(model, 0, 0, model);
                imageModel = model;
            }
        }
        return new ImageFetcher(imageModel);
    }

    public static class Factory implements ModelLoaderFactory<ImageModel, InputStream> {

        private final ModelCache<ImageModel, ImageModel> mModelCache = new ModelCache<>(500);

        @Override
        public ModelLoader<ImageModel, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new ImageLoader(mModelCache);
        }

        @Override
        public void teardown() {

        }
    }

}
