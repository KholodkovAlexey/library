package librarywebapp.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DbHandler {

    static public final JdbcTemplate JT = InitDb();

    static {
        CreateTables();
        InsertData();
    }

    static private JdbcTemplate InitDb() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:./mem");
        dataSource.setPassword("");

        return new JdbcTemplate(dataSource);
    }

    static private void CreateTables() {
        JT.execute("DROP TABLE books IF EXISTS");
        JT.execute("DROP TABLE users IF EXISTS");

        JT.execute("CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(40) NOT NULL UNIQUE, password VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY (id))");

        JT.execute("CREATE TABLE books (isn VARCHAR(40) NOT NULL UNIQUE,"
                + "author VARCHAR(40) NOT NULL,title VARCHAR(40) NOT NULL,"
                + "borrower_id INT,FOREIGN KEY (borrower_id) REFERENCES users(id))");
    }

    static private void InsertData() {
        JT.execute("INSERT INTO users (name, password) VALUES ('John','pass1')");

        JT.execute("INSERT INTO users (name, password) VALUES ('James','pass2')");

        JT.execute("INSERT INTO users (name, password) VALUES ('Jerry','pass3')");

        JT.execute("INSERT INTO users (name, password) VALUES ('Carl','pass4')");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('1486-2549-2114-5686','Author1','Title1',2)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('1486-2549-2114-5687','Author1','Title2',2)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('1486-2549-2114-5688','Author1','Title3',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0001','TestAuthor','TestTitle1',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0002','TestAuthor','TestTitle2',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0003','TestAuthor','TestTitle3',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0004','TestAuthor','TestTitle4',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0005','TestAuthor','TestTitle5',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0006','TestAuthor','TestTitle6',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0007','TestAuthor','TestTitle7',null)");

        JT.execute("INSERT INTO books (isn, author, title, borrower_id)\n"
                + "	VALUES ('0000-0000-0008','TestAuthor','TestTitle8',null)");

    }

    private DbHandler() {

    }
}
