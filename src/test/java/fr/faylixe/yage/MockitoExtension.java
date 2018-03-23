package fr.faylixe.yage;

import java.lang.reflect.Parameter;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * 
 * @author fv
 */
public final class MockitoExtension implements TestInstancePostProcessor, ParameterResolver {

	/** {@inheritDoc} **/
	@Override
	public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) {
		MockitoAnnotations.initMocks(testInstance);
	}

	/** {@inheritDoc} **/
	@Override
	public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
		return parameterContext.getParameter().isAnnotationPresent(Mock.class);
	}

	/** {@inheritDoc} **/
	@Override
	public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
		return getMock(parameterContext.getParameter(), extensionContext);
	}

	/**
	 * 
	 * @param parameter
	 * @param extensionContext
	 * @return
	 */
	private Object getMock(final Parameter parameter, final ExtensionContext extensionContext) {
		final Class<?> mockType = parameter.getType();
		final Store mocks = extensionContext.getStore(Namespace.create(MockitoExtension.class, mockType));
		final String mockName = getMockName(parameter);
		if (mockName != null) {
			return mocks.getOrComputeIfAbsent(mockName, key -> mock(mockType, mockName));
		}
		return mocks.getOrComputeIfAbsent(mockType.getCanonicalName(), key -> mock(mockType));
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	private String getMockName(final Parameter parameter) {
		final String explicitMockName = parameter.getAnnotation(Mock.class).name().trim();
		if (!explicitMockName.isEmpty()) {
			return explicitMockName;
		}
		else if (parameter.isNamePresent()) {
			return parameter.getName();
		}
		return null;
	}

}