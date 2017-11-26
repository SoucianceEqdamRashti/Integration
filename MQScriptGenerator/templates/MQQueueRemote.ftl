DEFINE QREMOTE('${headers.NAME}') +
*  ALTDATE(${headers.ALTDATE}) +
*  ALTTIME(${headers.ALTTIME}) +
   CLUSNL(<#if headers.CLUSNL = "">' '<#else>${headers.CLUSNL}</#if>) +
   CLUSTER(<#if headers.CLUSTER == "">' '<#else>${headers.CLUSTER}</#if>) +
   CLWLPRTY(<#if headers.CLWLPRTY == "">0<#else>${headers.CLWLPRTY}</#if>) +
   CLWLRANK(<#if headers.CLWLRANK == "">0<#else>${headers.CLWLRANK}</#if>) +
   CUSTOM(<#if headers.CUSTOM == "">' '<#else>${headers.CUSTOM}</#if>) +
   DEFBIND(<#if headers.DEFBIND == "">OPEN<#else>${headers.DEFBIND}</#if>) +
   DEFPRTY(<#if headers.DEFPRTY == "">0<#else>${headers.DEFPRTY}</#if>) +
   DEFPSIST(<#if headers.DEFPSIST == "">YES<#else>${headers.DEFPSIST}</#if>) +
   DEFPRESP(<#if headers.DEFPRESP == "">SYNC<#else>${headers.DEFPRESP}</#if>) +
   DESCR(<#if headers.DESCR == "">' '<#else>${headers.DESCR}</#if>) +
   PUT(<#if headers.PUT == "">ENABLED<#else>${headers.PUT}</#if>) +
   RQMNAME('${headers.RQMNAME}') +
   RNAME('${headers.RNAME}') +
   SCOPE(<#if headers.SCOPE == "">QMGR<#else>${headers.SCOPE}</#if>) +
   XMITQ('${headers.XMITQ}') +
   REPLACE
*******************************************************************************