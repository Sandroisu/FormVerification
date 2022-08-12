package com.slatinin.formverification;

public interface Field<T> {

    T getView();

    boolean validate();

    void setOrder(int order);

    int getOrder();

}
