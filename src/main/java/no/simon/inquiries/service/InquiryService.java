package no.simon.inquiries.service;

import no.simon.inquiries.exception.InquiryNotFoundException;
import no.simon.inquiries.model.Inquiry;
import no.simon.inquiries.model.Status;
import no.simon.inquiries.repository.InquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository repository;

    public InquiryService(InquiryRepository repository) {
        this.repository = repository;
    }

    public Inquiry create(String name, String message) {
        return repository.save(new Inquiry(name, message));
    }

    public List<Inquiry> getAll() {
        return repository.findAll();
    }

    public Inquiry getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new InquiryNotFoundException(id));
    }

    public Inquiry updateStatus(Long id, Status status) {
        Inquiry inquiry = getById(id);
        inquiry.setStatus(status);
        return repository.save(inquiry);
    }
}
