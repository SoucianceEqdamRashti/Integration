package org.souciance.iib;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Shutdown {
  private final static Logger log = LoggerFactory.getLogger(Shutdown.class);

  public void process(Exchange exchange) throws Exception {
    final CamelContext context = exchange.getContext();
    Thread shutdownThread = new Thread(() -> {
      Thread.currentThread().setName("ShutDown");
      try {
        context.stop();
        System.exit(0);
      } catch (Exception ex) {
        log.error("Failed to shutdown SpringBootIIBAdminApp", ex);
      }
    });
    shutdownThread.start();
  }
}
