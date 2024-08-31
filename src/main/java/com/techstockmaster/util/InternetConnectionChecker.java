package com.techstockmaster.util;

import java.net.InetAddress;

public class InternetConnectionChecker {
    public static boolean isConnectedToInternet() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000);
        } catch (Exception e) {
            return false;
        }
    }
}