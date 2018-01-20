package net.simplifiedcoding.speechtotext.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.simplifiedcoding.speechtotext.R;


public class MenuAdapter extends BaseAdapter {
    Context context;
    String[] itemNames;
    int [] itemImages;
    private static LayoutInflater inflater = null;

    public MenuAdapter(Context context, String[] data, int [] itemImageLink) {
        this.context = context;
        this.itemNames = data;
        this.itemImages=itemImageLink;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemNames.length;
    }

    @Override
    public Object getItem(int position) {
        return itemNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.drawer_menu_item, null);
        }

        ImageView imageView = (ImageView) vi.findViewById(R.id.imgItemImage);
        TextView textView = (TextView) vi.findViewById(R.id.txtItemName);
        imageView.setImageResource(itemImages[position]);
        textView.setText(itemNames[position]);

        return vi;
    }

}