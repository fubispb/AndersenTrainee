package internet_shop.application;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHandlerTest {

    CommandHandler commands;

    @BeforeEach
    void setUp() {
        commands = new CommandHandler("Mike");
    }

    @Test
    void userCommandHandler() {
        assertClientName("Mike");
        commands.userCommandHandler("/add chair 6");
        assertBucketSize(1);
        assertAmount(300);
        commands.userCommandHandler("/add coffee 1");
        assertBucketSize(2);
        assertAmount(320);
        commands.userCommandHandler("/delete chair");
        assertBucketSize(1);
        assertAmount(20);
        commands.userCommandHandler("/add coffee 1");
        assertBucketSize(1);
        assertAmount(40);
        commands.userCommandHandler("/clear");
        assertBucketSize(0);
        assertAmount(0);
    }

    private void assertAmount(int amount) {
        Assert.assertEquals(amount, commands.getCurrentBucketAmount());
    }

    private void assertClientName(String name) {
        Assert.assertEquals(commands.getClientName(), "Mike");
    }

    public void assertBucketSize(int size) {
        Assert.assertEquals(size, commands.getBucketSize());
    }

}