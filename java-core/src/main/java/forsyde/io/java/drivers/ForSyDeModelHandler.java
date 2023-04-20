/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.inference.PopulateTaskCommunications;
import forsyde.io.java.inference.SystemGraphInference;
import forsyde.io.java.migrations.*;
import forsyde.io.java.validation.SDFValidator;
import forsyde.io.java.validation.SystemGraphValidation;

/**
 * @author rjordao
 *
 */
public final class ForSyDeModelHandler {

	private final List<SystemGraphMigrator> registeredMigrators = new ArrayList<>();
	private final List<SystemGraphValidation> registesteredValidators = new ArrayList<>();
	private final List<SystemGraphInference> registeredInferences = new ArrayList<>();
	private final List<ForSyDeModelDriver> registeredDrivers = new ArrayList<>();
	private final List<PathMatcher> registeredDriversInputMatchers = new ArrayList<>();
	private final List<PathMatcher> registeredDriversOutputMatchers = new ArrayList<>();
//	PathMatcher forsydeMLMatcher;
//	PathMatcher graphMLMatcher;
//	PathMatcher dotMatcher;
//	PathMatcher linguaFrancaMatcher;
//	PathMatcher amaltheaMatcher;

	public ForSyDeModelHandler(ForSyDeModelDriver... extraDrivers) {
		// register default inferences
		registeredInferences.add(new PopulateTaskCommunications());
		// register default migrators
		registeredMigrators.add(new NoMoreReactiveTaskMigration());
        registeredMigrators.add(new TaskCallSequenceSplit());
		registeredMigrators.add(new SDFCombToSDFActorConversion());
		registeredMigrators.add(new ParallelSkeletonsNameMigrator());
		registeredMigrators.add(new MadeMocAndParallelPortsMultipleMigrator());
		// default validators
		registesteredValidators.add(new SDFValidator());
		// register default drivers
        registeredDrivers.add(new ForSyDeFiodlHandler());
		// register extra drivers
		registeredDrivers.addAll(Arrays.asList(extraDrivers));
		// make their
		for (ForSyDeModelDriver driver : registeredDrivers) {
			final String inExtensions = "{" + String.join(",", driver.inputExtensions()) + "}";
			final String outExtensions = "{" + String.join(",", driver.outputExtensions()) + "}";
			registeredDriversInputMatchers.add(
					FileSystems.getDefault().getPathMatcher("glob:**." + inExtensions)
			);
			registeredDriversOutputMatchers.add(
					FileSystems.getDefault().getPathMatcher("glob:**." + outExtensions)
			);
		}
	}

	public ForSyDeModelHandler registerSystemGraphMigrator(SystemGraphMigrator systemGraphMigrator, int applyOrder) {
		registeredMigrators.add(applyOrder, systemGraphMigrator);
		return this;
	}

	public ForSyDeModelHandler registerDriver(ForSyDeModelDriver extraDriver, int loadOrder) {
		if (!registeredDrivers.contains(extraDriver)) {
			final String inExtensions = "{" + String.join(",", extraDriver.inputExtensions()) + "}";
			final String outExtensions = "{" + String.join(",", extraDriver.outputExtensions()) + "}";
			registeredDrivers.add(loadOrder, extraDriver);
			registeredDriversInputMatchers.add(loadOrder,
					FileSystems.getDefault().getPathMatcher("glob:**." + inExtensions)
			);
			registeredDriversOutputMatchers.add(loadOrder,
					FileSystems.getDefault().getPathMatcher("glob:**." + outExtensions)
			);
		}
		return this;
	}

	public ForSyDeModelHandler registerDriver(ForSyDeModelDriver extraDriver) {
		return registerDriver(extraDriver, registeredDrivers.size());
	}

	public boolean canLoadModel(Path path) {
		return registeredDriversInputMatchers.stream().anyMatch(d ->
				d.matches(path)
		);
	}

	public boolean canWriteModel(Path path) {
		return registeredDriversOutputMatchers.stream().anyMatch(d ->
				d.matches(path)
		);
	}

	public ForSyDeSystemGraph loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	public ForSyDeSystemGraph loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	public ForSyDeSystemGraph loadModel(Path inPath) throws Exception {
		for (int i = 0; i < registeredDrivers.size(); i++) {
			if (registeredDriversInputMatchers.get(i).matches(inPath)) {
				final ForSyDeSystemGraph forSyDeSystemGraph = registeredDrivers.get(i).loadModel(inPath);
				for (SystemGraphMigrator systemGraphMigrator : registeredMigrators) {
					if (!systemGraphMigrator.effect(forSyDeSystemGraph)) {
						throw new Exception("Migrator " + systemGraphMigrator.getName() + " has failed its migration.");
					}
				}
				for (SystemGraphValidation validation : registesteredValidators) {
					final Optional<String> validationResult = validation.validate(forSyDeSystemGraph);
					if (validationResult.isPresent()) {
						throw new Exception(validationResult.get());
					}
				}
				registeredInferences.forEach(inference -> inference.infer(forSyDeSystemGraph));
				return forSyDeSystemGraph;
			}
		}
		throw new Exception("Unsupported read format for file: " + inPath.toString());
//		if (forsydeMLMatcher.matches(inPath)) {
//			driver = new ForSyDeMLDriver();
//		} else if(linguaFrancaMatcher.matches(inPath)) {
//			driver = new ForSyDeLFDriver();
//		} else if(amaltheaMatcher.matches(inPath)) {
//			driver = new ForSyDeAmaltheaDriver();
//		} else {
//
//		}
//		return driver.loadModel(inPath);
	}
	
	public void writeModel(ForSyDeSystemGraph model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	public void writeModel(ForSyDeSystemGraph model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	public void writeModel(ForSyDeSystemGraph model, Path outPath) throws Exception {
		registeredInferences.forEach(inference -> inference.infer(model));
		for (SystemGraphValidation validation : registesteredValidators) {
			final Optional<String> validationResult = validation.validate(model);
			if (validationResult.isPresent()) {
				throw new Exception(validationResult.get());
			}
		}
		for (int i = 0; i < registeredDrivers.size(); i++) {
			if (registeredDriversOutputMatchers.get(i).matches(outPath)) {
				registeredDrivers.get(i).writeModel(model, outPath);
				return;
			}
		}
		throw new Exception("Unsupported write format for file: " + outPath.toString());
//		if (forsydeMLMatcher.matches(outPath)) {
//			driver = new ForSyDeMLDriver();
//		} else if(graphMLMatcher.matches(outPath)) {
//			driver = new ForSyDeGraphMLDriver();
//		}  else if(dotMatcher.matches(outPath)) {
//			driver = new ForSyDeDOTDriver();
//		} else {
//			throw new Exception("Supported write formats: ['forxml', 'forsyde.xml', 'graphml', 'dot', 'gv', 'graphviz'].");
//		}
//		if (outPath.getParent() != null)
//			Files.createDirectories(outPath.getParent());
//		try {
//			Files.createFile(outPath);
//		} catch (FileAlreadyExistsException ignored) {}
//		driver.writeModel(model, outPath);
	}
}
