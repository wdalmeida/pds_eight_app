package model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class LoginModel {

    @NotNull
    private String login;

    @NotNull
    private String pwd;


}
