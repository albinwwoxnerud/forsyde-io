/*
 * generated by Xtext 2.25.0
 */
package io.github.forsyde.io.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class MetaAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("io/github/forsyde/io/parser/antlr/internal/InternalMeta.tokens");
	}
}
