package solid;

//Open Closed Principle
//Расширяем класс OrderProcessor пре- и пост- обработкой
public class OrderProcessorWithPreAndPostProcessing extends OrderProcessor {

    public OrderProcessorWithPreAndPostProcessing(MailSender mailSender, OrderRepository repository) {
        super(mailSender, repository);
    }

    @Override
    public void process(Order order) {
        beforeProcessing();
        super.process(order);
        afterProcessing();
    }

    private void beforeProcessing() {
        // Осуществим некоторые действия перед обработкой заказа
    }

    private void afterProcessing() {
        // Осуществим некоторые действия после обработки заказа
    }
}
