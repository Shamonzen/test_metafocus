package no.simon.inquiries.exception;

public class InquiryNotFoundException extends RuntimeException {

    public InquiryNotFoundException(Long id) {
        super("Inquiry with id " + id + " not found");
    }
}
