package com.smart_keyboard.services;

import android.inputmethodservice.InputMethodService;
import android.view.View;

import com.smart_keyboard.R;

public class SmartKeyboardService extends InputMethodService {

    private View keyboardLayout;

    @Override
    public View onCreateInputView() {
        keyboardLayout = (View) getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        return keyboardLayout;
    }
}
