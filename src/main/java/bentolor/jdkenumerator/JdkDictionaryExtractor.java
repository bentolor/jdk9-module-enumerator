package bentolor.jdkenumerator;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ModuleRef;

/**
 * This class tries iterate over all Java JDK 12 modules / symbol to build a wordlist of every JDK symbol as (spell checking)
 * dictionary.
 */
public final class JdkDictionaryExtractor {

    public static void main(String[] args) {
//        var modules = ModuleLayer.boot().modules();
//        modules.stream().map(Module::getDescriptor).map(ModuleDescriptor::name)
//                        .forEach(System.out::println);

        var cg = new ClassGraph()
                .enableClassInfo().enableFieldInfo().enableMethodInfo().enableAnnotationInfo()
                .verbose()
                .enableSystemJarsAndModules()
                .whitelistModules("*")
                .blacklistModules("bentolor.jdkenumerator", "io.github.classgraph");
        try (var sr = cg.scan()) {
            sr.getModules().stream().map(ModuleRef::getName).forEach(System.out::println);
            System.out.println(sr.getModules().size());
        }
    }
}
