DEFINE QLOCAL(${headers.NAME}) +
   ACCTQ(QMGR) +
*  ALTDATE(${headers.ALTDATE!''}) +
*  ALTTIME(${headers.ALTTIME!''}) +
   BOQNAME('${headers.BOQNAME!' '}') +
   BOTHRESH(${headers.BOTHRESH!0}) +
   CLUSNL('${headers.CLUSNL!' '}') +
   CLUSTER(${headers.CLUSTER!""}) +
   CLCHNAME('${headers.CLCHNAME!''}') +
   CLWLPRTY(${headers.CLWLPRTY!0}) +
   CLWLRANK(${headers.CLWRANK!0}) +
   CLWLUSEQ(QMGR) +
*  CRDATE(${headers.CRDATE!''}) +
*  CRTIME(${headers.CRTIME!''}) +
*  CURDEPTH(${headers.CURDEPTH!0}) +
   CUSTOM(${headers.CUSTOM!''}) +
   DEFBIND(${headers.DEFBIND!"OPEN"}) +
   DEFPRTY(${headers.DEFPRTY!0}) +
   DEFPSIST(${headers.DEFPSIST!"YES"}) +
   DEFPRESP(${headers.DEFPRESP!"SYNC"}) +
   DEFREADA(${headers.DEFREADA!"NO"}) +
   DEFSOPT(${headers.DEFSOPT!"SHARED"}) +
*  DEFTYPE(${headers.DEFTYPE!"PREDEFINED"}) +
   DESCR('${headers.DESCR!"No description provided"}') +
   DISTL(${headers.DISTL!"NO"}) +
   GET(${headers.GET!"ENABLED"}) +
   HARDENBO +
   INITQ('${headers.INITQ!"" }') +
*  IPPROCS(${headers.IPPROCS!0} ) +
   MAXDEPTH(${headers.MAXDEPTH!999999999}) +
   MAXMSGL(${headers.MAXMSGL!104857600}) +
   MONQ(${headers.MONQ!"QMGR"}) +
   MSGDLVSQ(${headers.MSGDLVSQ!"PRIORITY"}) +
   NOTRIGGER +
   NPMCLASS(${headers.NPMCLASS!"NORMAL"}) +
*  OPPROCS(${headers.OPPROCS!0}) +
   PROCESS('${headers.PROCESS!""}') +
   PUT(${headers.PUT!"ENABLED"}) +
   PROPCTL(${headers.PROPCTL!"COMPAT"}) +
   QDEPTHHI(${headers.QDEPTHHI!80}) +
   QDEPTHLO(${headers.QDEPTHLO!20}) +
   QDPHIEV(${headers.QDPHIEV!"DISABLED"}) +
   QDPLOEV(${headers.QDPLOEV! "DISABLED"}) +
   QDPMAXEV(${headers.QDPMAXEV!"ENABLED"}) +
   QSVCIEV(${headers.QSVCIEV!"NONE"}) +
   QSVCINT(${headers.QSVCINT!999999999?c}) +
   RETINTVL(${headers.RETINTVL!999999999?c}) +
   SCOPE(${headers.SCOPE!"QMGR"}) +
   SHARE +
   STATQ(${headers.STATQ!"QMGR"}) +
   TRIGDATA('${headers.TRIGDATA!""}') +
   TRIGDPTH(${headers.TRIGDPTH!1}) +
   TRIGMPRI(${headers.TRIGMPRI!0}) +
   TRIGTYPE(${headers.TRIGTYPE!"FIRST"}) +
   USAGE(${headers.USAGE!"NORMAL"}) +
   REPLACE
*******************************************************************************