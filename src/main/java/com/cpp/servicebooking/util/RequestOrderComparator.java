package com.cpp.servicebooking.util;

import com.cpp.servicebooking.models.RequestOrder;

import java.util.Comparator;

public class RequestOrderComparator implements Comparator<RequestOrder> {

    @Override
    public int compare(RequestOrder o1, RequestOrder o2) {
        return (int)Math.ceil(o1.getUser().getUserInfo().getDistance() - o2.getUser().getUserInfo().getDistance());
    }
}
