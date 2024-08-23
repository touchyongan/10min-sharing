package io.kmaker.cli.proxy.simple;

import java.lang.reflect.Proxy;

public class ProxyMain {

    public static void main(String[] args) {

        final var realObj = new RealObjImpl();
        final var handler = new RealObjInvocationHandler(realObj);
        final var proxy = (RealObj) Proxy.newProxyInstance(RealObjImpl.class.getClassLoader(), new Class[] { RealObj.class}, handler);
        System.out.println("Starting call via proxy....");
        proxy.printInfo();
    }
}
