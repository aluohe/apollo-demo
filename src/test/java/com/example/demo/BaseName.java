package com.example.demo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class BaseName {

    String name;

    String code;

}