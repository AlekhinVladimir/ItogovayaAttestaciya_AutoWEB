interface SearchService {
    LibraryItem findByInventoryNumber(String inventoryNumber);
    LibraryItem findByAuthor(String author);
}