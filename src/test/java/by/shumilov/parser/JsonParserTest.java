package by.shumilov.parser;

import by.shumilov.bean.Department;
import by.shumilov.bean.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JsonParserTest {

    @Test
    void parseObjectToJson() {
        JsonParser parser = new JsonParser();
        Employee firstEmployee = new Employee("Sasha", "Beliy", "+375441234567",
                new Department("Administration"),new ArrayList<>());
        String s = parser.parseObjectToJson(firstEmployee);
        System.out.println(s);
    }
}