package bentolor.jdkenumerator;

import io.github.classgraph.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class tries iterate over all Java JDK 12 modules / symbol to build a wordlist of every JDK symbol as (spell checking)
 * dictionary. Targets: https://github.com/Jason3S/cspell-dicts
 */
public final class JdkDictionaryExtractor {

    private final List<String> reservedJavaWords = List.of("abstract", "continue", "for", "new", "switch", "assert",
            "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double",
            "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof",
            "return", "transient", "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile", "const", "float", "native", "super", "while", "true", "false", "null");

    private final Set<String> dictionary = new HashSet<>(reservedJavaWords);

    private JdkDictionaryExtractor() {
    }

    public static void main(String[] args) throws IOException {
        var dictionary = new JdkDictionaryExtractor().createDictionary();
        var outfile = Path.of("java-keywords.txt");
        log("(Re)writing: "+outfile);
        Files.deleteIfExists(outfile);
        Files.write(outfile, dictionary, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
    }

    private List<String> createDictionary() {
        log("Scanning JDK Modules");
        var cg = new ClassGraph()
                .enableClassInfo().enableFieldInfo().enableMethodInfo()
                .enableSystemJarsAndModules()
                .whitelistModules("*")
                .blacklistModules("bentolor.jdkenumerator", "io.github.classgraph");
        try (var sr = cg.scan()) {
            log("Found "+sr.getModules().size()+" JDK modules");
            sr.getAllClasses().stream()
                    .filter(ci -> ci.isPublic() && !ci.isAnonymousInnerClass() && !ci.isInnerClass() && !isInternalPackage(ci))
                    .forEach(this::handleClass);
        }

        return dictionary.stream()
                .filter(s -> (s.length() > 3) && !s.contains("$"))
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    private boolean isInternalPackage(ClassInfo ci) {
        return ci.getPackageName().matches(".*\\bsun\\b.*");
    }


    private void handleClass(ClassInfo classInfo) {
        String className = classInfo.getSimpleName();
        log("=== Processing class: " + classInfo.getName());
        dictionary.add(className);
        classInfo.getFieldInfo().stream().filter(FieldInfo::isPublic).map(FieldInfo::getName).forEach((fieldName) -> {
            log("  Adding field:" + fieldName);
            dictionary.add(fieldName);
        });
        classInfo.getDeclaredMethodInfo().stream().filter(MethodInfo::isPublic).map(MethodInfo::getName).forEach((methodName) -> {
            log("  Adding method:" + methodName + "(…)");
            dictionary.add(methodName);
        });
    }

    private static void log(String log) {
        System.out.println(log);
    }
}
