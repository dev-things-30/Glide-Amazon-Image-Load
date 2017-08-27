package com.ankit.glideamazonimageload.glide;

import android.util.Log;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.ankit.glideamazonimageload.AmazonClient;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ankit on 26-08-2017.
 */

public class ImageFetcher implements DataFetcher<InputStream> {

    private final ImageModel imageModel;
    private InputStream mInputStream;
    boolean downloadComplete = false;
    int transferId = 0;

    public ImageFetcher(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        return fetchStream(imageModel);
    }

    private InputStream fetchStream(final ImageModel imageModel) {
        TransferUtility transferUtility = AmazonClient.getClient().getTransferUtility();
        TransferObserver bolomessages = transferUtility.download(imageModel.getBucketName(), imageModel.getId(), new File(imageModel.getLocalPath()));
        transferId = bolomessages.getId();

        bolomessages.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.wtf("AWSS3", "onStateChanged = " + state);
                if (state == TransferState.COMPLETED) {
                    File initialFile = new File(imageModel.getLocalPath());
                    try {
                        mInputStream = new FileInputStream(initialFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    downloadComplete = true;
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                if (bytesTotal == 0) return;
//                final int percentage = (int) ((bytesCurrent / (float)bytesTotal) * 100);
//                Log.wtf("AWSS3", "onProgressChanged = " + percentage);
            }

            @Override
            public void onError(int id, Exception ex) {
                // do something
                Log.wtf("AWSS3", "onError");
                ex.printStackTrace();
                downloadComplete = true;
            }
        });
        while (!downloadComplete){}
        return mInputStream;
    }

    @Override
    public void cleanup() {
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mInputStream = null;
            }
        }
    }

    @Override
    public String getId() {
        return imageModel.getId();
    }

    @Override
    public void cancel() {
        AmazonClient.getClient().getTransferUtility().cancel(transferId);
    }
}
