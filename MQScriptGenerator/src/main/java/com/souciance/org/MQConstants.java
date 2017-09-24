package com.souciance.org;

/**
 * Created by moeed on 2017-09-19.
 */
public class MQConstants {
    public enum MQQueueAliasAttributes {
        NAME("NAME"),
        ALTDATE("ALTDATE"),
        ALTTIME("ALTTIME"),
        TARGET("TARGET"),
        CLUSNL("CLUSNL"),
        CLUSTER("CLUSTER"),
        CLWLPRTY("CLWLPRTY"),
        CLWLRANK("CLWLRANK"),
        CUSTOM("CUSTOM"),
        DEFBIND("DEFBIND"),
        DEFPRTY("DEFPRTY"),
        DEFPSIST("DEFPSIST"),
        DEFPRESP("DEFPRESP"),
        DEFREADA("DEFREADA"),
        DESCR("DESCR"),
        GET("GET"),
        PUT("PUT"),
        PROPCTL("PROPCTL"),
        SCOPE("SCOPE"),
        TARGTYPE("TARGTYPE");
        private final String attribute;
        MQQueueAliasAttributes(String attribute) {
            this.attribute = attribute;
        }
        public String getAttribute() {
            return attribute;
        }
    }
    public enum MQQueueLocalAttributes {
        NAME("NAME"),
        ALTDATE("ALTDATE"),
        ALTTIME("ALTTIME"),
        BOQNAME("BOQNAME"),
        BOTHRESH("BOTHRESH"),
        CLUSNL("CLUSNL"),
        CLUSTER("CLUSTER"),
        CLCHNAME("CLCHNAME"),
        CLWLPRTY("CLWLPRTY"),
        CLWLRANK("CLWLRANK"),
        CLWLUSEQ("CLWLUSEQ"),
        CRDATE("CRDATE"),
        CRTIME("CRTIME"),
        CURDEPTH("CURDEPTH"),
        CUSTOM("CUSTOM"),
        DEFBIND("DEFBIND"),
        DEFPRTY("DEFPRTY"),
        DEFPSIST("DEFPSIST"),
        DEFPRESP("DEFPRESP"),
        DEFREADA("DEFREADA"),
        DEFSOPT("DEFSOPT"),
        DEFTYPE("DEFTYPE"),
        DESCR("DESCR"),
        DISTL("DISTL"),
        GET("GET"),
        INITQ("INITQ"),
        IPPROCS("IPPROCS"),
        MAXDEPTH("MAXDEPTH"),
        MAXMSGL("MAXMSGL"),
        MONQ("MONQ"),
        MSGDLVSQ("MSGDLVSQ"),
        NPMCLASS("NPMCLASS"),
        OPPROCS("OPPROCS"),
        PROCESS("PROCESS"),
        PUT("PUT"),
        PROPCTL("PROPCTL"),
        QDEPTHHI("QDEPTHHI"),
        QDEPTHLO("QDEPTHLO"),
        QDPHIEV("QDPHIEV"),
        QDPLOEV("QDPLOEV"),
        QDPMAXEV("QDPMAXEV"),
        QSVCIEV("QSVCIEV"),
        QSVCINT("QSVCINT"),
        RETINTVL("RETINTVL"),
        SCOPE("SCOPE"),
        STATQ("STATQ"),
        TRIGDATA("TRIGDATA"),
        TRIGDPTH("TRIGDPTH"),
        TRIGMPRI("TRIGMPRI"),
        TRIGTYPE("TRIGTYPE"),
        USAGE("USAGE");

        private final String identifier;

        MQQueueLocalAttributes(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }
    }

    public enum MQQueueRemoteAttributes {
        ALTDATE("ALTDATE"),
        ALTTIME("ALTTIME"),
        CLUSNL("CLUSNL"),
        CLUSTER("CLUSTER"),
        CLCHNAME("CLCHNAME"),
        CLWLPRTY("CLWLPRTY"),
        CLWLRANK("CLWLRANK"),
        CUSTOM("CUSTOM"),
        DEFBIND("DEFBIND"),
        DEFPRTY("DEFPRTY"),
        DEFPSIST("DEFPSIST"),
        DEFPRESP("DEFPRESP"),
        DESCR("DESCR"),
        PUT("PUT"),
        RQMNAME("RQMNAME"),
        RNAME("RNAME"),
        SCOPE("SCOPE"),
        XMITQ("XMITQ");
        private final String identifier;
        MQQueueRemoteAttributes(String identifier) {
            this.identifier = identifier;
        }
        public String getIdentifier() {
            return identifier;
        }
    }

    public enum MQTopicAttributes {
        PATRON_STATUS_REQUEST("23"),
        CHECKOUT_REQUEST("11"),
        CHECKIN_REQUEST("09"),
        BLOCK_PATRON_REQUEST("01"),
        SC_STATUS_REQUEST("99"),
        ACS_RESEND_REQUEST("97"),
        LOGIN_REQUEST("93"),
        PATRON_INFORMATION_REQUEST("63"),
        END_PATRON_SESSION_REQUEST("35"),
        FEE_PAID_REQUEST("37"),
        ITEM_INFORMATION_REQUEST("17"),
        ITEM_STATUS_UPDATE_REQUEST("19"),
        PATRON_ENABLE_REQUEST("25"),
        HOLD_REQUEST("15"),
        RENEW_REQUEST("29"),
        RENEW_ALL_REQUEST("65"),
        PATRON_STATUS_RESPONSE("24"),
        CHECKOUT_RESPONSE("12"),
        CHECKIN_RESPONSE("10"),
        SC_STATUS_RESPONSE("98"),
        ACS_RESEND_RESPONSE("96"),
        LOGIN_RESPONSE("94"),
        PATRON_INFORMATION_RESPONSE("64"),
        END_PATRON_SESSION_RESPONSE("36"),
        FEE_PAID_RESPONSE("38"),
        ITEM_INFORMATION_RESPONSE("18"),
        ITEM_STATUS_UPDATE_RESPONSE("20"),
        PATRON_ENABLE_RESPONSE("26"),
        HOLD_RESPONSE("16"),
        RENEW_RESPONSE("30"),
        RENEW_ALL_RESPONSE("66");

        private final String identifier;

        MQTopicAttributes(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }
    }

    public enum MQSubscriptionAttributes {
        PATRON_STATUS_REQUEST("23"),
        CHECKOUT_REQUEST("11"),
        CHECKIN_REQUEST("09"),
        BLOCK_PATRON_REQUEST("01"),
        SC_STATUS_REQUEST("99"),
        ACS_RESEND_REQUEST("97"),
        LOGIN_REQUEST("93"),
        PATRON_INFORMATION_REQUEST("63"),
        END_PATRON_SESSION_REQUEST("35"),
        FEE_PAID_REQUEST("37"),
        ITEM_INFORMATION_REQUEST("17"),
        ITEM_STATUS_UPDATE_REQUEST("19"),
        PATRON_ENABLE_REQUEST("25"),
        HOLD_REQUEST("15"),
        RENEW_REQUEST("29"),
        RENEW_ALL_REQUEST("65"),
        PATRON_STATUS_RESPONSE("24"),
        CHECKOUT_RESPONSE("12"),
        CHECKIN_RESPONSE("10"),
        SC_STATUS_RESPONSE("98"),
        ACS_RESEND_RESPONSE("96"),
        LOGIN_RESPONSE("94"),
        PATRON_INFORMATION_RESPONSE("64"),
        END_PATRON_SESSION_RESPONSE("36"),
        FEE_PAID_RESPONSE("38"),
        ITEM_INFORMATION_RESPONSE("18"),
        ITEM_STATUS_UPDATE_RESPONSE("20"),
        PATRON_ENABLE_RESPONSE("26"),
        HOLD_RESPONSE("16"),
        RENEW_RESPONSE("30"),
        RENEW_ALL_RESPONSE("66");

        private final String identifier;

        MQSubscriptionAttributes(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }
    }

}
