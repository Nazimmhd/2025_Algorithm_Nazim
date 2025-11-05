package utils;

import entities.Book;

import java.util.Arrays;
import java.util.Random;

public class BookGenerator {
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random randomGen = new Random();
    private static String[] authors = {
            "William Shakespeare",
            "Jane Austen",
            "Mark Twain",
            "Charles Dickens",
            "Leo Tolstoy",
            "Rick Riordan",
            "George Orwell",
            "J.K. Rowling",
            "Ernest Hemingway",
            "F. Scott Fitzgerald",
            "Agatha Christie",
            "J.R.R. Tolkien",
            "Toni Morrison",
            "Suzanne Collins",
            "Lewis Carroll",
            "Thomas Flintham"
    };

    private static String[] titles = {
            "Charlotte's Web",
            "Matilda",
            "The Very Hungry Caterpillar",
            "The Tale of Peter Rabbit",
            "The Cat in the Hat",
            "Where the Wild Things Are",
            "Winnie-the-Pooh",
            "Alice's Adventures in Wonderland",
            "The Wind in the Willows",
            "The Little Prince",
            "Percy Jackson and the Olympians: The Lightning Thief",
            "The Hunger Games",
            "Divergent",
            "The Maze Runner",
            "Twilight",
            "The Fault in Our Stars",
            "The Giver",
            "A Wrinkle in Time",
            "Pride and Prejudice",
            "Jane Eyre",
            "Wuthering Heights",
            "To Kill a Mockingbird",
            "The Great Gatsby",
            "1984",
            "Moby-Dick",
            "Crime and Punishment",
            "Great Expectations",
            "Frankenstein",
            "Brave New World",
            "Fahrenheit 451",
            "The Handmaid's Tale",
            "Animal Farm",
            "Lord of the Flies",
            "The Road",
            "Never Let Me Go",
            "The Book Thief",
            "Station Eleven",
            "The Power",
            "Life of Pi",
            "The Alchemist",
            "The Kite Runner",
            "The Shadow of the Wind",
            "The Secret Garden",
            "Anne of Green Gables",
            "Little Women",
            "Coraline",
            "Goodnight Moon",
            "Peter Pan"
    };

    /**
     * Generate the specified number of random Book objects, stored in an array.
     * <p>
     * Each Book is generated using fixed author and title information, plus a randomly generated ISBN (with length 10).
     * Up to 4 duplicate books will be placed in randomly selected positions within the array.
     * </p>
     *
     * @return The specified number of randomly generated Book objects, stored in an array
     */
    public static Book [] getRandomArray(){
        Book [] books = new Book[numBooks];
        for (int i = 0; i < numBooks; i++) {
            books[i] = generateBook();
        }

        // Add duplicates
        Book duplicate = books[books.length - 1];
        // Add up to 4 duplicates to the array
        int numDuplicates = randomGen.nextInt(1, 5);
        for (int i = 0; i < numDuplicates; i++) {
            // Randomly choose a position to insert at
            int randomIndex = randomGen.nextInt(books.length);
            books[randomIndex] = duplicate;
        }

        return books;
    }

    /**
     * Generate a Book containing random title, author and ISBN values.
     * @return a Book containing a randomly generated ISBN and a randomly selected title and author
     */
    public static Book generateBook(){
        int authorIndex = randomGen.nextInt(authors.length);
        String randomAuthor = authors[authorIndex];

        int titleIndex = randomGen.nextInt(titles.length);
        String randomTitle = titles[titleIndex];

        String randomIsbn = generateISBN();

        return new Book(randomIsbn, randomTitle, randomAuthor);
    }

    /**
     * Generate an ISBN
     * @return a 10-character string for use as an ISBN
     */
    public static String generateISBN(){
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int charIndex = randomGen.nextInt(characters.length());
            isbn.append(characters.charAt(charIndex));
        }

        return isbn.toString();
    }

    public static void main(String[] args) {
        // Generate a book and display it
        Book randomBook = generateBook();
        System.out.println(randomBook.format());

        // Generate an array of 10 random books and display it
        Book [] books = getRandomArray();
        for(Book b: books){
            System.out.println(b);
        }

        System.out.println("----------------------");

        // Sort the array and display it
        Arrays.sort(books);
        for(Book b: books){
            System.out.println(b);
        }
    }
}
