package io.kmaker.cli.proxy.maker;

import java.lang.reflect.Proxy;

public class MarkerMain {

    public static void main(String[] args) {

//        final var bookService = new BookServiceImpl();
//        final var serviceHandler = new ServiceInvocationHandler(bookService);
//        final var proxy = (BookService) Proxy.newProxyInstance(BookServiceImpl.class.getClassLoader(), new Class[] {BookService.class}, serviceHandler);
//        proxy.bookInfo();

        final var proxy = getProxyOf(new BookServiceImpl(), BookService.class);
        proxy.bookInfo();

    }

    private static <T extends Service> T getProxyOf(T serviceInstanceImpl,
                                                    Class<T> serviceInterface) {
        final var serviceHandler = new ServiceInvocationHandler(serviceInstanceImpl);
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[] { serviceInterface}, serviceHandler);
    }
}
