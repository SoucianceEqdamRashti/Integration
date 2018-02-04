package org.souciance.camel.lotto;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class LottoRoute extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        onException(Exception.class).to("direct:ErrorHandler");

        from("timer:test?repeatCount=1")
                .process(exchange -> {
                    exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
                    exchange.getIn().setHeader("Accept", "application/json");
                })
                .to("https4://api.www.svenskaspel.se/external/draw/lotto/draws/result?accesskey=81d8f066-de28-4795-930d-78a1054086e5")
                .convertBodyTo(String.class)
                .log("${body} and ${headers}")
                .bean(LottoParser.class, "getLottoDrawNumbers")
                .multicast().stopOnException().to("direct:LottoSaturday", "direct:LottoWednesday")
               .end();

        from("direct:LottoSaturday")
                .toD("${headers.LOTTO_SATURDAY_URI}")
                .convertBodyTo(String.class)
                .log("${body} and ${headers}")
                .setHeader("LOTTO_DAY", constant("lördag"))
                .bean(LottoParser.class, "getLottoNumbers")
                .setHeader("CamelTelegramChatId", constant("135342235"))
                .to("telegram:bots/483417546:AAEhhkalmJNRX8dVoqaPccTsS5lfn-CwdGk");

        from("direct:LottoWednesday")
                .toD("${headers.LOTTO_WEDNESDAY_URI}")
                .convertBodyTo(String.class)
                .log("${body} and ${headers}")
                .setHeader("LOTTO_DAY", constant("onsdag"))
                .bean(LottoParser.class, "getLottoNumbers")
                .setHeader("CamelTelegramChatId", constant("135342235"))
                .to("telegram:bots/483417546:AAEhhkalmJNRX8dVoqaPccTsS5lfn-CwdGk");

        from("direct:ErrorHandler")
                .setHeader("CamelTelegramChatId", constant("135342235"))
                .setBody(constant("Någonting gick fel med att hämta siffrorna. Prova igen!"))
                .to("telegram:bots/483417546:AAEhhkalmJNRX8dVoqaPccTsS5lfn-CwdGk");
    }
}
