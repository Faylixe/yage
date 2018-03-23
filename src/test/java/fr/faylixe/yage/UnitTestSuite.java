package fr.faylixe.yage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.faylixe.yage.memory.MemoryTestSuite;

/**
 * 
 * @author fv
 */
@RunWith(Suite.class)
@SuiteClasses({
	MemoryTestSuite.class
})
public final class UnitTestSuite {

}
