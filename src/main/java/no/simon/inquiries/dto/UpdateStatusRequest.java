package no.simon.inquiries.dto;

import jakarta.validation.constraints.NotNull;
import no.simon.inquiries.model.Status;

public record UpdateStatusRequest(
        @NotNull(message = "Status is required")
        Status status) {
}
