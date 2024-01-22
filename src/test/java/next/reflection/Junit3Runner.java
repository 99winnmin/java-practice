package next.reflection;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Junit3Runner {
    @Test
    @DisplayName("테스트1: test 로 시작하는 메소드를 자동으로 실행한다.")
    public void runner() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("test")) {
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }
    }
}
