class Book extends LibraryItem {
    private String title;

    public Book(String inventoryNumber, String author, String title) {
        super(inventoryNumber, author);
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public void display() {
        System.out.println("Книга: " + title + ", Автор: " + getAuthor() + ", Инв. номер: " + getInventoryNumber());
    }
}