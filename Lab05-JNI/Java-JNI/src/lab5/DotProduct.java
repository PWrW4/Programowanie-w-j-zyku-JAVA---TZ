package lab5;

public class DotProduct {

    static {
        System.loadLibrary("dotProduct");
        // Load native library hello.dll (Windows) or libhello.so (Unixes)
        //  at runtime
        // This library contains a native method called sayHello()
    }

    public double[] a = {1.0,3.0,-5.0};
    public double[] b = {4.0,-2.0,-1.0};
    public double c = -999.0;

    public void printVariableC(){
        System.out.println(c);
    }

    public double multi00(double[] a, double[] b){
        double sum = 0.0;

        for (int i=0;i<a.length;i++){
            sum += a[i] * b[i];
        }

        return sum;
    };

    public native double multi01(double[] a, double[] b);

    // zakładamy, że po stronie kodu natywnego wyliczony zostanie iloczyn skalarny dwóch wektorów
    public native double multi02(double[] a);

    // zakładamy, że drugi atrybut będzie pobrany z obiektu przekazanego do metody natywnej
    public native void multi03();
    // zakładamy, że po stronie natywnej utworzone zostanie okienko na atrybuty,
    // a po ich wczytaniu i przepisaniu do a,b obliczony zostanie wynik.
    // Wynik powinna wyliczać metoda Javy multi04
    // (korzystająca z parametrów a,b i wpisująca wynik do c).

    // mnoży a i b, wynik wpisuje do c
    public void multi04() {
        double sum = 0.0;

        for (int i=0;i<a.length;i++){
            sum += a[i] * b[i];
        }

        c = sum;
    }

}
