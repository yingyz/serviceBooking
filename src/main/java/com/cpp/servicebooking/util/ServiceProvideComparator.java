package com.cpp.servicebooking.util;

import com.cpp.servicebooking.models.ServiceProvide;

import java.util.Comparator;

public class ServiceProvideComparator implements Comparator<ServiceProvide> {
    @Override
    public int compare(ServiceProvide o1, ServiceProvide o2) {
        return (int)Math.ceil(o1.getUser().getUserInfo().getDistance() - o2.getUser().getUserInfo().getDistance());
    }
}
