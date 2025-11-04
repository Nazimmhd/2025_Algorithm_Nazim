import entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsEmptyIndexTest {
    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void emptyIndex_NullArray() {
        Book[] books = null;
        assertThrows(IllegalArgumentException.class,
            () -> {
                BookUtils.emptyIndex(books, 0);
            }
            , "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle indices of less than 0
     */
    @Test
    void emptyIndex_IllegalIndex_LessThanZero() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        assertThrows(IndexOutOfBoundsException.class,
            () -> {
                BookUtils.emptyIndex(books, -1);
            }
            , "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle indices greater than length of array
     */
    @Test
    void emptyIndex_IllegalIndex_GreaterThanLength() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    BookUtils.emptyIndex(books, 4);
                }
                , "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle indices equal to length of array
     */
    @Test
    void emptyIndex_IllegalIndex_EqualToLength() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    BookUtils.emptyIndex(books, 3);
                }
                , "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly overwrite a book at the start of the array
     */
    @Test
    void emptyIndex_LegalIndex_ReplaceBookAtStart() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        int selectedIndex = 0;
        Book expResult = new Book(books[selectedIndex].getIsbn(), books[selectedIndex].getTitle(), books[selectedIndex].getAuthor());

        Book result = BookUtils.emptyIndex(books, selectedIndex);

        assertEquals(expResult, result);
        assertNull(books[selectedIndex]);
    }

    /**
     * Confirm method can correctly overwrite a book at the end of the array
     */
    @Test
    void emptyIndex_LegalIndex_ReplaceBookAtEnd() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        int selectedIndex = books.length-1;
        Book expResult = new Book(books[selectedIndex].getIsbn(), books[selectedIndex].getTitle(), books[selectedIndex].getAuthor());

        Book result = BookUtils.emptyIndex(books, selectedIndex);

        assertEquals(expResult, result);
        assertNull(books[selectedIndex]);
    }

    /**
     * Confirm method can correctly overwrite a book within the array
     */
    @Test
    void emptyIndex_LegalIndex_ReplaceBookInMiddle() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};

        int selectedIndex = 1;
        Book expResult = new Book(books[selectedIndex].getIsbn(), books[selectedIndex].getTitle(), books[selectedIndex].getAuthor());

        Book result = BookUtils.emptyIndex(books, selectedIndex);

        assertEquals(expResult, result);
        assertNull(books[selectedIndex]);
    }

    /**
     * Confirm method can correctly overwrite a null slot within the array
     */
    @Test
    void emptyIndex_LegalIndex_ReplaceNullSlotInArray() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = null;
        Book b3 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2, b3};

        int selectedIndex = 2;
        Book result = BookUtils.emptyIndex(books, selectedIndex);

        assertNull(result);
        assertNull(books[selectedIndex]);
    }
}