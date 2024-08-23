package io.kmaker.cli.proxy.demo;

public class TestImpl implements Testable {

    @Override
    public void call() {
        System.out.println("This is test implementation");
    }
}
