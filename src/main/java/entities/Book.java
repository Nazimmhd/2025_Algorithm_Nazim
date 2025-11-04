package entities;

public class Book implements Comparable<Book>{
    private final String isbn;
    private final String title;
    private final String author;

    public Book(String isbn, String title, String author) {
        validateISBN(isbn);
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    private static void validateISBN(String isbn) {
        if(isbn == null || isbn.length() != 10){
            throw new IllegalArgumentException("All books must contain a valid ISBN value. A valid ISBN contains 10 " +
                    "characters.");
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Checks if this book is equal to the supplied Book parameter. Two Books are considered equal if they contain
     * the same ISBN
     * @param o the Book to compare against
     * @return true if the supplied Book is considered equal to this Book, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    /**
     * Creates a string representation of the data held in this Book object.
     * @return A string representing this Book's data, in the form: "$title by $author - ISBN: $isbn"
     */
    public String format(){
        return "\"" + title + "\" by " + author + " - ISBN: " + isbn;
    }

    /**
     * Compares two Books by title (ascending). Where titles are equal, books are compared by author (ascending)
     * @param other the object to be compared.
     * @return 0 if the two books are equal, < 0 if this book should come before the parameter book when sorted in
     * ascending order, > 0 if this book should come after the parameter book when sorted in ascending order.
     * @throws IllegalArgumentException If the Book supplied as a parameter is null
     */
    public int compareTo(Book other){
        if(other == null){
            throw new IllegalArgumentException("Cannot compare to a null Book");
        }

        int titleComparison = this.title.compareTo(other.title);
        if(titleComparison == 0){
            return this.author.compareTo(other.author);
        }

        return titleComparison;
    }
}
