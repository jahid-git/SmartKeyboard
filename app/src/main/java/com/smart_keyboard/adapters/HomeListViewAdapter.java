package com.smart_keyboard.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart_keyboard.R;
import com.smart_keyboard.activities.EmojisActivity;
import com.smart_keyboard.activities.OfflineTranslatorActivity;
import com.smart_keyboard.activities.PreferencesActivity;
import com.smart_keyboard.activities.ThemesActivity;
import com.smart_keyboard.activities.TutorialsActivity;
import com.smart_keyboard.models.HomeListItem;
import com.smart_keyboard.utilities.AppUtilities;

import java.util.List;

public class HomeListViewAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_CARD = 0;
    private static final int VIEW_TYPE_LIST_ITEM = 1;

    private Context context;
    private List<HomeListItem> homeListItemList;

    public HomeListViewAdapter(Context context, List<HomeListItem> homeListItemList) {
        this.context = context;
        this.homeListItemList = homeListItemList;
    }

    @Override
    public int getCount() {
        return homeListItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return homeListItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.homeListItemIcon);
        TextView title = convertView.findViewById(R.id.homeListItemTitle);
        TextView description = convertView.findViewById(R.id.homeListItemDescription);

        HomeListItem item = homeListItemList.get(position);

        icon.setImageResource(item.getIconResourcesId());
        if(position == 0){
            icon.setPadding(-15,-15, -15, -15);
        }

        TypedValue typedValue = new TypedValue();
        convertView.getContext().getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_IN);

        title.setText(item.getTitle());

        if(item.getDescription().isEmpty()){
            description.setVisibility(View.GONE);
        } else {
            description.setText(item.getDescription());
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 1:
                        view.getContext().startActivity(new Intent(view.getContext(), OfflineTranslatorActivity.class));
                        break;
                    case 2:
                        view.getContext().startActivity(new Intent(view.getContext(), ThemesActivity.class));
                        break;
                    case 3:
                        view.getContext().startActivity(new Intent(view.getContext(), EmojisActivity.class));
                        break;
                    case 4:
                        view.getContext().startActivity(new Intent(view.getContext(), PreferencesActivity.class));
                        break;
                    case 5:
                        view.getContext().startActivity(new Intent(view.getContext(), TutorialsActivity.class));
                        break;
                    case 6:
                        AppUtilities.showAboutDialog(view.getContext());
                        break;
                    default:
                        break;
                }
            }
        });


        return convertView;
    }
}
