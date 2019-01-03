package wolox.training.jsons.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PasswordUpdate {

    @NotNull
    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }
}
