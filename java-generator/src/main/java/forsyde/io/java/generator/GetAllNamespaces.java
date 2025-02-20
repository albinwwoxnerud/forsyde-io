package forsyde.io.java.generator;

import forsyde.io.java.generator.dsl.ForSyDeTraitDSLLexer;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.exceptions.InconsistentTraitHierarchyException;
import forsyde.io.java.generator.specs.EdgeTraitSpec;
import forsyde.io.java.generator.specs.TraitHierarchy;
import forsyde.io.java.generator.specs.VertexTraitSpec;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
//import org.gradle.api.DefaultTask;
//import org.gradle.api.Task;
//import org.gradle.api.file.RegularFileProperty;
//import org.gradle.api.tasks.InputFile;
//import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class GetAllNamespaces { // extends DefaultTask implements Task {

//    @InputFile
//    abstract RegularFileProperty getInputModelDSL();// = getProject().file("traithierarchy.traitdsl");
//
//    @TaskAction
//    public void execute() {
//        try {
//            final ForSyDeTraitDSLLexer forSyDeTraitDSLLexer = new ForSyDeTraitDSLLexer(CharStreams.fromPath(getInputModelDSL().get().getAsFile().toPath()));
//            final CommonTokenStream commonTokenStream = new CommonTokenStream(forSyDeTraitDSLLexer);
//            final ForSyDeTraitDSLParser forSyDeTraitDSLParser = new ForSyDeTraitDSLParser(commonTokenStream);
//            final ForSyDeIOTraitDSLVisitor forSyDeIOTraitDSLVisitor = new ForSyDeIOTraitDSLVisitor();
//            //final ForSyDeIOTraitDSLListener forSyDeTraitDSLListener = new ForSyDeIOTraitDSLListener();
//            //final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
//            //parseTreeWalker.walk(forSyDeTraitDSLListener, forSyDeTraitDSLParser.rootTraitHierarchy());
//            final TraitHierarchy traitHierarchy = forSyDeIOTraitDSLVisitor.getRootTraitHierarchy(forSyDeTraitDSLParser.rootTraitHierarchy());//objectMapper.readValue(inputModelJson, TraitHierarchy.class);
//            Set<String> namespaces = new HashSet<>();
//            for (VertexTraitSpec vertexTraitSpec : traitHierarchy.vertexTraits) {
//                if (vertexTraitSpec.getNamespaces().isEmpty()) {
//                    namespaces.add("forsyde.io.java.typed.viewers");
//                } else {
//                    namespaces.add("forsyde.io.java.typed.viewers." + String.join(".", vertexTraitSpec.getNamespaces()));
//                }
//            }
//            for (EdgeTraitSpec edgeTraitSpec : traitHierarchy.edgeTraits) {
//                if (edgeTraitSpec.getNamespaces().isEmpty()) {
//                    namespaces.add("forsyde.io.java.typed.edges");
//                } else {
//                    namespaces.add("forsyde.io.java.typed.edges." + String.join(".", edgeTraitSpec.getNamespaces()));
//                }
//            }
//            for (String name : namespaces) {
//                System.out.println(name);
//            }
//        } catch (IOException | InconsistentTraitHierarchyException e) {
//            e.printStackTrace();
//        }
//    }
}
