import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;


public class RunTest {
    public static int reverseInt(int usernumber) {                             // метод reverseInt - принимает целое число и возвращает число в обратном порядке
        int number = usernumber;                                               // целочисленная переменная number принимает значение usernumber
        String reversed = "";                                                  // переменная reversed типа String принимает пустое значение
        if (number <= 0)                                                       // Если number меньше или равно 0, то
            throw new IllegalArgumentException("Введите положительное число"); // выбрасываем исключение IllegalArgumentException с сообщением "Введите положительное число"

        while (number > 0) {                                                   // цикл, выполняется пока number > 0
            int lastDigit = number % 10;                                       // помещаем в переменную lastDigit последнюю цифру числа из переменной number
            reversed = reversed + Integer.toString(lastDigit);                 // в переменную reversed помещаем значение из самой себя и добавляем последнюю цифру числа из переменной number, преобразовав ее значение в строку
            number = number / 10;                                              // убираем из переменной number последнее число, выполнив целочисленное деление на 10.
        }
        return Integer.parseInt(reversed);                                     // после завершения работы цикла возвращаем значение из переменной reversed, преобразовав его в целое число
    }


    @BeforeAll
    public static void beforeAll() {
        System.out.println("Начало тестирования");

    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Окончание тестирования");
    }


    @Test
    public void reverseIntTestPositive() {                                          // вводим положительное значение. Ожидаем, что выведется число в обратном порядке. Тест пройдет.
        Assertions.assertEquals(1234, reverseInt(4321));
    }

    @Test
    public void reverseIntTestExceptionNegative() {                                 // вводим отрицательное значение. Ожидаем, что выведется число в обратном порядке. Тест не пройдет.
        Assertions.assertEquals(-1234, reverseInt(4321));
    }

    @Test
    public void reverseIntTestExceptionNull() {                                     // тест на проверку исключения. Ожидаем, что при вводе 0 сработает исключение. Тест пройдет.
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            reverseInt(0);
        });
    }

    @Test
    public void reverseIntTestExceptionNegativeException() {                        // тест на проверку исключения. Ожидаем, что при вводе отрицательного числа сработает исключение. Тест пройдет.
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            reverseInt(-6547);
        });
    }

    @Test
    public void reverseIntTestBig() {                                               // число с большим значением 214748364, ожидаем, что выведется число в обратном порядке. Тест пройдет.
        Assertions.assertEquals(214748364, reverseInt(463847412));
    }

    @Test
    public void reverseIntTestNullsBefore() {                                       // число с нолями впереди, ожидаем, что выведется число в обратном порядке. Тест пройдет.
        Assertions.assertEquals(0005, reverseInt(5000));
    }

    @Test
    public void reverseIntTestNulls() {                                             // вводим 0, тест падает в исключение java.lang.IllegalArgumentException: Введите положительное число
        Assertions.assertEquals(0, reverseInt(0));
    }

    public boolean isAdult(String birthdayDate) {                                   // метод isAdult - принимает дату рождения в формате "YYYY-MM-DD" и проверяет, исполнилось ли пользователю 18 лет. Если да, возвращает true, если нет, то false
        try {                                                                       // блок try с кодом, которое может вызвать исключение
            LocalDate dateOfBirth = LocalDate.parse(birthdayDate);                  // создаем объект dateOfBirth типа LocalDate, строка birthdayDate преобразуется в объект LocalDate
            LocalDate currentDate = LocalDate.now();                                // создаем объект currentDate типа LocalDate, представляет текущую дату
            Period periodBetween = Period.between(dateOfBirth, currentDate);        // создаем объект periodBetween типа Period, представляет разницу в годах, месяцах, днях между объектами dateOfBirth и currentDate
            return periodBetween.getYears() >= 18;                                  // получаем количество лет с помощью getYears() и сравниваем с 18, если больше, то возвращается true, если меньше, то - false.
        } catch (Exception e) {                                                     // блок catch - если какой-либо код из блока try не смог выполнится, то
            return false;                                                           // возвращаем false
        }
    }

    @Test
    public void ageCheckerTestAdult() {                             // дата рождения совершеннолетнего, ожидаем, что вернется true. Тест пройдет.
        Assertions.assertTrue(isAdult("2000-02-05"));
    }


    @Test
    public void ageCheckerTestChild() {                             // дата рождения ребенка, ожидаем, что вернется false. Тест пройдет.
        Assertions.assertFalse(isAdult("2013-02-05"));
    }
    @Test
    public void ageCheckerTestBirthDay() {                          // граничное значение, День рождения 18 лет назад, день и месяц совпадают с сегодняшней датой, ожидаем, что вернется true. Тест пройдет.
        Assertions.assertTrue(isAdult("2006-02-20"));
    }
    @Test
    public void ageCheckerTestBirthDayFuture() {                    // др в будущем, ожидаем, что вернется false. Тест пройдет.
        Assertions.assertFalse(isAdult("2026-02-20"));
    }
    @Test
    public void ageCheckerTestBirthDayIncorrectFormat() {           // некорректный формат ввода даты, ожидаем, что вернется false. Тест пройдет.
        Assertions.assertFalse(isAdult("20260220"));
    }

}