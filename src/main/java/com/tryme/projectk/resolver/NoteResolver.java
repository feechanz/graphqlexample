package com.tryme.projectk.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tryme.projectk.model.Account;
import com.tryme.projectk.model.Note;
import com.tryme.projectk.repository.AccountRepository;
import com.tryme.projectk.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteResolver implements GraphQLResolver<Note> {
    private AccountRepository accountRepository;

    public NoteResolver(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Note note){
        return accountRepository.findOne(note.getAccount().getId());
    }
}
