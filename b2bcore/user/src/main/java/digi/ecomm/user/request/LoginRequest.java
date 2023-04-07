package digi.ecomm.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String uid;

    private String password;
}
