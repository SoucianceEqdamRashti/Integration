package org.souciance.integration.audit;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;

import java.util.Date;
import java.util.EventObject;

/**
 * AuditEventNotifier is class that uses Camels EventNotifier to print out time taken to send to messages to specific endpoints
 * and for the complete exchange to complete. These are good metrics when wanting to know how fast a lookup call can be.
 *
 * */
public class AuditEventNotifier extends EventNotifierSupport {

    public void notify(EventObject event) throws Exception {
        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info(">>> Took " + sent.getTimeTaken() + " millis to send to external system : " + sent.getEndpoint());
        }

        if (event instanceof ExchangeCompletedEvent) {;
            ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
            Exchange exchange = exchangeCompletedEvent.getExchange();
            String routeId = exchange.getFromRouteId();
            Date created = ((ExchangeCompletedEvent) event).getExchange().getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
            // calculate elapsed time
            Date now = new Date();
            long elapsed = now.getTime() - created.getTime();
            log.info(">>> Took " + elapsed + " millis for the exchange on the route : " + routeId);
        }
    }

    public boolean isEnabled(EventObject event) {
        return true;
    }

    /**
     * This method  filters events not interested in and listen for route and exchange completion events
     * @throws Exception
     */
    protected void doStart() throws Exception {
        // filter out unwanted events
        setIgnoreCamelContextEvents(true);
        setIgnoreServiceEvents(true);
        setIgnoreRouteEvents(true);
        setIgnoreExchangeCreatedEvent(true);
        setIgnoreExchangeCompletedEvent(false);
        setIgnoreExchangeFailedEvents(true);
        setIgnoreExchangeRedeliveryEvents(true);
        setIgnoreExchangeSentEvents(false);
    }
}