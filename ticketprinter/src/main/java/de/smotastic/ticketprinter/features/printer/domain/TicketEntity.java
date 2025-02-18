package de.smotastic.ticketprinter.features.printer.domain;

import lombok.Builder;

@Builder
public record TicketEntity(String id, String title, String description, String assignee) {
}
