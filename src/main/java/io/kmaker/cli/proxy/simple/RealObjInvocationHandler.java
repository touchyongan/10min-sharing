package io.kmaker.cli.proxy.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RealObjInvocationHandler implements InvocationHandler {
    private RealObj realObj;

    public RealObjInvocationHandler(RealObj realObj) {
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {

        // pre processing

        final var returnValue = method.invoke(realObj, args);

        // post processing

        return returnValue;
    }
}
