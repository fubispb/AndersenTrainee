package solid;


// Interface Segregation Principle
// Separate 1 common interface into 2 send and confirm interfaces
public interface MailSender {

    void sendConfirmationEmail(Order order);

}
