package sdf;

import forsyde.io.java.core.*;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.AsUndirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Set;
import java.util.stream.Collectors;

public class SDFTests {

    // thread safe as it is only a function holder
    final ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();

    @Test
    public void sobelSDFModel() throws Exception {
        final ForSyDeSystemGraph forSyDeSystemGraph = forSyDeModelHandler.loadModel("examples/sdf/sobel2mpsoc.forsyde.xmi");
        final Set<SDFComb> actors = forSyDeSystemGraph.vertexSet().stream().filter(SDFComb::conforms)
                .map(SDFComb::enforce).collect(Collectors.toSet());
        final Set<Vertex> actorsVertexes = actors.stream().map(VertexViewer::getViewedVertex)
                .collect(Collectors.toSet());
        final Set<SDFChannel> channels = forSyDeSystemGraph.vertexSet().stream()
                .filter(SDFChannel::conforms)
                .map(SDFChannel::enforce).collect(Collectors.toSet());
        final Graph<Vertex, EdgeInfo> undirected = new AsUndirectedGraph<>(forSyDeSystemGraph);
        final ConnectivityInspector<Vertex, EdgeInfo> inspector = new ConnectivityInspector<>(undirected);
        for (Vertex actorVertex: actorsVertexes) {
            Assertions.assertTrue(inspector.connectedSetOf(actorVertex).containsAll(actorsVertexes));
        }
        Assertions.assertEquals(4, actors.size());
        Assertions.assertEquals(4, channels.size());

        // contributed from https://github.com/YihangZhao123/master-thesis
        Assertions.assertTrue(actors.stream().anyMatch(v ->
            VertexAcessor.getNamedPort(forSyDeSystemGraph, v.getViewedVertex(), "gy", "moc::sdf::SDFChannel").isPresent() &&
            VertexAcessor.getNamedPort(forSyDeSystemGraph, v.getViewedVertex(), "gx", "moc::sdf::SDFChannel").isPresent()
        ));
        Assertions.assertTrue(actors.stream().anyMatch(v ->
                VertexAcessor.getNamedPort(forSyDeSystemGraph, v.getViewedVertex(), "gy", "moc::sdf::SDFChannel").map(SDFChannel::conforms).orElse(false) &&
                VertexAcessor.getNamedPort(forSyDeSystemGraph, v.getViewedVertex(), "gx", "moc::sdf::SDFChannel").map(SDFChannel::conforms).orElse(false)
        ));

        // check that sobel/getPx outputs 6
        Assertions.assertTrue(forSyDeSystemGraph.edgeSet().stream().filter(e ->
                    e.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE) &&
                    forSyDeSystemGraph.getEdgeSource(e).getIdentifier().equals("sobel/getPx") &&
                    SDFComb.conforms(forSyDeSystemGraph.getEdgeSource(e)) &&
                    SDFChannel.conforms(forSyDeSystemGraph.getEdgeSource(e))
                ).allMatch(e ->
                    SDFComb.safeCast(forSyDeSystemGraph.getEdgeSource(e)).flatMap(sourceSDF ->
                        e.getSourcePort().map(port -> sourceSDF.getProduction().get(port).equals(6))
                    ).orElse(false)
                ));

    }

}
