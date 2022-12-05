package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.DataUsers;
import com.bcafinance.gjwaspringbootjpa.services.DataUserService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class DataUserController {
    @Getter
    private DataUserService dataUserService;


    public DataUserController() {
    }

    @Autowired
    public DataUserController(DataUserService userService) {
        this.dataUserService = userService;
    }

    @PostMapping("/v1/users")
    public ResponseEntity<Object>
    saveUser(@RequestBody DataUsers dataUser) throws Exception {
        if(dataUser==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);

        dataUserService.saveUsers(dataUser);

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }

//    @GetMapping("/v1/users/{id}")
//    public ResponseEntity<Object> getUsersById(@PathVariable("id") long id) throws Exception {
//        DataUsers users = dataUserService.findByIdUsers(id);
//
//        if(users != null)
//        {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,users,null,null);
//        }
//        else
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/v1/users/datas/all")
//    public ResponseEntity<Object> findAllUsers()throws Exception{
//        List<DataUsers> lsUsers = dataUserService.findAllUsers();
//
//        if(lsUsers.size()==0)
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsUsers,null,null);
//    }
//
//    @GetMapping("/v1/users/fullname/{name}")
//    public ResponseEntity<Object> getUserContaining(@PathVariable("name") String name)throws Exception{
//        List<DataUsers> lsUsers = dataUserService.findFullNameContaining(name);
//
//        if(lsUsers.size()==0){
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_FAILED_AUTHENTICATION);
//        }else {
//            return new ResponseHandler().
//                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,dataUserService.findFullNameContaining(name),null,null);
//        }
//    }

    @GetMapping("/v1/a/{token}")
    public ResponseEntity<Object> getUserTokenContaining(@PathVariable("token") String token)throws Exception{
        List<DataUsers> lsUsers = dataUserService.findTokenLike(token);

        if(lsUsers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_FAILED_AUTHENTICATION);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_AUTHENTICATION,HttpStatus.CREATED,null,null,null);
        }
    }
}