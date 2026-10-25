package no.simon.inquiries.repository;

import no.simon.inquiries.model.Inquiry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InquiryRepository {

    private final Map<Long, Inquiry> store = new ConcurrentHashMap<>();

    private final AtomicLong nextId = new AtomicLong();

    public Inquiry save(Inquiry inquiry) {
        if (inquiry.getId() == null) {
            inquiry.setId(nextId.incrementAndGet());
        }
        store.put(inquiry.getId(), inquiry);
        return inquiry;
    }

    public List<Inquiry> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Inquiry> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
