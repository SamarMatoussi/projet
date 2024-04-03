package tn.Backend.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import tn.Backend.entites.Client;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Client user;
    private String applicationUrl;

    public RegistrationCompleteEvent(Client user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
