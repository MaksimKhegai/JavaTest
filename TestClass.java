import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.database.*;
import com.exceptions.InvalidSearchTypeException;
import com.exceptions.ObjectNotFoundException;
import com.exceptions.WrongArgumentException;
import com.exceptions.WrongArgumentsNumberException;
import com.ui.*;

/** 
 * Main testing class
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClass {
	static private Base base = null;
	static private Menu menu = null;
	static private Commands commands = null;
	
	@BeforeClass
	public static void init() {
		base = new Base();
		menu = new Menu();
		commands = new Commands(base);
	}
	
	@AfterClass
	public static void tearDown() {
		base = null;
		menu = null;
		commands = null;
		System.gc();
	}
	
	@Test
	public void testAddClient() throws WrongArgumentException {
		assertNotNull(base.addClient("TestFN", "TestSN", "TestLN", "10/12/1000"));
	}
	
	@Test
	public void testAddProduct() throws WrongArgumentException {
		assertNotNull(base.addProduct("TestBrand", "TestType", "TestColor", "10/12/4566"));
	}
	
	@Test
	public void testAddSale() throws WrongArgumentException, WrongArgumentsNumberException, 
										ObjectNotFoundException {
		menu.execute("show clients");
		assertNotNull(base.addSale("10/04/2222", base.findProductById(0), 
				base.findClientById(0)));
	}
	
	@Test
	public void testFindClientById() throws ObjectNotFoundException {
		assertNotNull(base.findClientById(0));
	}
	
	@Test
	public void testFindClientsByName() throws ObjectNotFoundException {
		assertTrue(base.findClientsByName("TestFN", "TestSN", "TestLN").size() != 0);
	}
	
	@Test
	public void testFindClientsByDate() throws ObjectNotFoundException, WrongArgumentException {
		assertTrue(base.findClientsByDate("10/12/1000").size() != 0);
	}
	
	@Test
	public void testFindProductById() throws ObjectNotFoundException {
		assertNotNull(base.findProductById(0));
	}
	
	@Test
	public void testFindClientsByBrand() throws ObjectNotFoundException {
		assertTrue(base.findProductsByBrand("TestBrand").size() != 0);
	}
	
	@Test
	public void testFindProductsByType() throws ObjectNotFoundException, WrongArgumentException {
		assertTrue(base.findProductsByType("TestType").size() != 0);
	}
	
	@Test
	public void testFindProductsByDate() throws ObjectNotFoundException, WrongArgumentException {
		assertTrue(base.findProductsByDate("10/12/4566").size() != 0);
	}
	
	@Test
	public void testFindSaleById() throws ObjectNotFoundException {
		assertNotNull(base.findSaleById(0));
	}
	
	@Test
	public void testFindSalesByDate() throws ObjectNotFoundException, WrongArgumentException {
		assertTrue(base.findSalesByDate("10/04/2222").size() != 0);
	}
	
	@Test
	public void testExecution() throws ObjectNotFoundException {
		assertTrue(menu.execute("show clients") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("show products") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("show sales") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("?") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("show ?") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("sort ?") == Common.errorCode.NOERROR);
		assertTrue(menu.execute("add ?") == Common.errorCode.NOERROR);
	}
	
	@Test
	public void testClientComparator() throws InvalidSearchTypeException {
		assertNotNull(commands.ChooseClientComparator(new String[]{"sort", "client", "first"}));
		assertNotNull(commands.ChooseClientComparator(new String[]{"sort", "client", "second"}));
		assertNotNull(commands.ChooseClientComparator(new String[]{"sort", "client", "last"}));
		assertNotNull(commands.ChooseClientComparator(new String[]{"sort", "client", "date"}));
	}
	
	@Test
	public void testProductComparator() throws InvalidSearchTypeException {
		assertNotNull(commands.ChooseProductComparator(new String[]{"sort", "product", "color"}));
		assertNotNull(commands.ChooseProductComparator(new String[]{"sort", "product", "date"}));
		assertNotNull(commands.ChooseProductComparator(new String[]{"sort", "product", "brand"}));
		assertNotNull(commands.ChooseProductComparator(new String[]{"sort", "product", "type"}));
	}
	
	@Test
	public void testSaleComparator() throws InvalidSearchTypeException {
		assertNotNull(commands.ChooseSaleComparator(new String[]{"sort", "sale", "client"}));
		assertNotNull(commands.ChooseSaleComparator(new String[]{"sort", "sale", "date"}));
		assertNotNull(commands.ChooseSaleComparator(new String[]{"sort", "sale", "product"}));
	}
}
