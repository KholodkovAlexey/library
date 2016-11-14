package librarywebapp.db;

import java.sql.SQLException;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DbHandler {

    static private final SimpleDriverDataSource DATA_SOURCE = new SimpleDriverDataSource();
    static public final JdbcTemplate JT = InitDb();

    static {
        try {
            String fpath = "/home/alexey/NetBeansProjects/library/src/main/webapp/liquibase_init.sql";

            PrepareByLiquibase(fpath);
            
            System.out.println("Liquibase started");
        } catch (SQLException | LiquibaseException e) {
            System.out.println("Failed to prepare by liquibase: " + e);
            
        }
        PrepareDb();
    }

    static private JdbcTemplate InitDb() {
        DATA_SOURCE.setDriverClass(org.h2.Driver.class);
        DATA_SOURCE.setUsername("sa");
        DATA_SOURCE.setUrl("jdbc:h2:./mem;MV_STORE=FALSE");
        DATA_SOURCE.setPassword("");

        return new JdbcTemplate(DATA_SOURCE);
    }

    static private void PrepareByLiquibase(String path) throws SQLException, DatabaseException, LiquibaseException {
        java.sql.Connection connection = DATA_SOURCE.getConnection();

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

        Liquibase liquibase = new liquibase.Liquibase(path, new ClassLoaderResourceAccessor(), database);

        liquibase.update(new Contexts(), new LabelExpression());
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

    static public void PrepareDb() {
        CreateTables();
        InsertData();
    }

    private DbHandler() {

    }
}
