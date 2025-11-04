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


    }


}
