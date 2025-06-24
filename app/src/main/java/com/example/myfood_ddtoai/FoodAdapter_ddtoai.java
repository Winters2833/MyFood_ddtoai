package com.example.myfood_ddtoai;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

public class FoodAdapter_ddtoai extends ArrayAdapter<Food_ddtoai> {
    Context context;
    int resource;
    List<Food_ddtoai> data;

    public FoodAdapter_ddtoai(Context context, int resource, List<Food_ddtoai> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Food_ddtoai item = data.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvName = convertView.findViewById(R.id.tvFoodName_ddtoai);
        TextView tvPrice = convertView.findViewById(R.id.tvFoodPrice_ddtoai);
        ImageView img = convertView.findViewById(R.id.imgFood_ddtoai);

        tvName.setText(item.name);
        tvPrice.setText("Giá: " + item.price + "đ");

        int imageId = context.getResources().getIdentifier(item.image, "drawable", context.getPackageName());
        if (imageId != 0) {
            img.setImageResource(imageId);
        } else {
            img.setImageResource(R.drawable.default_food); // fallback nếu không tìm thấy
        }
        return convertView;
    }
}
