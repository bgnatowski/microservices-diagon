package pl.bgnat.customer.dto;

import lombok.Builder;

@Builder
public record FraudCheckResponse(boolean isFraudster) {
}
