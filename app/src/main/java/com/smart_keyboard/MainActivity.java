package com.smart_keyboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.smart_keyboard.adapters.HomeListViewAdapter;
import com.smart_keyboard.models.HomeListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View keyboardPermissionsSection;
    private View keyboardLanguagesSection;
    private MaterialButton enableSmartKeyboardButton;
    private ListView homeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_main);

        keyboardPermissionsSection = findViewById(R.id.keyboardPermissionsSection);
        keyboardLanguagesSection = findViewById(R.id.keyboardLanguagesSection);

        enableSmartKeyboardButton = findViewById(R.id.enableSmartKeyboardButton);
        enableSmartKeyboardButton.setOnClickListener(this);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        ((ImageView)findViewById(R.id.indicatorTriangle)).setColorFilter(typedValue.data, PorterDuff.Mode.SRC_IN);

        homeListView = findViewById(R.id.homeListView);

        List<HomeListItem> homeListItemList = new ArrayList<>();

        homeListItemList.add(new HomeListItem(R.drawable.ic_chatgpt, "ChatGPT API", "ChatGPT api key setup"));
        homeListItemList.add(new HomeListItem(R.drawable.ic_translator, "Offline translator", "Download first time translator"));
        homeListItemList.add(new HomeListItem(R.drawable.ic_theme, "Themes", "Change and create themes"));
        homeListItemList.add(new HomeListItem(R.drawable.ic_emojis, "Emojis, stickers, gifs and fonts", "Emoji, sticker, gif and fonts"));
        homeListItemList.add(new HomeListItem(R.drawable.ic_preferences,"Preferences", ""));
        homeListItemList.add(new HomeListItem(R.drawable.ic_tutorials, "Tutorials", ""));
        homeListItemList.add(new HomeListItem(R.drawable.ic_about_dev, "About developer", ""));

        homeListView.setAdapter(new HomeListViewAdapter(this, homeListItemList));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isKeyboardPermissionEnabled()){
            keyboardPermissionsSection.setVisibility(View.VISIBLE);
            keyboardLanguagesSection.setVisibility(View.GONE);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);
            dialog.setTitle(getResources().getString(R.string.enable_smart_keyboard_dialog_title));
            dialog.setMessage(getResources().getString(R.string.enable_smart_keyboard_dialog_msg));
            dialog.setPositiveButton(getResources().getString(R.string.enable_btn), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                }
            });
            dialog.setNegativeButton(getResources().getString(R.string.close_btn), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog.show();

        } else {
            keyboardPermissionsSection.setVisibility(View.GONE);
            keyboardLanguagesSection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == enableSmartKeyboardButton.getId()){
            startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
        }
    }

    public boolean isKeyboardPermissionEnabled() {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> inputMethodList = imeManager.getEnabledInputMethodList();
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
            if (inputMethodInfo.getPackageName().equals(getApplication().getPackageName())) {
                return true;
            }
        }
        return false;
    }

}