package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

public class InternetConnectionCheckerTeste {

    @Test
    void connectionTest() {
        InternetConnectionChecker.isConnectedToInternet();
    }
}