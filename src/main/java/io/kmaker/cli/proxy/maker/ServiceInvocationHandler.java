package io.kmaker.cli.proxy.maker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {

    private Service service;

    public ServiceInvocationHandler(Service service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {

        final var returnObj = method.invoke(service, args);

        return returnObj;
    }
}
