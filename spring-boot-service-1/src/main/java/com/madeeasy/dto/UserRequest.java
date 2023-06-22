package com.madeeasy.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Component
public class UserRequest {
    private String name;
    private String password;
}