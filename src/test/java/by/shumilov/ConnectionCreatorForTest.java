package by.shumilov;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreatorForTest {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader(getFileFromResource("test_database.properties")));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException | URISyntaxException e) {
            e.printStackTrace(); // fatal exception
        }

        DATABASE_URL = (String) properties.get("db.url");
    }

    private ConnectionCreatorForTest() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }

    public static File getFileFromResource(final String fileName)
            throws URISyntaxException {
        ClassLoader classLoader = ConnectionCreatorForTest.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return new File(resource.toURI());
        } else {
            throw new URISyntaxException(fileName, ": couldn't be parsed.");
        }
    }
}
