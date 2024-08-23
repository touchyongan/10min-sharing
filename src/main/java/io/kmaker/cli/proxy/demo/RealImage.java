package io.kmaker.cli.proxy.demo;

public class RealImage implements Image, Testable {

    @Override
    public void render() {
        System.out.println("This is real image...");
    }

    @Override
    public void call() {
        System.out.println("Image of callable");
    }
}
