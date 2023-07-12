package pl.bgnat.dto;

import lombok.Builder;

@Builder
public record FraudCheckResponse(boolean isFraudster) {
}
