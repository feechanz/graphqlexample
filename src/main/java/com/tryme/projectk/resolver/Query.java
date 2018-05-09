package com.tryme.projectk.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.tryme.projectk.model.Account;
import com.tryme.projectk.model.Note;
import com.tryme.projectk.repository.AccountRepository;
import com.tryme.projectk.repository.NoteRepository;

import java.util.List;

public class Query implements GraphQLQueryResolver {
    private NoteRepository noteRepository;
    private AccountRepository accountRepository;

    public Query(AccountRepository accountRepository, NoteRepository noteRepository) {
        this.accountRepository = accountRepository;
        this.noteRepository = noteRepository;
    }

    public Note findOneNote(Long noteid){
        return noteRepository.findOne(noteid);
    }

    public Account findOneAccount(Long accountid){
        return accountRepository.findOne(accountid);
    }

    public List<Note> findAllNotes() {
        return Lists.newArrayList(noteRepository.findAll());
    }

    public List<Account> findAllAccounts() {
        return Lists.newArrayList(accountRepository.findAll());
    }

    public long countNotes() {
        return noteRepository.count();
    }
    public long countAccounts() {
        return accountRepository.count();
    }
}
