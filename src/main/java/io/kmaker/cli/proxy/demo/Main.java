package io.kmaker.cli.proxy.demo;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        Image proxyImage = new ProxyImage(new RealImage());
        proxyImage.render();

        System.out.println("--------------");

        Image image = new RealImage();
        ImageInvocation invocation = new ImageInvocation(image);
        Image proxy = (Image) Proxy.newProxyInstance(Image.class.getClassLoader(), new Class[]{ Image.class}, invocation);
        proxy.render();

        System.out.println("------------");

        Testable testable = new TestImpl();
        ImageInvocation invocation1 = new ImageInvocation(testable);
        Testable proxy1 = (Testable) Proxy.newProxyInstance(Image.class.getClassLoader(), new Class[]{ Testable.class}, invocation1);
        proxy1.call();


        System.out.println("---------Multi interface-------------");
        var multInt1 = (Testable) Proxy.newProxyInstance(Testable.class.getClassLoader(), new Class[]{ Image.class, Testable.class}, invocation1);
        multInt1.call();

        invocation1 = new ImageInvocation(new RealImage());
        var multInt2 = (Image) Proxy.newProxyInstance(Image.class.getClassLoader(), new Class[]{ Image.class, Testable.class}, invocation1);
        multInt2.render();
    }
}
