package me.zhang.commonadapter.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Zhang on 6/9/2015 4:00 下午.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> mDatas;
    private Context mContext;
    private int mItemLayoutId;

    public CommonAdapter(Context context, List<T> datas, int itemLayoutId) {
        mContext = context;
        mDatas = datas;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, mItemLayoutId,
                position, convertView, parent);
        fillDatas(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void fillDatas(ViewHolder holder, T t);

}
