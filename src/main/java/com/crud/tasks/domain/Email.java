package com.crud.tasks.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Email {
    @NotNull
    private String receiverEmail;
    @NotNull
    private String subject;
    @NotNull
    private String message;
    private String toCC;
}
