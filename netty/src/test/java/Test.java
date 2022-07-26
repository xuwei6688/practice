import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer i = 1 << 1;
        System.out.println(i);
    }
}
