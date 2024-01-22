package next.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() throws Exception {
        SoftAssertions s = new SoftAssertions();
        Class<Question> clazz = Question.class;
        logger.debug("Class Name {}", clazz.getName());
    }

    @Test
    @DisplayName("테스트2: 리플렉션을 이용해서 클래스의 생성자 정보를 출력해야 한다.")
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            StringBuilder sb = new StringBuilder();
            Class[] parameterTypes = constructor.getParameterTypes();
            sb.append("\nconstructor name : ").append(constructor.getName()).append("\n");
            sb.append("parameter length : ").append(parameterTypes.length).append("\n");
            for (Class paramType : parameterTypes) {
                sb.append("param type : ").append(paramType).append("\n");
            }
            logger.debug(sb.toString());
        }
    }

    @Test
    @DisplayName("테스트3: 리플렉션을 이용해서 클래스의 필드 정보를 출력해야 한다.")
    public void field() {
        Class<Question> clazz = Question.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            logger.debug("\nfield name : {} \nfield type : {}", field.getType(), field.getName());
        }
    }

    @Test
    @DisplayName("테스트4: 리플렉션을 이용해서 클래스의 메소드 정보를 출력해야 한다.")
    public void method() throws Exception {
        Class<Question> clazz = Question.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            StringBuilder sb = new StringBuilder();
            sb.append("\nmethod name : ").append(method.getName()).append("\n");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class paramType : parameterTypes) {
                sb.append("param type : ").append(paramType).append("\n");
            }
            sb.append("return type : ").append(method.getReturnType()).append("\n");
            logger.debug(sb.toString());
        }
    }

    @Test
    @DisplayName("테스트5: 자바 Reflection API를 활용해 다음 Student 클래스의 name과 age 필드에 값을 할당한 후 getter 메소드를 통해 값을 확인한다.")
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        try {
            Student student = new Student();

            Field nameField = clazz.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(student, "유승민");

            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(student, 26);

            assertThat(student.getName()).isEqualTo("유승민");
            assertThat(student.getAge()).isEqualTo(26);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
