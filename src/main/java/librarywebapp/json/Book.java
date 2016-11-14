package librarywebapp.json;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

public final class Book implements JSONConvertible {
    private final String isn, author, title, borrower_name;
    
    public Book(String isn) {
        this.isn = isn;
        author = "";
        title = "";
        borrower_name = "";
    }

    public Book(String isn, String author, String title, String borrower_name) {
        this.isn = isn;
        this.author = author;
        this.title = title;
        this.borrower_name = borrower_name;
    }
    
    public Book(HttpServletRequest request) {
        isn = request.getParameter("isn");
        author = request.getParameter("author");
        title = request.getParameter("title");
        borrower_name = request.getParameter("borrower_name");
    }
    
    public String getIsn() {
        return isn;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getBorrowerName() {
        return borrower_name;
    }
    
    @Override
    public String toJSON() {
        String res = "{";
        
        res += "\"isn\":\"" + isn + "\",";
        res += "\"author\":\"" + author + "\",";
        res += "\"title\":\"" + title + "\",";
        res += "\"borrower_name\":\"" + borrower_name + '\"';
        
        return res + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean res = obj != null;
        
        if (res) {
            res = getClass() == obj.getClass();

            if (res) {
                Book book = (Book) obj;
                res = isn.equals(book.getIsn());
            }
        }

        return res;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.isn);
        return hash;
    }
}
