package org.souciance.integration.file;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.io.File;

/**
 * Created by ser on 2016-09-12.
 */
public class FileTransferIntegrationTest extends CamelBlueprintTestSupport {

    private boolean debugBeforeMethodCalled;
    private boolean debugAfterMethodCalled;

    @Override
    public boolean isCreateCamelContextPerClass() {
        return true;
    }

    @Override
    protected String getBlueprintDescriptor() {
        return "blueprint/filetransfer_test.xml";
    }
    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected String getBundleFilter() {
        // don't want the normal bundle to start as it causes conflict to our test and we want to stop the eventbus bundle to start
        return "(!(Bundle-SymbolicName=samples))";
    }

    @Test
    public void testFileTransfer_FileExist() throws Exception {
        context.getRouteDefinition("FileTransfer").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:FileTransfer");
            }
        });

        context.start();
        template.sendBody("direct:FileTransfer", "This is a test message");
        Thread.sleep(1000);
        File file = new File("src/test/resources/filetransfer/fileTransferred.txt");
        assertTrue(file.exists());
        context.stop();
        file.delete();
    }


    @Override
    public boolean isUseDebugger() {
        // must enable debugger
        return true;
    }

    @Override
    protected void debugBefore(Exchange exchange, org.apache.camel.Processor processor, ProcessorDefinition<?> definition, String id, String label) {
        log.debug("Before " + definition + " with body " + exchange.getIn().getBody());
        debugBeforeMethodCalled = true;
    }

    @Override
    protected void debugAfter(Exchange exchange, org.apache.camel.Processor processor, ProcessorDefinition<?> definition, String id, String label, long timeTaken) {
        log.debug("After " + definition + " with body " + exchange.getIn().getBody());
        debugAfterMethodCalled = true;
    }
}
