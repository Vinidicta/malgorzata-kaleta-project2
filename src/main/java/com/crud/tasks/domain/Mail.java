package com.crud.tasks.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Mail {
    private String mailTo;

    private String toCc;

    private String subject;

    private String message;
}
