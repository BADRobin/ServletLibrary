package oleg.bryl.entity;

public class BookInfo extends BaseEntity {

    private int amount;
    private Book book;

    public BookInfo() {
        book = new Book();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return getId() + "/" + amount + "/" + book;
    }
    }
