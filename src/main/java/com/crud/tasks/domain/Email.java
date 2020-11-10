package com.crud.tasks.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class Email {
    private String receiverEmail;
    private String subject;
    private String message;
    private String toCC;
}
