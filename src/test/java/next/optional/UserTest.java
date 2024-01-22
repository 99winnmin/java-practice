package next.optional;


import static next.optional.User.ageIsInRange1;
import static next.optional.User.ageIsInRange2;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertThat(ageIsInRange1(new User("crong", 35))).isTrue();
        assertThat(ageIsInRange1(new User("crong", 48))).isFalse();
        assertThat(ageIsInRange1(new User("crong", null))).isFalse();
        assertThat(ageIsInRange1(new User("crong", 29))).isFalse();
        assertThat(ageIsInRange1(null)).isFalse();
    }
    
    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertThat(ageIsInRange2(new User("crong", 35))).isTrue();
        assertThat(ageIsInRange2(new User("crong", 48))).isFalse();
        assertThat(ageIsInRange2(new User("crong", null))).isFalse();
        assertThat(ageIsInRange2(new User("crong", 29))).isFalse();
        assertThat(ageIsInRange2(null)).isFalse();
    }

    @Test
    @DisplayName("테스트1: User 클래스의 인스턴스를 자바 Reflection API를 활용해 User 인스턴스를 생성한다.")
    public void createInstance() throws Exception {
        Class<User> clazz = User.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();
            Object[] parameterTypes = new Object[parameters.length];

            assertThat(clazz)
                .isEqualTo(constructor.newInstance(parameterTypes).getClass());
        }
    }
}
