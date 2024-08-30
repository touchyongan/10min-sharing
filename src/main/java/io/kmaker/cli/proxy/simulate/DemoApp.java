package io.kmaker.cli.proxy.simulate;

import io.kmaker.cli.proxy.simulate.entity.People;

import java.lang.reflect.Proxy;

public class DemoApp {

    public static void main(String[] args) throws Exception {
//        callDirectly();
        callViaProxy();
    }

    static void callDirectly() {
        PeopleService service = new PeopleServerImpl();
        People people = new People();
        people.setFirstName("John");
        people.setLastName("Smith");
        people.setGender("Female");
        service.add(people);
    }

    static void callViaProxy() {
        // call via proxy
        PeopleServerImpl imp = new PeopleServerImpl();
        TransactionProxyHandler proxyHandler = new TransactionProxyHandler(imp);
        PeopleService proxy = (PeopleService) Proxy.newProxyInstance(
                PeopleService.class.getClassLoader(),
                new Class[] {PeopleService.class}, proxyHandler);

        People people = new People();
        people.setFirstName("John");
        people.setLastName("Smith");
        people.setGender("Female");
        proxy.add(people);
    }
}
