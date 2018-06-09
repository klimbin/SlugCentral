package com.example.kevin.slugcentral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Course> courseList = null;
    private ArrayList<Course> arrayList;

    public ListViewAdapter(Context context, List<Course> courseList) {
        mContext = context;
        this.courseList = courseList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Course>();
        this.arrayList.addAll(courseList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Course getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_item, null);
            // Locate the TextViews in list_view_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(courseList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        courseList.clear();
        if (charText.length() != 0) {
            for (Course wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    courseList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
