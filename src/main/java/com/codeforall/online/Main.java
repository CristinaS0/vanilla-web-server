package com.codeforall.online;

import java.lang.module.Configuration;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(8095);
        server.start();
    }
}
