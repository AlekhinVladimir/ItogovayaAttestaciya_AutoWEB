import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;



public class BookingService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BookingService.class);
    private static final String DB_FILE = "bookings.txt";

    public boolean book(String userId, LocalDateTime from, LocalDateTime to) throws CantBookException {
        if (checkTimeInBD(from, to)) {
            return createBook(userId, from, to);
        }
        throw new CantBookException("Слот уже занят");
    }

    public boolean checkTimeInBD(LocalDateTime from, LocalDateTime to) {
        try {
            List<String> bookings = Files.readAllLines(Paths.get(DB_FILE));
            for (String booking : bookings) {
                String[] parts = booking.split(",");
                LocalDateTime bookedFrom = LocalDateTime.parse(parts[1]);
                LocalDateTime bookedTo = LocalDateTime.parse(parts[2]);
                if (from.isBefore(bookedTo) && to.isAfter(bookedFrom)) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла базы данных", e);
            return false;
        }
    }

    public boolean createBook(String userId, LocalDateTime from, LocalDateTime to) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DB_FILE), StandardOpenOption.APPEND)) {
            writer.write(userId + "," + from.toString() + "," + to.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            logger.error("Ошибка при записи в файл базы данных", e);
            return false;
        }
    }
}