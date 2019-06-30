package sofa.consumer;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.testng.Assert;
import sofa.publisher.ExampleService;
import sofa.publisher.pojo.AnotherDummyObject;
import sofa.publisher.pojo.DummyObject;

import static org.mockito.Mockito.*;

public class ExampleConsumerTest extends junit.framework.TestCase {

    private ExampleConsumer exampleConsumer;

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        exampleConsumer = new ExampleConsumer();
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
    }

    @org.testng.annotations.Test
    public void testConsumeDummyMethod() {

        exampleConsumer.consumeDummyMethod("Hello");
    }

    @org.testng.annotations.Test
    public void testConsumeAnotherMethod() {
        DummyObject input = new DummyObject();
        input.setParam1("awesome");
        input.setParam2("awesome");

        AnotherDummyObject output = exampleConsumer.consumeAnotherMethod(input);

        Assert.assertNotNull(output);
    }

    @org.testng.annotations.Test
    public void testHandleUnfinishedMethod() {
        ExampleService exampleService = mock(ExampleService.class);
        exampleConsumer.setExampleService(exampleService);
        when(exampleService.notYetImplemented(any(DummyObject.class))).thenReturn(new AnotherDummyObject());

        DummyObject input = new DummyObject();
        input.setParam1("awesome");
        input.setParam2("awesome");

        AnotherDummyObject output = exampleConsumer.handleUnfinishedMethod(input);

        Assert.assertNotNull(output);
    }
}