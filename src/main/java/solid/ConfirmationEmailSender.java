package solid;

//Single Responsibility Principle
// Выносим логику по отправке подтверждения заказа в отдельный класс
public class ConfirmationEmailSender implements MailSender {

    @Override
    public void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();

        // Шлем письмо клиенту
    }
}
