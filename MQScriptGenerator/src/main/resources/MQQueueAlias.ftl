DEFINE QALIAS('${headers.NAME}') +
*  ALTDATE(${headers.ALTDATE!''}) +
*  ALTTIME(${headers.ALTTIME!''}) +
   TARGET('${headers.TARGET}') +
   CLUSNL('${headers.CLUSNL!''}') +
   CLUSTER('${headers.CLUSTER!''}') +
   CLWLPRTY(${headers.CLWLPRTY!0}) +
   CLWLRANK(${headers.CLWRANK!0}) +
   CUSTOM('${headers.CUSTOM!''}') +
   DEFBIND(${headers.DEFBIND!"OPEN"}) +
   DEFPRTY(${headers.DEFPRTY!0}) +
   DEFPSIST(${headers.DEFPSIST!"YES"}) +
   DEFPRESP(${headers.DEFPRESP!"SYNC"}) +
   DEFREADA(${headers.DEFREADA!"NO"}) +
   DESCR('${headers.DESCR!"No description provided"}') +
   GET(${headers.GET!"ENABLED"}) +
   PUT(${headers.PUT!"ENABLED"}) +
   PROPCTL(${headers.PROPCTL!"COMPAT"}) +
   SCOPE(${headers.SCOPE!"QMGR"}) +
   TARGTYPE(${headers.TARGTYPE!"QUEUE"}) +
   REPLACE
*******************************************************************************