package io.kmaker.cli.proxy.simulate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Objects;

public class TransactionProxyHandler implements InvocationHandler {

    private final Object target;

    public TransactionProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {

        // Find the corresponding method in the implementation class
        final var targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        // Get the annotation from the implementation class's method
        final var transactional = targetMethod.getDeclaredAnnotation(Transactional.class);

        final Connection connection;
        if (Objects.nonNull(transactional)) {
            connection = ConnectionManager.getConnection(false);
        } else {
            connection = ConnectionManager.getConnection();
        }
        final var autoCommit = connection.getAutoCommit();
        Object returnObj = null;
        try {
            returnObj = method.invoke(target, args);
            if (!autoCommit) {
                ConnectionManager.commit();
            }
        } catch (final Exception e) {
            if (!autoCommit) {
                ConnectionManager.rollback();
            }
            throw e;
        } finally {
            connection.close();
        }
        return returnObj;
    }
}
