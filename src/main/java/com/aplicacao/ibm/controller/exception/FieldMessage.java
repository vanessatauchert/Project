package com.aplicacao.ibm.controller.exception;

import com.aplicacao.ibm.ExcludeFromJacocoGeneratedReport;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fildName;
    private String message;
}
