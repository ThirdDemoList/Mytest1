package com.demo.mark.mytest1;


import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：mark on 2017/7/8 10:46
 * <p>
 * 邮箱：2285581945@qq.com
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Userbean> mList;

    public MyAdapter(Context context, List<Userbean> List) {
        this.context = context;
        this.mList = List;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View  view1  = View.inflate(context,R.layout.layout_list_item,null);
        TextView username = view1.findViewById(R.id.tv_list_user);
        ImageView usericon = view1.findViewById(R.id.iv_list_user);
        usericon.setImageURI(Uri.parse(mList.get(i).getImageurl()));
        username.setText(mList.get(i).getUsername());
        return view1;
    }
}
