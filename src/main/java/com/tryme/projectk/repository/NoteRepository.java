package com.tryme.projectk.repository;

import com.tryme.projectk.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAllByAccount_Id(Long account);
}
