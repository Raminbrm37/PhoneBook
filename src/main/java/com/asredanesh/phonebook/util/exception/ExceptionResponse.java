package com.asredanesh.phonebook.util.exception;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;
}
