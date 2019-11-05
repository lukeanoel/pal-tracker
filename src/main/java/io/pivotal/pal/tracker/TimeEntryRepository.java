package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TimeEntryRepository extends Repository {

    @Autowired
    public TimeEntry create(TimeEntry any);

    @Autowired
    TimeEntry find(long timeEntryId);

    @Autowired
    List<TimeEntry> list();

    @Autowired
    List<TimeEntry> findAll();

    @Autowired
    TimeEntry update(long id, TimeEntry any);

    @Autowired
    void delete(long timeEntryId);
}
