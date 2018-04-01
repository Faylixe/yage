package fr.faylixe.yage.cpu.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.anyByte;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;
import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.cpu.register.IRegisterProviderTest;
import fr.faylixe.yage.memory.IMemoryStream;
import fr.faylixe.yage.memory.IMemoryStreamTest;

/**
 * 
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IInstructionSetTest extends IInstructionStreamTest, IMemoryStreamTest, IRegisterProviderTest {

	/**
	 * 
	 * @param expectedOpcode
	 * @param expectedCycle
	 * @param instruction
	 * @param test
	 */
	default void performInstructionTest(
			final int expectedOpcode,
			final int expectedCycle,
			final IInstruction instruction,
			final Consumer<IExecutionContext> test) {
		assertEquals(expectedOpcode, instruction.getOpcode());
		assertEquals(expectedCycle, instruction.getCycle());
		final IExecutionContext context = mock(IExecutionContext.class);
		bindMemoryStream(context, getTestMemoryStream());
		bindInstructionStream(context, getTestInstructionStream());
		bindRegisterProvider(context, getTestRegisterProvider());
		try {
			instruction.execute(context);
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
		test.accept(context);
	}

	/**
	 * 
	 * @param context
	 * @param stream
	 */
	static void bindMemoryStream(final IExecutionContext context, final IMemoryStream stream) {
		try {
			when(context.readByte(anyInt()))
				.then(arg -> stream.readByte(arg.getArgument(0)));
			when(context.readBytes(anyInt(), anyInt()))
				.then(arg -> stream.readBytes(arg.getArgument(0), arg.getArgument(1)));
			//when(context)
			//	.writeByte(anyByte(), anyInt())
			//	.do();
			//doAnswer(arg -> stream.writeByte(arg.getArgument(0), arg.getArgument(1)))
				
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
	}

	/**
	 * 
	 * @param context
	 * @param stream
	 */
	static void bindInstructionStream(final IExecutionContext context, final IInstructionStream stream) {
		// TODO : Implements.
	}

	/**
	 * 
	 * @param context
	 * @param provider
	 */
	static void bindRegisterProvider(final IExecutionContext context, final IRegisterProvider provider) {
		// TODO : Implements.
	}

}
