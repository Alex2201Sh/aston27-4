package by.shumilov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDataBuilder {

    void test() throws SQLException, FileNotFoundException {
        Connection connection = ConnectionCreatorForTest.createConnection();
        Statement statement = null;
        statement = connection.createStatement();

        StringBuilder stringBuilder1 = getStringBuilder("src/test/java/sql/2.create_tables.sql");
        StringBuilder stringBuilder2 = getStringBuilder("src/test/java/sql/3.fill_tables.sql");

        statement.execute(stringBuilder1.toString());
        statement.execute(stringBuilder2.toString());

//        ResultSet resultSet = statement.executeQuery("SELECT * from positions");
//        while (resultSet.next()) {
//            String name = resultSet.getString("name");
//            System.out.println(name);
//        }
    }

    private static StringBuilder getStringBuilder(String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filename);
        try (FileReader fr = new FileReader(file)) {
            int content;
            while ((content = fr.read()) != -1) {
                stringBuilder.append((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
