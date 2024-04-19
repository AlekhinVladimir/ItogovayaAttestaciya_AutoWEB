import java.util.List;

class SimpleSearchService implements SearchService {
    private List<LibraryItem> items;

    public SimpleSearchService(List<LibraryItem> items) {
        this.items = items;
    }

    @Override
    public LibraryItem findByInventoryNumber(String inventoryNumber) {
        for (LibraryItem item : items) {
            if (item.getInventoryNumber().equals(inventoryNumber)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public LibraryItem findByAuthor(String author) {
        for (LibraryItem item : items) {
            if (item.getAuthor().equals(author)) {
                return item;
            }
        }
        return null;
    }
}
