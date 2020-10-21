package solid;

// Single Responsibility Principle
// Taking out the logic for sending order confirmation to a separate class
public class ConfirmationEmailSender implements MailSender {

    @Override
    public void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
    }
}
