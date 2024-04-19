import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Инициализация реестра с объектами
        List<LibraryItem> items = new ArrayList<>();
        items.add(new Book("123", "Лев Толстой", "Война и мир"));
        items.add(new Magazine("456", "Наталья Нарочницкая", "Исторический журнал"));

        // Инициализация сервиса поиска
        SearchService searchService = new SimpleSearchService(items);

        // Поиск и вывод на экран по инвентарному номеру
        LibraryItem foundByInventory = searchService.findByInventoryNumber("123");
        if (foundByInventory != null) {
            foundByInventory.display();
        }

        // Поиск и вывод на экран по автору
        LibraryItem foundByAuthor = searchService.findByAuthor("Лев Толстой");
        if (foundByAuthor != null) {
            foundByAuthor.display();
        }
    }
}