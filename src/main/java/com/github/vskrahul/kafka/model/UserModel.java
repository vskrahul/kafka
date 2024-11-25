package com.github.vskrahul.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class UserModel {
    String userId;
    String firstName;
    String lastName;
}
