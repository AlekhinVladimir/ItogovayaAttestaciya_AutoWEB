import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryTests {

    private List<LibraryItem> items;
    private SimpleSearchService searchService;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        items = new ArrayList<>();
        items.add(new Book("123", "Лев Толстой", "Война и мир"));
        items.add(new Magazine("456", "Наталья Нарочницкая", "Исторический журнал"));
        searchService = new SimpleSearchService(items);
    }

    @Test
    public void testBookDisplay() {
        Book book = new Book("123", "Лев Толстой", "Война и мир");
        assertEquals("Лев Толстой", book.getAuthor());
        assertEquals("123", book.getInventoryNumber());
    }

    @Test
    public void testMagazineDisplay() {
        Magazine magazine = new Magazine("456", "Наталья Нарочницкая", "Исторический журнал");
        assertEquals("Наталья Нарочницкая", magazine.getAuthor());
        assertEquals("456", magazine.getInventoryNumber());
    }

    @Test
    public void testFindByInventoryNumber() {
        LibraryItem foundItem = searchService.findByInventoryNumber("123");
        assertNotNull(foundItem);
        assertTrue(foundItem instanceof Book);
        assertEquals("Война и мир", ((Book) foundItem).getTitle());
    }

    @Test
    public void testFindByAuthor() {
        LibraryItem foundItem = searchService.findByAuthor("Лев Толстой");
        assertNotNull(foundItem);
        assertEquals("123", foundItem.getInventoryNumber());
    }

    @Test
    public void testFindByInventoryNumberNotFound() {
        LibraryItem foundItem = searchService.findByInventoryNumber("999");
        assertNull(foundItem);
    }

    @Test
    public void testFindByAuthorNotFound() {
        LibraryItem foundItem = searchService.findByAuthor("Неизвестный Автор");
        assertNull(foundItem);
    }
}
