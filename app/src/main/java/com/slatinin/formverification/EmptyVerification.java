package com.slatinin.formverification;

import android.widget.EditText;

public class EmptyVerification implements Field<EditText> {
    private final EditText editText;
    private int order;

    public EmptyVerification(EditText editText) {
        this.editText = editText;
    }

    @Override
    public EditText getView() {
        return editText;
    }

    @Override
    public boolean validate() {
        if (editText.getText() != null && !editText.getText().toString().equals("")) {
            return true;
        }
        editText.setError("EMPTY");
        return false;
    }

    @Override
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
