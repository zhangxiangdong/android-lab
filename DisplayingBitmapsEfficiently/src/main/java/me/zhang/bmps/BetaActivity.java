package me.zhang.bmps;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.zhang.bmps.util.BitmapUtils;
import me.zhang.bmps.util.BitmapWorkerTask;
import me.zhang.bmps.view.AsyncDrawable;

/**
 * Created by Zhang on 4/26/2015 4:59 下午.
 */
public class BetaActivity extends BaseActivity {

    Bitmap mPlaceHolderBitmap;
    private GridView mGridView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta);
        mContext = getApplicationContext();
        final int mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        final int mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mPlaceHolderBitmap =
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mGridView = (GridView) findViewById(R.id.image_grid);
        List<String> images = getAllShownImagesPath(mContext);
        List<Uri> datas = new ArrayList<>();
        for (String s : images) {
            datas.add(Uri.fromFile(new File(s)));
        }
        final ImageGridAdapter adapter = new ImageGridAdapter(mContext, datas);
        mGridView.setAdapter(adapter);

        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
                // TODO
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int numColumns = (int) Math.floor(
                        mGridView.getWidth() / (mImageThumbSize + mImageThumbSpacing)
                );
                if (numColumns > 0) {
                    final int columnWidth =
                            (mGridView.getWidth() / numColumns) - mImageThumbSpacing;
                    //noinspection SuspiciousNameCombination
                    adapter.setItemHeight(columnWidth);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    //noinspection deprecation
                    mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    /**
     * Getting All Images Path
     *
     * @return ArrayList with images Path
     */
    public List<String> getAllShownImagesPath(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data;
        List<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA};

        cursor = context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(absolutePathOfImage);
        }
        cursor.close();
        return listOfAllImages;
    }

    private void loadBitmap(Uri uri, ImageView imageView) {
        if (cancelPotentialWork(uri, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(mContext, imageView);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(getResources(), mPlaceHolderBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(uri);
        }
    }

    private boolean cancelPotentialWork(Uri uri, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = BitmapUtils.getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            final Uri bitmapUri = bitmapWorkerTask.getUri();
            // If bitmapUri is not yet set or it differs from the new uri
            if (bitmapUri == null || bitmapUri != uri) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    class ImageGridAdapter extends BaseAdapter {

        private final Context mContext;
        private final List<Uri> mDatas;
        private int mItemHeight = 0;
        private GridView.LayoutParams mImageViewLayoutParams;

        public ImageGridAdapter(Context context, List<Uri> datas) {
            mContext = context;
            mDatas = datas;
            mImageViewLayoutParams = new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        public void setItemHeight(int height) {
            if (height == mItemHeight) {
                return;
            }
            mItemHeight = height;
            mImageViewLayoutParams =
                    new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(mImageViewLayoutParams);
            } else { // Otherwise re-use the converted view
                imageView = (ImageView) convertView;
            }

            // Check the height matches our calculated column width
            if (imageView.getLayoutParams().height != mItemHeight) {
                imageView.setLayoutParams(mImageViewLayoutParams);
            }

            loadBitmap(mDatas.get(position), imageView);
            return imageView;
        }
    }

}
