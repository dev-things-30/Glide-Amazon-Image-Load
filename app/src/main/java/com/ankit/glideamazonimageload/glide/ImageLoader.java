package com.ankit.glideamazonimageload.glide;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Created by ankit on 26-08-2017.
 */

public class ImageLoader implements ModelLoader<ImageModel, InputStream> {

    private final ModelCache<ImageModel, ImageModel> mModelCache;

    public ImageLoader(ModelCache<ImageModel, ImageModel> mModelCache) {
        this.mModelCache = mModelCache;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(ImageModel model, int width, int height, Options options) {
        ImageModel imageModel = model;
        if (mModelCache != null) {
            imageModel = mModelCache.get(model, 0, 0);
            if (imageModel == null) {
                mModelCache.put(model, 0, 0, model);
                imageModel = model;
            }
        }
        return new LoadData<>(new ObjectKey(model), new ImageFetcher(imageModel));
    }

    @Override
    public boolean handles(ImageModel imageModel) {
        return true;
    }

    public static class Factory implements ModelLoaderFactory<ImageModel, InputStream> {

        private final ModelCache<ImageModel, ImageModel> mModelCache = new ModelCache<>(500);

        @Override
        public ModelLoader<ImageModel, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new ImageLoader(mModelCache);
        }

        @Override
        public void teardown() {

        }
    }

}
