package hu.bnpi.dhte.inventory.responsible.mapper;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDto;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDto;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResponsibleMapperIT {

    private ResponsibleMapper mapper = Mappers.getMapper(ResponsibleMapper.class);

    @Test
    void toEmployeeDtoTest() {
        Employee employee = new Employee(1L, "John Doe", new ArrayList<InventoryItem>(), "johndoe@mail.com");
        EmployeeDto result = mapper.toEmployeeDto(employee);
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void toDepartmentDtoTest() {
        Employee employee = new Employee(1L, "John Doe", new ArrayList<InventoryItem>(), "johndoe@mail.com");
        Department department = new Department(2L, "Finance Department", new ArrayList<InventoryItem>(), employee);
        DepartmentDto result =  mapper.toDepartmentDto(department);
        assertEquals("John Doe", result.getLeaderName());
    }
}