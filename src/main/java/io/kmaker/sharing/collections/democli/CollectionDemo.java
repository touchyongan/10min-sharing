package io.kmaker.sharing.collections.democli;

import java.util.ArrayList;
import java.util.List;

public class CollectionDemo {

    /*
    - usage of static collection method
    Implementation Notes
    1. The collections created using the factory methods are not commonly used implementations.
       e.g: List is not an ArrayList and the Map is not a HashMap. Those are different implementations that are introduced in Java 9.
       These implementations are internal and their constructors have restricted access.
    2. Immutable
    3. No null Element Allowed
    4. Value-Based Instances
    5. Serialization: Collections created from factory methods are Serializable if the elements of the collection are Serializable.
     */

    public static void main(String[] args) {
        case3();
    }

    static void case1() {
        List<String> list1 = List.of("A", "B", "C");
        System.out.println("Using List.of() : " + list1);

        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        System.out.println("Using ArrayList(): " + list2);
    }

    static void case2() {

        var list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        System.out.println("Using ArrayList(): " + list2);
        System.out.println("Want to add new element");
        list2.add("D");
        System.out.println("After add new element: " + list2);

        System.out.println("--------------------------------");

        var list1 = List.of("A", "B", "C");
        System.out.println("Using List.of() : " + list1);
        System.out.println("Want to add new element");
        list1.add("D");
        System.out.println("After add new element: " + list1);
    }

    static void case3() {

        var list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        System.out.println("Using ArrayList(): " + list2);
        System.out.println("Want to add new element");
        list2.add("D");
        System.out.println("After add new element: " + list2);

        System.out.println("--------------------------------");

        var list1 = new ArrayList<>(forCase3());
        System.out.println("Using List.of() : " + list1);
        System.out.println("Want to add new element");
        list1.add("D");
        System.out.println("After add new element: " + list1);
    }

    static List<String> forCase3() {
        return List.of("A", "B", "C");
    }


    static List<String> case4_1() {
        return List.of();
    }

    static List<String> case4_2() {
        return new ArrayList<>();
    }
}
