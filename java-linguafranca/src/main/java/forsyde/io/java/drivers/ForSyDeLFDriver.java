package forsyde.io.java.drivers;


import com.google.inject.Injector;
import forsyde.io.java.adapters.LinguaFrancaAdapter;
import forsyde.io.java.core.ForSyDeSystemGraph;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.lflang.LFStandaloneSetup;
import org.lflang.lf.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public class ForSyDeLFDriver implements ForSyDeModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of("lf", "linguafranca");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of();
    }

    @Override
    public ForSyDeSystemGraph loadModel(Path inPath) throws Exception {
        final Injector injector = new LFStandaloneSetup().createInjectorAndDoEMFRegistration();
        final ResourceSet resourceSet = injector.getInstance(ResourceSet.class);
        final LinguaFrancaAdapter linguaFrancaAdapter = new LinguaFrancaAdapter();
        final Resource res = resourceSet.getResource(URI.createURI(inPath.toAbsolutePath().toString()), true);
        return linguaFrancaAdapter.convert((Model) res.getContents().get(0));
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        final Injector injector = new LFStandaloneSetup().createInjectorAndDoEMFRegistration();
        final ResourceSet resourceSet = injector.getInstance(ResourceSet.class);
        final Resource res = resourceSet.getResource(URI.createURI("inmemory-lf.lf"), true);
        res.load(in, resourceSet.getLoadOptions());
        final LinguaFrancaAdapter linguaFrancaAdapter = new LinguaFrancaAdapter();
        return linguaFrancaAdapter.convert((Model) res.getContents().get(0));
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        throw new Exception("Lingua Franca writing is not supported");
    }


}
