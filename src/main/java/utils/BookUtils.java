package utils;

import entities.Book;

public class BookUtils {
    /**
     * @param books is the array of Book objects to be displaed
     * @return a formatted String showing each Book's details or "No books found" if empty
     *
      */

    public static String toString(Book[] books){
       if (books==null||books.length==0){
           return "Nobooks found";
       }

       String result ="";
       boolean hasbook = false;

       for (int i=0;i< books.length;i++){
           result+=i;

           if (books[i]!=null){
               result+=books[i].format();
               hasbook=true;
           }
           else {
               result +=null;
           }
           result+="" ;

       }

return result;
    }
/**
 * @param books the array of Book object
 * @param target the Book object to be deleted
 * @return if the book was founda and deleted false otherwise
 */
public static boolean delete(Book[] books, Book target){
    if (books==null||target==null){
        return false;
    }
    for (int i=0;i< books.length;i++){
        if (target.equals(books[i])){
            for (int j=i ;j< books.length-1;j++){
                books[j]=books[j+1];
            }
            books[books.length-1]=null;
            return true;
        }
    }
    return false;
}
}
