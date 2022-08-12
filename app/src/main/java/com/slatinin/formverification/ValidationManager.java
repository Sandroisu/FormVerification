package com.slatinin.formverification;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class ValidationManager {

    private final List<Field<? extends View>> validationList;


    public ValidationManager() {
        this.validationList = new ArrayList<>();
    }

    public void addFieldToBeValidated(Field<? extends View> field, int order) {
        field.setOrder(order);
        int fieldToBeRemoved = -1;
        for (int i = 0; i < validationList.size(); i++) {
            if (validationList.get(i).getOrder() == order) {
                fieldToBeRemoved = i;
                break;
            }
        }

        if (fieldToBeRemoved >= 0) {
            validationList.remove(fieldToBeRemoved);
        }
        validationList.add(field);
        Collections.sort(validationList, (o1, o2) -> Integer.compare(o1.getOrder(), o2.getOrder()));
    }

    public boolean validate(NestedScrollView scrollView) {
        int firstViewPosition = -1;
        for (int i = 0; i < validationList.size(); i++) {
            Field<? extends View> field = validationList.get(i);
            if (!field.validate()) {
                if (firstViewPosition < 0) {
                    firstViewPosition = i;
                }
            }
        }
        if (firstViewPosition >= 0) {
            scrollToField(validationList.get(firstViewPosition).getView(), scrollView);
            return false;
        }
        return true;
    }

    public void scrollToField(View v, NestedScrollView scrollView) {
        scrollView.invalidate();
        Point realPosition = new Point();
        setRealPointPosition(scrollView, v.getParent(), v, realPosition);
        scrollView.smoothScrollTo(0, (int) realPosition.y, 500);
    }

    private void setRealPointPosition(ViewGroup parent, ViewParent viewParent, View child,
                                      Point realPosition) {
        if (viewParent instanceof ViewGroup) {
            ViewGroup parentGroup = (ViewGroup) viewParent;
            realPosition.x += child.getLeft();
            realPosition.y += child.getTop();
            if (parentGroup.equals(parent)) {
                return;
            }
            setRealPointPosition(parent, parentGroup.getParent(), parentGroup, realPosition);
        }
    }

}
