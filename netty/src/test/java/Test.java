import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Po po = Po.class.getConstructor().newInstance();

    }
}
