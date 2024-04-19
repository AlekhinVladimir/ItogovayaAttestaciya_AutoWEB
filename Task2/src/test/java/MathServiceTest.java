import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class MathServiceTest {

    private final MathService mathService = new MathService();

    // Параметризованный тест для проверки корректного вычисления корней
    @ParameterizedTest
    @CsvSource({
            "1, -3, 2, 2.0, 1.0", // два различных корня
            "1, 2, 1, -1.0, -1.0", // один корень (дискриминант равен нулю)

    })
    public void testGetAnswer_ValidCoefficients_ShouldReturnCorrectRoots(int a, int b, int c, double expectedFirst, double expectedSecond) throws NotFoundAnswerException {
        Pair result = mathService.getAnswer(a, b, c);
        Assertions.assertEquals(expectedFirst, result.first, 0.001, "Первый корень не соответствует ожидаемому");
        Assertions.assertEquals(expectedSecond, result.second, 0.001, "Второй корень не соответствует ожидаемому");
    }

    // Негативный тест для проверки ситуации с отрицательным дискриминантом
    @Test
    public void testGetAnswer_NegativeDiscriminant_ShouldThrowException() {
        int a = 1, b = 1, c = 1; // Дискриминант будет отрицательным
        Executable executable = () -> mathService.getAnswer(a, b, c);
        Assertions.assertThrows(NotFoundAnswerException.class, executable, "Должно быть выброшено исключение NotFoundAnswerException");
    }

    // Тест для проверки вычисления дискриминанта
    @Test
    public void testGetD_ShouldReturnCorrectDiscriminant() {
        int a = 1, b = -3, c = 2;
        int expectedD = 1;
        int resultD = mathService.getD(a, b, c);
        Assertions.assertEquals(expectedD, resultD, "Вычисленный дискриминант не соответствует ожидаемому");
    }
}