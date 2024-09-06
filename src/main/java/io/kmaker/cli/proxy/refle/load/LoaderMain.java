package io.kmaker.cli.proxy.refle.load;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.List;

public class LoaderMain {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        final var pair = loadEntryPoint("io.kmaker.cli.proxy.refle.load.TestStaticDataset", "crawl");
        final var obj = pair.get(0);
        final var method = (Method) pair.get(1);
        method.invoke(obj, new Context());
    }

    public static List<Object> loadEntryPoint(final String module,
                                                      final String methodName) {
        try {
            final var clz = Class.forName(module);
            final var method = clz.getDeclaredMethod(methodName, Context.class);
            final var con = clz.getDeclaredConstructor();
            final var obj = con.newInstance();
            return List.of(obj, method);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
