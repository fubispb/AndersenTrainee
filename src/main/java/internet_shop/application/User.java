package internet_shop.application;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1590813466145354671L;
    private final long id;
    private final String name;


    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
