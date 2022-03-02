package jpql.domain.item;

import javax.persistence.Entity;

@Entity
public class Book extends Item{

    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
