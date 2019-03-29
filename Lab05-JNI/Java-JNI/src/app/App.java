package app;

import lab5.DotProduct;

public class App {

    // Test
    public static void main(String[] args) {
        DotProduct dot = new DotProduct(); // Create an instance and invoke the native method

        dot.multi03();
        dot.printVariableC();
        System.out.println(dot.multi00(dot.a,dot.b));
        System.out.println(dot.multi01(dot.a,dot.b));
        System.out.println(dot.multi02(dot.a));

    }

}