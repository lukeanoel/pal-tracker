package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    @Autowired
    TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry entryCreated = repository.create(timeEntry);

        return new ResponseEntity<TimeEntry>(entryCreated, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        if(repository.find(id) == null)
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);

        TimeEntry entry = repository.find(id);

        return new ResponseEntity<TimeEntry>(entry, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        //return new ResponseEntity<List<TimeEntry>>(repository.list(), HttpStatus.OK);
        List<TimeEntry> entries = new ArrayList<TimeEntry>();

        HttpStatus stat;

        if (repository.findAll() == null)
            return new ResponseEntity(entries, HttpStatus.OK);

        for (TimeEntry entry : repository.findAll()) {
            entries.add(entry);
        }

        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        if (repository.find(id) == null) {
            repository.create(timeEntry);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        repository.update(id, timeEntry);

        return new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        if (repository.find(id) == null)
           return new ResponseEntity(HttpStatus.NOT_FOUND);

        repository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
