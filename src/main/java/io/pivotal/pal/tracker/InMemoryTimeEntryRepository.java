package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> entries = new HashMap<Long, TimeEntry>();
    private long count = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(count);
        entries.put(count, timeEntry);
        count++;

        return entries.get(count-1L);
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return entries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> findAll() {
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        entries.remove(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(entries.values());
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (entries.get(id) == null)
            return null;

        entries.put(id, timeEntry);
        entries.get(id).setId(id);

        return entries.get(id);
    }
}
