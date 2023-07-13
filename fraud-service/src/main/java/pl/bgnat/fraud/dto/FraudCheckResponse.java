package pl.bgnat.fraud.dto;

import lombok.Builder;

@Builder
public record FraudCheckResponse(boolean isFraudster) {
}
