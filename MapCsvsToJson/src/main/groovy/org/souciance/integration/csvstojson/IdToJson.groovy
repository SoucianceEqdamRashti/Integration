package org.souciance.integration.csvstojson

import groovy.json.JsonOutput

class IdToJson {

    String toJson(def CSV) {
        return JsonOutput.toJson("ListOfRows": CSV)
    }

}