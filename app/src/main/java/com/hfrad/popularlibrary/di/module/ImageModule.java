package com.hfrad.popularlibrary.di.module;

import android.widget.ImageView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.hfrad.popularlibrary.mvp.view.image.GlideImageLoader;
import com.hfrad.popularlibrary.mvp.view.image.IImageLoader;

@Module
public class ImageModule {
    @Singleton
    @Provides
    IImageLoader<ImageView> getImageLoader() {
        return new GlideImageLoader();
    }
}
