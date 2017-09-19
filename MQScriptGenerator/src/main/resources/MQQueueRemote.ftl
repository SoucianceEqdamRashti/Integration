DEFINE QREMOTE('MAINTENANCE_SELUADP90_ALEBUS_OAGIS_XML_OUT') +
*  ALTDATE(${headers.ALTDATE!''}) +
*  ALTTIME(${headers.ALTTIME!''}) +
   CLUSNL('${headers.CLUSNL!' '}') +
   CLUSTER(${headers.CLUSTER!""}) +
   CLCHNAME('${headers.CLCHNAME!''}') +
   CLWLPRTY(${headers.CLWLPRTY!0}) +
   CLWLRANK(${headers.CLWRANK!0}) +
   CUSTOM(${headers.CUSTOM!''}) +
   DEFBIND(${headers.DEFBIND!"OPEN"}) +
   DEFPRTY(${headers.DEFPRTY!0}) +
   DEFPSIST(${headers.DEFPSIST!"YES"}) +
   DEFPRESP(${headers.DEFPRESP!"SYNC"}) +
   DESCR('${headers.DESCR!"No description provided"}') +
   PUT(${headers.PUT!"ENABLED"}) +
   RQMNAME('${headers.RQMNAME}') +
   RNAME('${headers.RNAME}') +
   SCOPE(${headers.SCOPE!"QMGR"}) +
   XMITQ('${headers.XMITQ}') +
   REPLACE
*******************************************************************************