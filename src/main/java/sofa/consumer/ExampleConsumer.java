package sofa.consumer;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import sofa.publisher.ExampleService;
import sofa.publisher.pojo.AnotherDummyObject;
import sofa.publisher.pojo.DummyObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleConsumer {

    private static final Logger LOGGER = Logger.getLogger(ExampleConsumer.class.getName());

    private ExampleService exampleService;

    public ExampleConsumer() {
        ConsumerConfig<ExampleService> consumerConfig = new ConsumerConfig<ExampleService>()
            .setInterfaceId(ExampleService.class.getName()) // Specify the interface
            .setProtocol("bolt") // Specify the protocol.setDirectUrl
            .setDirectUrl("bolt://127.0.0.1:12200");
        exampleService = consumerConfig.refer();
    }

    public ExampleService getExampleService() {
        return exampleService;
    }

    public void setExampleService(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    public void consumeDummyMethod(String input) {
        LOGGER.log(Level.INFO, "The input I sent: " + input);
        String output = exampleService.dummyMethod(input);
        LOGGER.log(Level.INFO, "The input I got: " + output);
    }

    public AnotherDummyObject consumeAnotherMethod(DummyObject input) {
        LOGGER.log(Level.INFO, "The input I sent: " + input);
        AnotherDummyObject output = exampleService.anotherDummyMethod(input);
        LOGGER.log(Level.INFO, "The input I got: " + output);
        return output;
    }

    public AnotherDummyObject handleUnfinishedMethod(DummyObject input) {
        LOGGER.log(Level.INFO, "The input I sent: " + input);
        AnotherDummyObject output = exampleService.notYetImplemented(input);
        LOGGER.log(Level.INFO, "The input I got: " + output);
        return output;
    }
}