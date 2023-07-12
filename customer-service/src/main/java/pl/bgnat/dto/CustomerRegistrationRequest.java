package pl.bgnat.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerRegistrationRequest(
	String firstName,
	String lastName,
	String email,
	String password,
	LocalDate dateOfBirth
) {
}
