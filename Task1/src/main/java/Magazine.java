class Magazine extends LibraryItem {
    private String issue;

    public Magazine(String inventoryNumber, String author, String issue) {
        super(inventoryNumber, author);
        this.issue = issue;
    }

    @Override
    public void display() {
        System.out.println("Журнал: " + issue + ", Автор: " + getAuthor() + ", Инв. номер: " + getInventoryNumber());
    }
}
