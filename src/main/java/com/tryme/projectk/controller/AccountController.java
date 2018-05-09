package com.tryme.projectk.controller;

import com.tryme.projectk.model.Account;
import com.tryme.projectk.model.ErrorK;
import com.tryme.projectk.repository.AccountRepository;
import com.tryme.projectk.utils.TextUtils;
import javassist.tools.web.BadHttpRequest;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "GetAccounts", method = RequestMethod.GET,produces="application/json")
    public ResponseEntity getAccounts(){
        Iterable<Account> result = accountRepository.findAll();
        return new ResponseEntity<Iterable<Account>>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "PostAccount", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity postAccount(@RequestBody Account account){
        account.setId(null);
        if(account == null)
            return new ResponseEntity<ErrorK>(new ErrorK("100","entity cannot be null"),HttpStatus.BAD_REQUEST);

        if(TextUtils.isEmpty(account.getFullname())|| TextUtils.isEmpty(account.getUsername()) || TextUtils.isEmpty(account.getPassword()))
            return new ResponseEntity<ErrorK>(new ErrorK("114","attribute cannot be empty"),HttpStatus.BAD_REQUEST);

        Account result = null;
        try {
            Account tmp = accountRepository.findByUsername(account.getUsername());
            if (tmp != null)
                return new ResponseEntity<ErrorK>(new ErrorK("111","username already exists"),HttpStatus.CONFLICT);
            result = accountRepository.save(account);
        }catch (Exception ex){
            return new ResponseEntity<ErrorK>(new ErrorK("115",ex.getMessage() + " \n\n " + ex.getStackTrace()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Account>(result,HttpStatus.CREATED);
    }
}
