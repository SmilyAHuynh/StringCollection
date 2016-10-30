package multiStringCollection;

public class MultiStringCollClient {
    public static final int SENTINEL = 0;
    public static void main(String[] args) {
        MultiStringColl P = new MultiStringColl(), N = new MultiStringColl();

        String[] A = new String[10];
        A[0] = "abc";
        A[1] = "def";
        A[2] = "hello";
        A[3] = "bye bye";
        A[4] = "ABC";
        A[5] = "DEF";
        A[6] = "123";
        A[7] = "456";
        A[8] = "789";
        A[9] = "";
        for (int i = 0; i < A.length; P.insert(A[i++]));

        System.out.println("First collection:");
        P.print();
        N.copy(P);
        System.out.println("\nFirst after copy:");
        P.print();
        System.out.println("\nSecond after copy:");
        N.print();

        System.out.println("\nP.equals(N):" + P.equals(N));
        System.out.println("\nP.belongs(\"hello\"):" + P.belongs("hello"));
        P.omit("hello");
        P.omit("abc");
        System.out.println("\nbelongs('hello'): " + P.belongs("hello"));
        System.out.println("belongs('abc'): " + P.belongs("abc"));
        System.out.println("\nHow many: " + P.get_howmany());
        System.out.println();
        P.print();
    }
}
