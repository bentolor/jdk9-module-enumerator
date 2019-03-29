/**
 * Open module to enable JDK9+ module scanning.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
open module bentolor.jdkenumerator {
    requires io.github.classgraph;
    // Get the complete module list by executing:
    //        java --list-modules
    // This is the list according to JDK 12
    requires java.base;
    requires java.compiler;
    requires java.datatransfer;
    requires java.desktop;
    requires java.instrument;
    requires java.logging;
    requires java.management;
    requires java.management.rmi;
    requires java.naming;
    requires java.net.http;
    requires java.prefs;
    requires java.rmi;
    requires java.scripting;
    requires java.se;
    requires java.security.jgss;
    requires java.security.sasl;
    requires java.smartcardio;
    requires java.sql;
    requires java.sql.rowset;
    requires java.transaction.xa;
    requires java.xml;
    requires java.xml.crypto;
    requires jdk.accessibility;
    requires jdk.aot;
    requires jdk.attach;
    requires jdk.charsets;
    requires jdk.compiler;
    requires jdk.crypto.cryptoki;
    requires jdk.crypto.ec;
    requires jdk.dynalink;
    requires jdk.editpad;
    requires jdk.hotspot.agent;
    requires jdk.httpserver;
    requires jdk.internal.ed;
    requires jdk.internal.jvmstat;
    requires jdk.internal.le;
    requires jdk.internal.opt;
    requires jdk.internal.vm.ci;
    requires jdk.internal.vm.compiler;
    requires jdk.internal.vm.compiler.management;
    requires jdk.jartool;
    requires jdk.javadoc;
    requires jdk.jcmd;
    requires jdk.jconsole;
    requires jdk.jdeps;
    requires jdk.jdi;
    requires jdk.jdwp.agent;
    requires jdk.jfr;
    requires jdk.jlink;
    requires jdk.jshell;
    requires jdk.jsobject;
    requires jdk.jstatd;
    requires jdk.localedata;
    requires jdk.management;
    requires jdk.management.agent;
    requires jdk.management.jfr;
    requires jdk.naming.dns;
    requires jdk.naming.rmi;
    requires jdk.net;
    requires jdk.rmic;
    requires jdk.sctp;
    requires jdk.security.auth;
    requires jdk.security.jgss;
    requires jdk.unsupported;
    requires jdk.unsupported.desktop;
    requires jdk.xml.dom;
    requires jdk.zipfs;
}