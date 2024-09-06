package io.kmaker.cli.proxy.refle;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
        Usage.from(new Parent());
        Usage.from(new Child());
    }
}

class Parent {
    public Parent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public static void from() {
        System.out.println("From Parent");
    }
}

class Child extends Parent {

    public static void from() {
        System.out.println("From Children");
    }
}

class Usage {
    static <T extends Parent> void from(final T t) {
        ReflectionUtil.invokeMethod(t.getClass(), "from");
    }
}

class ReflectionUtil {

    @SneakyThrows
    public static <T> T newInstance(final Class<T> clz,
                                    final List<Class<?>> parameterTypes,
                                    final Object... initArgs) {
        var paramTypes = Objects.isNull(parameterTypes) || parameterTypes.isEmpty() ? List.<Class<?>>of() : parameterTypes;
        var con = clz.getDeclaredConstructor(paramTypes.toArray(Class[]::new));
        return con.newInstance(initArgs);
    }

    @SneakyThrows
    public static <T> T invokeMethod(final Class<T> clz,
                                     final String methodName) {
        final var method = clz.getMethod(methodName);
        return (T) method.invoke(null);
    }
}
