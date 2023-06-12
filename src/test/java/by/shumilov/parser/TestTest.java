package by.shumilov.parser;


import by.shumilov.TestDataBuilder;
import by.shumilov.bean.Employee;
import by.shumilov.service.EmployeeService;
import by.shumilov.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestTest {

    @Test
    void dwefsrvdf(){
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> all = employeeService.findAll();
        System.out.println(all);
    }

}
