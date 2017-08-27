package com.ankit.glideamazonimageload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ankit.glideamazonimageload.glide.DisplayImage;

import static com.ankit.glideamazonimageload.Constants.KEY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadImage(View view) {
        DisplayImage.getInstance().displayImageForUser(this, (ImageView) findViewById(R.id.imageView), KEY);
    }
}
