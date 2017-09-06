package com.ankit.glideamazonimageload.glide;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

/**
 * Created by ankit on 26-08-2017.
 */

public class ImageModel {

    String id;
    String localPath;
    String bucketName;

    public ImageModel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageModel imageFid = (ImageModel) o;

        return id.equals(imageFid.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getBucketName() {
        return bucketName;
    }
}
