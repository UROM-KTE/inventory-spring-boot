package hu.bnpi.dhte.inventory.responsible.controller;

import hu.bnpi.dhte.inventory.responsible.dtos.*;
import hu.bnpi.dhte.inventory.responsible.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/responsible")
public class ResponsibleController {

    private ResponsibleService service;

    @GetMapping
    public List<ResponsibleDetails> findResponsible(@RequestParam Optional<String> name) {
        return service.findResponsible(name);
    }

    @GetMapping(value = "/employees")
    public List<EmployeeDetails> findEmployees(@RequestParam Optional<String> name) {
        return service.findEmployees(name);
    }

    @GetMapping(value = "/departments")
    public List<DepartmentDetails> findDepartments(@RequestParam Optional<String> name) {
        return service.findDepartments(name);
    }

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDetails saveEmployee(@Valid @RequestBody SaveEmployeeCommand command) {
        return service.saveEmployee(command);
    }

    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDetails saveDepartment(@Valid @RequestBody SaveDepartmentCommand command) {
        return service.saveDepartment(command);
    }

    @PutMapping(value = "/employees")
    public EmployeeDetails updateEmployee(@Valid @RequestBody UpdateEmployeeCommand command) {
        return service.updateEmployee(command);
    }

    @PutMapping(value = "/departments")
    public DepartmentDetails updateDepartment(@Valid @RequestBody UpdateDepartmentCommand command) {
        return service.updateDepartment(command);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeResponsible(@PathVariable("id") long id) {
        service.deleteResponsible(id);
    }
}
