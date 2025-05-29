package main.java.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currency {
    private String code;
    private String name;

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
