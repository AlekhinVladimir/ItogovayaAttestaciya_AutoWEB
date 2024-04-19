import org.junit.jupiter.api.*;
import org.mockito.*;
import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private BookingService bookingService;
    private static final String DB_FILE = "bookings.txt";

    @BeforeEach
    public void setUp() throws IOException {
        bookingService = Mockito.spy(new BookingService());
        // Очистка файла перед каждым тестом
        Files.write(Paths.get(DB_FILE), new byte[0]);
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        // Удаление файла после всех тестов
        Files.deleteIfExists(Paths.get(DB_FILE));
    }

    @Test
    public void testBookSlotAvailable() throws CantBookException, IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(1);

        assertTrue(bookingService.book("user1", from, to));

        // Проверяем, что запись добавлена в файл
        List<String> bookings = Files.readAllLines(Paths.get(DB_FILE));
        assertTrue(bookings.contains("user1," + from.toString() + "," + to.toString()));
    }

    @Test
    public void testBookSlotUnavailable() throws IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(1);

        // Добавляем предварительную запись в файл
        Files.write(Paths.get(DB_FILE), ("user1," + from.toString() + "," + to.toString()).getBytes(), StandardOpenOption.APPEND);

        assertThrows(CantBookException.class, () -> bookingService.book("user2", from, to));

        // Проверяем, что в файле только одна запись
        List<String> bookings = Files.readAllLines(Paths.get(DB_FILE));
        assertEquals(1, bookings.size());
    }

    @Test
    public void testBookingOverlap() throws IOException {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(2);

        // Добавляем предварительную запись в файл
        Files.write(Paths.get(DB_FILE), ("user1," + from.toString() + "," + to.toString()).getBytes(), StandardOpenOption.APPEND);

        // Пытаемся забронировать перекрывающийся слот времени
        LocalDateTime newFrom = from.plusHours(1);
        LocalDateTime newTo = newFrom.plusHours(1);

        assertThrows(CantBookException.class, () -> bookingService.book("user2", newFrom, newTo));

        // Проверяем, что новая запись не добавлена
        List<String> bookings = Files.readAllLines(Paths.get(DB_FILE));
        assertEquals(1, bookings.size());
    }
}