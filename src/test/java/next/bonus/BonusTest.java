package next.bonus;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BonusTest {
    private static final Logger logger = LoggerFactory.getLogger(BonusTest.class);


    @Test
    @DisplayName("테스트1: @ElapsedTime 애노테이션일 설정되어 있는 메소드를 자동으로 실행한다.")
    public void run() throws Exception {
        Class<Bonus> clazz = Bonus.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ElapsedTime.class)) {
                long start = System.currentTimeMillis();
                method.invoke(clazz.getDeclaredConstructor().newInstance());
                long end = System.currentTimeMillis();
                logger.debug("\ncurrent method name : {} \nprocessing time : {}", method.getName(), end - start);
            }
        }
    }

}
