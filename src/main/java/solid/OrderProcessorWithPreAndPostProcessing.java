package solid;

// Open Closed Principle
// Extending the OrderProcessor class with pre - and post - processing
public class OrderProcessorWithPreAndPostProcessing extends OrderProcessor {

    public OrderProcessorWithPreAndPostProcessing(MailSender mailSender, OrderRepository repository) {
        super(mailSender, repository);
    }

    private void beforeProcessing() {
    }

    private void afterProcessing() {
    }

    @Override
    public void process(Order order) {
        beforeProcessing();
        super.process(order);
        afterProcessing();
    }
}
