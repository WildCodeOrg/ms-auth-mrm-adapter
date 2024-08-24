package org.skyhigh.msauthmrmadapter.validation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class MultipleFlkException extends RuntimeException {
    private List<FlkException> flkExceptions;
}
