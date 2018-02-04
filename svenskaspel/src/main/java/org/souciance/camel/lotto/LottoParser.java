package org.souciance.camel.lotto;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.camel.Exchange;

import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {

    public static void getLottoDrawNumbers(Exchange exchange) {
        String body = exchange.getIn().getBody().toString();
        System.out.println("hej"+body);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(body);
        int drawNumberSaturday = JsonPath.read(document, "$.results[0].drawNumber");
        exchange.getIn().setHeader("LOTTO_SATURDAY_URI", "https4://api.www.svenskaspel.se/external/draw/lottosaturday/draws/".concat(String.valueOf(drawNumberSaturday)).concat("/result?accesskey=81d8f066-de28-4795-930d-78a1054086e5"));
        int drawNumberWednesday = JsonPath.read(document, "$.results[1].drawNumber");
        exchange.getIn().setHeader("LOTTO_WEDNESDAY_URI", "https4://api.www.svenskaspel.se/external/draw/lottowednesday/draws/".concat(String.valueOf(drawNumberWednesday)).concat("/result?accesskey=81d8f066-de28-4795-930d-78a1054086e5"));
        exchange.getIn().setBody(null);
    }

    public static void getLottoNumbers(Exchange exchange) {
        String body = exchange.getIn().getBody().toString();
        String day = exchange.getIn().getHeader("LOTTO_DAY").toString();
        String lottoSaturdayResults = getLotto1Numbers(body, day);
        String lottoWednesdayResults = getLotto2Numbers(body, day);
        exchange.getIn().setBody(lottoSaturdayResults.concat(". ").concat(lottoWednesdayResults));
    }

    private static String getLotto1Numbers(String body, String day) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(body);
        List<List<Integer>> resultsLottoSaturday = JsonPath.read(document, "$.result.drawResult[0]..numbers");
        List<String> mainNumbers = resultsLottoSaturday.get(0).stream().map(Object::toString).collect(Collectors.toList());
        List<String> extraNumbers = resultsLottoSaturday.get(1).stream().map(Object::toString).collect(Collectors.toList());
        String prettyPrintMainNumbers = String.join(" ", mainNumbers);
        String prettyPrintExtraNumbers = String.join(" ", extraNumbers);
        return "Rätt rad för lotto 1 för " + day + " är " + prettyPrintMainNumbers + "och extra siffrorna är " + prettyPrintExtraNumbers;
    }

    private static String getLotto2Numbers(String body, String day) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(body);
        List<List<Integer>> resultsLottoSaturday = JsonPath.read(document, "$.result.drawResult[1]..numbers");
        List<String> mainNumbers = resultsLottoSaturday.get(0).stream().map(Object::toString).collect(Collectors.toList());
        List<String> extraNumbers = resultsLottoSaturday.get(1).stream().map(Object::toString).collect(Collectors.toList());
        String prettyPrintMainNumbers = String.join(" ", mainNumbers);
        String prettyPrintExtraNumbers = String.join(" ", extraNumbers);
        return "Rätt rad för lotto 2 för " + day + " är " + prettyPrintMainNumbers + "och extra siffrorna är " + prettyPrintExtraNumbers;
    }
}
