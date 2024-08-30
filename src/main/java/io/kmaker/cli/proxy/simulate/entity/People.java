package io.kmaker.cli.proxy.simulate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class People {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
}
