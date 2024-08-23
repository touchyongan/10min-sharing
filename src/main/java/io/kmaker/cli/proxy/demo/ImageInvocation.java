package io.kmaker.cli.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ImageInvocation implements InvocationHandler {

    private Object target;

    public ImageInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {

        System.out.println("Business logic before call real obj");
        Object obj = method.invoke(target, args);
        System.out.println("Business logic after call real obj");
        return obj;
    }
}
