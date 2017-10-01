package com.souciance.org;

import com.souciance.org.routes.*;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new MQScriptHeader());
        main.addRouteBuilder(new MQQueueLocal());
        main.addRouteBuilder(new MQQueueAlias());
        main.addRouteBuilder(new MQQueueRemote());
        main.addRouteBuilder(new MQRoutes());
        main.run(args);
    }

}

