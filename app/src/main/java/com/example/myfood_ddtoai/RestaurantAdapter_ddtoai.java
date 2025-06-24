package com.example.myfood_ddtoai;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

public class RestaurantAdapter_ddtoai extends ArrayAdapter<Restaurant_ddtoai> {
    Context context;
    int resource;
    List<Restaurant_ddtoai> data;

    public RestaurantAdapter_ddtoai(Context context, int resource, List<Restaurant_ddtoai> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurant_ddtoai item = data.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvName = convertView.findViewById(R.id.tvRestaurantName_ddtoai);
        ImageView img = convertView.findViewById(R.id.imgRestaurant_ddtoai);

        tvName.setText(item.name);

        // ✅ Load ảnh từ drawable thay vì dùng Picasso
        int imageId = context.getResources().getIdentifier(item.image, "drawable", context.getPackageName());
        img.setImageResource(imageId);

        return convertView;
    }
}
