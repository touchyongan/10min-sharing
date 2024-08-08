package io.kmaker.sharing.specification.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentProjectionData {
    private Long id;
    private String name;
    private int age;
}
