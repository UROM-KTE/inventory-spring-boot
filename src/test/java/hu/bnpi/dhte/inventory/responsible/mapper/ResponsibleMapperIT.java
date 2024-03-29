package hu.bnpi.dhte.inventory.responsible.mapper;

import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDetails;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDetails;
import hu.bnpi.dhte.inventory.responsible.dtos.ResponsibleDetails;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponsibleMapperIT {

    private ResponsibleMapper mapper = Mappers.getMapper(ResponsibleMapper.class);

    @Test
    void toEmployeeDtoTest() {
        Employee employee = new Employee(1L, "001","John Doe", new ArrayList<>(), "johndoe@mail.com");
        EmployeeDetails result = mapper.toEmployeeDetails(employee);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void toDepartmentDtoTest() {
        Employee employee = new Employee(1L, "001", "John Doe", new ArrayList<>(), "johndoe@mail.com");
        Department department = new Department(2L, "002", "Finance Department", new ArrayList<>(), employee);
        DepartmentDetails result =  mapper.toDepartmentDetails(department);
        assertEquals("John Doe", result.getLeaderName());
    }

    @Test
    void employeeToResponsibleDtoTest() {
        Employee employee = new Employee(1L, "001", "John Doe", new ArrayList<>(), "johndoe@mail.com");
        ResponsibleDetails result = mapper.toResponsibleDetails(employee);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void departmentToResponsibleDtoTest() {
        Employee employee = new Employee(1L, "001", "John Doe", new ArrayList<>(), "johndoe@mail.com");
        Department department = new Department(2L, "002", "Finance Department", new ArrayList<>(), employee);
        ResponsibleDetails result = mapper.toResponsibleDetails(department);
        assertEquals("Finance Department", result.getName());
    }
}