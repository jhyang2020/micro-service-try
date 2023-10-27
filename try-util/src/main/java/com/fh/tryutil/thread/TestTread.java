package com.fh.tryutil.thread;

import java.util.concurrent.Callable;

/**
 * @Author jhYang
 * @Date 2022/9/17 0017 13:48
 * @Discription todo
 */
public class TestTread implements Callable<String> {
    private String name;

    public TestTread(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(name);
        return name;
    }
}
