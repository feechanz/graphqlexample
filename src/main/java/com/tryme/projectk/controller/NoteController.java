package com.tryme.projectk.controller;

import com.tryme.projectk.model.ErrorK;
import com.tryme.projectk.model.Note;
import com.tryme.projectk.repository.NoteRepository;
import com.tryme.projectk.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "GetNotes/{accountid}", method = RequestMethod.GET,produces="application/json")
    public ResponseEntity getNotes(@PathVariable("accountid") Long accountid){
        List<Note> result = noteRepository.findAllByAccount_Id(accountid);
        return new ResponseEntity<List<Note>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "GetNote/{noteid}", method = RequestMethod.GET,produces="application/json")
    public ResponseEntity getNote(@PathVariable("noteid") Long noteid){
        Note result = noteRepository.findOne(noteid);
        if(result == null)
            return new ResponseEntity<ErrorK>(new ErrorK("114","object not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<Note>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "PostNote", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity postNote(@RequestBody Note note){
        note.setId(null);
        if(note == null)
            return new ResponseEntity<ErrorK>(new ErrorK("100","entity cannot be null"),HttpStatus.BAD_REQUEST);

        if(TextUtils.isEmpty(note.getNote()) || TextUtils.isEmpty(note.getTitle()))
            return new ResponseEntity<ErrorK>(new ErrorK("114","attribute cannot be empty"),HttpStatus.BAD_REQUEST);

        Note result = null;
        try {
            result = noteRepository.save(note);
        }catch (Exception ex){
            return new ResponseEntity<ErrorK>(new ErrorK("115",ex.getMessage() + " \n\n " + ex.getStackTrace()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Note>(result,HttpStatus.CREATED);
    }
}
