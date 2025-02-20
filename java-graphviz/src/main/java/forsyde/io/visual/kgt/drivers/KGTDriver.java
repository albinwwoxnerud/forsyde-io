package forsyde.io.visual.kgt.drivers;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses;
import forsyde.io.core.EdgeInfo;
import forsyde.io.core.EdgeTrait;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.core.*;
import forsyde.io.core.drivers.ModelDriver;
import forsyde.io.visual.kgt.adapter.ForSyDe2KGTNode;
import forsyde.io.visual.kgt.adapter.KlighDContainer;
import forsyde.io.visual.kgt.adapter.KlighDEdge;
import forsyde.io.visual.kgt.adapter.KlighDNodeView;
import org.ainslec.picocog.PicoWriter;
import org.jgrapht.Graph;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class KGTDriver implements ModelDriver, ForSyDe2KGTNode {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("kgt", "kgraph");
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        throw new UnsupportedOperationException("'ForSyDeKGTDriver' does not support loading Kgraph models.");
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {
        final KNode kNode = convert(model);

//        final PicoWriter topWriter = new PicoWriter();
//        // keep a map of writer to make sure that we can write everything correctly
//        final Map<KlighDNodeView, PicoWriter> writers = new HashMap<>();
//        // we make a fake top to make sure everything works even in the absence of a
//        // container for all elements
//        topWriter.writeln_r("knode forsyde {");
//        // topWriter.writeln("krectangle");
//        topWriter.writeln("klabel \"ForSyDe Model\"");
//        for (KlighDNodeView root : klighDContainer.getRoots()) {
//            writers.put(root, topWriter.createDeferredWriter());
//        }
//        topWriter.writeln_l("}");
//        final Queue<KlighDNodeView> queue = new ArrayDeque<>(klighDContainer.getRoots());
//        while(!queue.isEmpty()) {
//            final KlighDNodeView cur = queue.poll();
//            final PicoWriter curWriter = writers.getOrDefault(cur, topWriter);
//            final String vId = cur.getId().replace(" ", "_").replace(".", "_");
//            curWriter.writeln_r("knode " + vId + " {");
//            curWriter.writeln("klabel \"" + cur.getLabel() + "\"");
//            for (String port: cur.getActiveKports(model)) {
//                final String portString = port.replace(" ", "_").replace(".", "_");
//                curWriter.writeln_r("kport " + portString + " {");
//                curWriter.writeln("klabel \"" + port + "\"");
//                curWriter.writeln_l("}");
//            }
//            if (cur.hasVisualizableProperties()) {
//                curWriter.writeln_r("knode " + vId + "vProperties {");
//                // TODO: until I figure out how to make the size of the node label be respected,
//                // this small sizing hack with width and height stays.
//                final int width = 5 * cur.getVisualizedProperties().entrySet().stream()
//                        .mapToInt(e -> ("\\n " + e.getKey() + ": " + e.getValue()).length()).sum();
//                final int height = 35 * cur.getVisualizedProperties().size();
//                curWriter.writeln("size: width=" + width + " height=" + height);
//                curWriter.write("klabel \"properties:");
//                cur.getVisualizedProperties()
//                        .forEach((k, v) -> curWriter.write("\\n " + k + ": " + v));
//                curWriter.writeln("\"");
//                curWriter.writeln_l("}");
//            }
//            for (KlighDNodeView child: cur.getChildren()) {
//                writers.put(child, curWriter.createDeferredWriter());
//                queue.add(child);
//            }
//            for (KlighDEdge edge: cur.getKedges()) {
//                edge.getSrcPort().ifPresentOrElse(srcPort -> {
//                    edge.getDstPort().ifPresentOrElse(dstPort -> {
//                        curWriter.writeln("kedge ( " +
//                                ":" + srcPort +
//                                " -> " +
//                                edge.getTarget().getId().replace(" ", "_").replace(".", "_") +
//                                ":" + dstPort +
//                                ")");
//                    }, () -> {
//                                ":" + srcPort +
//                                " -> " +
//                                edge.getTarget().getId().replace(" ", "_").replace(".", "_") +
//                                ")");
//                    });
//                }, () -> {
//                    curWriter.writeln("kedge ( " +
//                            " -> " +
//                            edge.getTarget().getId().replace(" ", "_").replace(".", "_") +
//                            ")");
//                });
//            }
//            curWriter.writeln_l("}");
//        }
//        out.write(topWriter.toString().getBytes(StandardCharsets.UTF_8));
    }

//    protected Vertex getParentInContainmentGraph(Graph<Vertex, EdgeInfo> containmentGraph, Vertex child) {
//        return containmentGraph.incomingEdgesOf(child).stream()
//                .filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))
//                .map(containmentGraph::getEdgeSource)
//                .filter(GreyBox::conforms)
//                .findFirst().orElse(null);
//    }
}
