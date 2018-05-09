package com.tryme.projectk.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tryme.projectk.model.Account;
import com.tryme.projectk.model.Note;
import com.tryme.projectk.repository.AccountRepository;
import com.tryme.projectk.repository.NoteRepository;
import com.tryme.projectk.utils.NoteNotFoundException;

public class Mutation implements GraphQLMutationResolver {
    private AccountRepository accountRepository;
    private NoteRepository noteRepository;

    public Mutation(AccountRepository accountRepository, NoteRepository noteRepository) {
        this.accountRepository = accountRepository;
        this.noteRepository = noteRepository;
    }

    public Account newAccount(String username, String password, String fullname) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setFullname(fullname);
        Account result = accountRepository.save(account);
        return result;
    }

    public Note newNote(String title, String note,  Long accountid) {
        Note noteObj = new Note();
        noteObj.setTitle(title);
        noteObj.setNote(note);
        noteObj.setAccountid(accountid);

        Note result = noteRepository.save(noteObj);
        return noteObj;
    }

    public boolean deleteNote(Long id) {
        noteRepository.delete(id);
        return true;
    }

    public Note updateNoteNote(String note, Long id) {
        Note noteObj = noteRepository.findOne(id);
        if(noteObj == null) {
            throw new NoteNotFoundException("The note to be updated was not found", id);
        }
        noteObj.setNote(note);

        noteRepository.save(noteObj);
        return noteObj;
    }
}
