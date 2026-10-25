package no.simon.inquiries.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateInquiryRequest(
        // @NotBlank: not null, not "", not "  " (only spaces)
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message cannot exceed 2000 characters")
        String message) {

}
