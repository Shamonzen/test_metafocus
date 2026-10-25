package no.simon.inquiries.controller;

import jakarta.validation.Valid;
import no.simon.inquiries.dto.CreateInquiryRequest;
import no.simon.inquiries.dto.UpdateStatusRequest;
import no.simon.inquiries.model.Inquiry;
import no.simon.inquiries.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService service;

    public InquiryController(InquiryService service) {
        this.service = service;
    }

    //POST /api/inquiries
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inquiry create(@Valid @RequestBody CreateInquiryRequest request) {
        return service.create(request.name(), request.message());
    }

    // GET /api/inquiries
    @GetMapping
    public List<Inquiry> getAll() {
        return service.getAll();
    }

    // GET /api/inquiries/5
    @GetMapping("/{id}")
    public Inquiry getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // PATCH /api/inquiries/5/status
    @PatchMapping("/{id}/status")
    public Inquiry updateStatus(@PathVariable Long id,
                                @Valid @RequestBody UpdateStatusRequest request) {
        return service.updateStatus(id, request.status());
    }
}
