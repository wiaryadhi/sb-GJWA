package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.dto.EmployeeDTO;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Employee;
import com.bcafinance.gjwaspringbootjpa.services.EmployeeService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/v1/employee")
    public ResponseEntity<Object>
    saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws Exception {
        Employee employee = modelMapper.map(employeeDTO,Employee.class);

        employeeService.saveEmployee(employee);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") long id) throws Exception {
        Employee employee = employeeService.findByIdEmployee(id);

        if(employee != null)
        {
            EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,employeeDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/employee/datas/all/0")
    public ResponseEntity<Object> findAllEmployee()throws Exception{

        int data = 0;
        List<Employee> lsEmployee = employeeService.findAllEmployee();

        if(lsEmployee.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsEmployee,null,null);
    }

    @GetMapping("/v1/employee/datas/search/{email}")
    public ResponseEntity<Object> getEmployeeByEmail(@PathVariable("email") String email)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,employeeService.findByEmailEmployee(email),null,null);
    }

    @PutMapping("/v1/employee/u")
    public ResponseEntity<Object> updateCustomerByID(@Valid @RequestBody EmployeeDTO employeeDTO)throws Exception{
        Employee employee = modelMapper.map(employeeDTO,Employee.class);

        employeeService.updateEmployeeById(employee);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @GetMapping("/v1/employee/dto/{id}")
    public ResponseEntity<Object> getEmployeeByIdDTO(@PathVariable("id") long id) throws Exception {
        Employee employee = employeeService.findByIdEmployee(id);

        if(employee != null)
        {

            modelMapper.getConfiguration().setSkipNullEnabled(true);
            TypeMap<Employee, EmployeeDTO> propertyMapper = modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);
            propertyMapper.addMappings(
                    mapper -> mapper.map(src -> src.getDivision().getName(), EmployeeDTO::setDivisionName)
            );
            //set agar object division tidak ikut semua, yang ikut hanya division name saja yang sudah tertulis diatas
            propertyMapper.addMappings(
                    mapper -> mapper.skip(EmployeeDTO::setDivision)
            );

            EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,employeeDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/employee/datas/all/dto/0")
    public ResponseEntity<Object> findAllEmployeeDTO()throws Exception{

        List<Employee> lsEmployee = employeeService.findAllEmployee();

        if(lsEmployee.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<EmployeeDTO> lsEmployeeDTO = modelMapper.map(lsEmployee, new TypeToken<List<EmployeeDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsEmployeeDTO,null,null);
    }

    @PostMapping("/v1/employee/bc")
    public ResponseEntity<Object>
    saveAllEmployee(@Valid @RequestBody List<Employee> listEmployee
    ) throws Exception {
        employeeService.saveAllEmployee(listEmployee);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("/v1/employee/dto/bc")
    public ResponseEntity<Object>
    saveAllEmployeeDTO(@Valid @RequestBody List<EmployeeDTO> listEmployeeDTO
    ) throws Exception {
        List<Employee> lsEmployee = modelMapper.map(listEmployeeDTO, new TypeToken<List<Employee>>() {}.getType());
        employeeService.saveAllEmployee(lsEmployee);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }
}
