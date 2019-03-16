package com.uniovi.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;
import com.uniovi.entities.types.Status;
import com.uniovi.repositories.SalesRepository;
import com.uniovi.repositories.UserRepository;
import com.uniovi.service.SalesService;
import com.uniovi.service.UsersService;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class sdiEntrega11010Tests {

    @Autowired
    private UsersService userService;

    @Autowired
    private SalesService saleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalesRepository salesRepository;

    static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckdriver022 = "C:\\Users\\Violeta\\Desktop\\SDI sesion 5 material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
    // En MACOSX (Debe ser la versión 65.0.1y desactivar las actualizacioens
    // automáticas):
    // staticString
    // PathFirefox65="/Applications/Firefox.app/Contents/MacOS/firefox-­‐bin";
    // staticString Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
    // Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox65, Geckdriver022);
    static String URL = "http://localhost:8090/home";

    public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
	System.setProperty("webdriver.firefox.bin", PathFirefox);
	System.setProperty("webdriver.gecko.driver", Geckdriver);
	WebDriver driver = new FirefoxDriver();
	return driver;
    }

    @Before
    public void setUp() {
	initdb();
	driver.navigate().to(URL);
    }

    private void initdb() {
	saleService.deleteAll();
	userService.deleteAll();

	User admin = new User();
	admin.setEmail("admin@email.com");
	admin.setPassword("admin");
	admin.setRole(Role.ROLE_ADMIN);
	admin.setName("Admin");
	admin.setSurname("Admin");
	admin.setValid(true);
	userService.addUser(admin);

	User user1 = new User();
	user1.setEmail("user1@email.com");
	user1.setPassword("12345");
	user1.setRole(Role.ROLE_STANDARD);
	user1.setName("User1");
	user1.setSurname("user1");
	user1.setValid(true);
	user1.setMoney(100);
	userService.addUser(user1);

	User user2 = new User();
	user2.setEmail("user2@email.com");
	user2.setPassword("12345");
	user2.setRole(Role.ROLE_STANDARD);
	user2.setName("User2");
	user2.setSurname("User2");
	user2.setValid(true);
	user2.setMoney(250);
	userService.addUser(user2);

	User user3 = new User();
	user3.setEmail("user3@email.com");
	user3.setPassword("12345");
	user3.setRole(Role.ROLE_STANDARD);
	user3.setName("User3");
	user3.setSurname("User3");
	user3.setValid(true);
	user3.setMoney(80.95);
	userService.addUser(user3);

	User user4 = new User();
	user4.setEmail("user4@email.com");
	user4.setPassword("12345");
	user4.setRole(Role.ROLE_STANDARD);
	user4.setName("User4");
	user4.setSurname("User4");
	user4.setValid(true);
	user4.setMoney(125.30);
	userService.addUser(user4);

	User user5 = new User();
	user5.setEmail("user5@email.com");
	user5.setPassword("12345");
	user5.setRole(Role.ROLE_STANDARD);
	user5.setName("User5");
	user5.setValid(true);
	user5.setSurname("User5");
	user5.setMoney(50);
	userService.addUser(user5);

	// Ofertas de cada usuario

	Sale oferta1a = new Sale();
	Sale oferta1b = new Sale();
	Sale oferta1c = new Sale();
	oferta1a.setTitle("Oferta 1a");
	oferta1b.setTitle("Oferta 1b");
	oferta1c.setTitle("Oferta 1c");
	oferta1a.setValid(true);
	oferta1b.setValid(true);
	oferta1c.setValid(true);
	oferta1a.setStatus(Status.SOLD);
	oferta1b.setStatus(Status.SOLD);
	oferta1c.setStatus(Status.ONSALE);
	oferta1a.setPrice(10);
	oferta1b.setPrice(110);
	oferta1c.setPrice(50);

	oferta1a.setOwner(user1);
	oferta1b.setOwner(user1);
	oferta1c.setOwner(user1);

	Sale oferta2a = new Sale();
	Sale oferta2b = new Sale();
	Sale oferta2c = new Sale();
	oferta2a.setTitle("Oferta 2a");
	oferta2b.setTitle("Oferta 2b");
	oferta2c.setTitle("Oferta 2c");
	oferta2a.setOwner(user2);
	oferta2b.setOwner(user2);
	oferta2c.setOwner(user2);
	oferta2a.setValid(true);
	oferta2b.setValid(true);
	oferta2c.setValid(true);
	oferta2a.setStatus(Status.ONSALE);
	oferta2b.setStatus(Status.SOLD);
	oferta2c.setStatus(Status.SOLD);
	oferta2a.setPrice(100);
	oferta2b.setPrice(10.50);
	oferta2c.setPrice(55.6);

	Sale oferta3a = new Sale();
	Sale oferta3b = new Sale();
	Sale oferta3c = new Sale();
	oferta3a.setTitle("Oferta 3a");
	oferta3b.setTitle("Oferta 3b");
	oferta3c.setTitle("Oferta 3c");
	oferta3a.setOwner(user3);
	oferta3b.setOwner(user3);
	oferta3c.setOwner(user3);
	oferta3a.setValid(true);
	oferta3b.setValid(true);
	oferta3c.setValid(true);
	oferta3a.setStatus(Status.SOLD);
	oferta3b.setStatus(Status.ONSALE);
	oferta3c.setStatus(Status.SOLD);
	oferta3a.setPrice(1000);
	oferta3b.setPrice(116);
	oferta3c.setPrice(35.25);

	Sale oferta4a = new Sale();
	Sale oferta4b = new Sale();
	Sale oferta4c = new Sale();
	oferta4a.setTitle("Oferta 4a");
	oferta4b.setTitle("Oferta 4b");
	oferta4c.setTitle("Oferta 4c");
	oferta4a.setOwner(user4);
	oferta4b.setOwner(user4);
	oferta4c.setOwner(user4);

	oferta4a.setValid(true);
	oferta4b.setValid(true);
	oferta4c.setValid(true);
	oferta4a.setStatus(Status.SOLD);
	oferta4b.setStatus(Status.SOLD);
	oferta4c.setStatus(Status.ONSALE);

	oferta4a.setPrice(12.6);
	oferta4b.setPrice(63);
	oferta4c.setPrice(21.50);
	Sale oferta5a = new Sale();
	Sale oferta5b = new Sale();
	Sale oferta5c = new Sale();
	oferta5a.setTitle("Oferta 5a");
	oferta5b.setTitle("Oferta 5b");
	oferta5c.setTitle("Oferta 5c");
	oferta5a.setOwner(user5);
	oferta5b.setOwner(user5);
	oferta5c.setOwner(user5);
	oferta5a.setValid(true);
	oferta5b.setValid(true);
	oferta5c.setValid(true);
	oferta5a.setStatus(Status.SOLD);
	oferta5b.setStatus(Status.ONSALE);
	oferta5c.setStatus(Status.SOLD);
	oferta5a.setPrice(47.23);
	oferta5b.setPrice(222);
	oferta5c.setPrice(2.50);
	// Compras
	oferta1a.setBuyer(user2);
	oferta1b.setBuyer(user3);

	oferta2c.setBuyer(user1);
	oferta2b.setBuyer(user4);

	oferta3a.setBuyer(user4);
	oferta3c.setBuyer(user5);

	oferta4a.setBuyer(user3);
	oferta4b.setBuyer(user5);

	oferta5a.setBuyer(user1);
	oferta5c.setBuyer(user2);

	saleService.addSale(oferta1a);
	saleService.addSale(oferta1b);
	saleService.addSale(oferta1c);

	saleService.addSale(oferta2a);
	saleService.addSale(oferta2b);
	saleService.addSale(oferta2c);

	saleService.addSale(oferta3a);
	saleService.addSale(oferta3b);
	saleService.addSale(oferta3c);

	saleService.addSale(oferta4a);
	saleService.addSale(oferta4b);
	saleService.addSale(oferta4c);

	saleService.addSale(oferta5a);
	saleService.addSale(oferta5b);
	saleService.addSale(oferta5c);

    }

    @Test
    public void testP01() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.xpath("//*[contains(text(),'Regístrate')]")).click();
	driver.findElement(By.name("email")).click();
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("nuevousuario@email.com");
	driver.findElement(By.name("name")).click();
	driver.findElement(By.name("name")).clear();
	driver.findElement(By.name("name")).sendKeys("UsuarioNuevo");
	driver.findElement(By.name("surname")).click();
	driver.findElement(By.name("surname")).clear();
	driver.findElement(By.name("surname")).sendKeys("UsuarioNuevo");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.name("passwordConfirm")).click();
	driver.findElement(By.name("passwordConfirm")).clear();
	driver.findElement(By.name("passwordConfirm")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();

	// El usuario pasa a la página de inicio.
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Usuario autenticado como:\t')]"));
	assertTrue(list.size() > 0);

	// Usuario se deconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();

    }

    @Test
    public void testP02() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.xpath("//*[contains(text(),'Regístrate')]")).click();
	// Todos los campos vacios
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();
	// El usuario sigue en la página de registro
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Regístrate')]"));
	assertTrue(list.size() > 0);
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("123@email.com");
	// Todos los campos vacios menos el email
	// Usuario sigue en página de registro
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();
	driver.findElement(By.name("name")).click();
	driver.findElement(By.name("name")).clear();
	driver.findElement(By.name("name")).sendKeys("Carmen");
	// Todos los campos vacios menos email y nombre
	// Usuario sigue en página de registro
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();
	driver.findElement(By.name("surname")).click();
	driver.findElement(By.name("surname")).clear();
	driver.findElement(By.name("surname")).sendKeys("Perez Almonte");
	// Campos de contraseñas vacios
	// Usuario sigue en página de registro
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();
    }

    @Test
    public void testP03() throws Exception {
	driver.get("http://localhost:8090/signup");
	driver.findElement(By.xpath("//*[contains(text(),'Regístrate')]")).click();
	driver.findElement(By.name("email")).click();
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("123@email.com");
	driver.findElement(By.name("name")).click();
	driver.findElement(By.name("name")).clear();
	driver.findElement(By.name("name")).sendKeys("Carmen");
	driver.findElement(By.name("surname")).click();
	driver.findElement(By.name("surname")).clear();
	driver.findElement(By.name("surname")).sendKeys("Perez Almonte");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.name("passwordConfirm")).click();
	driver.findElement(By.name("passwordConfirm")).clear();
	driver.findElement(By.name("passwordConfirm")).sendKeys("abcde");
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();

	// El usuario vuelve a la página de registro
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Regístrate')]"));
	assertTrue(list.size() > 0);

	// Se muestra el mensaje del error.
	List<WebElement> list1 = driver.findElements(By.xpath("//*[contains(text(),'Las contraseñas no coinciden')]"));
	assertTrue(list1.size() > 0);
    }

    @Test
    public void testP04() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.linkText("Regístrate")).click();
	driver.findElement(By.name("email")).click();
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("user1@email.com");
	driver.findElement(By.name("name")).clear();
	driver.findElement(By.name("name")).sendKeys("Carmen");
	driver.findElement(By.name("surname")).clear();
	driver.findElement(By.name("surname")).sendKeys("Perez Almonte");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.name("passwordConfirm")).clear();
	driver.findElement(By.name("passwordConfirm")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Registrarse')]")).click();

	// El usuario vuelve a la página de registro
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Regístrate')]"));
	assertTrue(list.size() > 0);

	// Se muestra el mensaje del error.
	List<WebElement> list1 = driver
		.findElements(By.xpath("//*[contains(text(),'Este Email ya está registrado.')]"));
	assertTrue(list1.size() > 0);
    }

    @Test
    public void testP05() {

	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("admin@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// El usuario pasa a la página de inicio.
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Usuario autenticado como:\t')]"));
	assertTrue(list.size() > 0);

	// Usuario se deconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();

    }

    @Test
    public void testP06() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("user1@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// El usuario pasa a la página de inicio.
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Usuario autenticado como:\t')]"));
	assertTrue(list.size() > 0);

	// Usuario se deconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
    }

    @Test
    public void testP07() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// Todos los campos vacios
	// El usuario sigue en la página de login
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Email:')]"));
	assertTrue(list.size() > 0);
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("user1@email.com");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// Contraseña vacia
	// El usuario sigue en la página de login
	list = driver.findElements(By.xpath("//*[contains(text(),'Email:')]"));
	assertTrue(list.size() > 0);
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// Usuario vacio
	// El usuario sigue en la página de login
	list = driver.findElements(By.xpath("//*[contains(text(),'Email:')]"));
	assertTrue(list.size() > 0);
    }

    @Test
    public void testP08() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("user1@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("abcde");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// El usuario sigue en la página de login
	// Se muestra el error
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'No se pudo iniciar sesión')]"));
	assertTrue(list.size() > 0);
    }

    @Test
    public void testP09() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("123@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// El usuario sigue en la página de login
	// Se muestra el error
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'No se pudo iniciar sesión')]"));
	assertTrue(list.size() > 0);
    }

    @Test
    public void testP10() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("user1@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	// El usuario pasa a la página de inicio.
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Usuario autenticado como:\t')]"));
	assertTrue(list.size() > 0);

	// Usuario se deconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
	// El usuario regresa a la pagina de login
	list = driver.findElements(By.xpath("//*[contains(text(),'Login')]"));
	assertTrue(list.size() > 0);

    }

    @Test
    public void testP11() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("user1@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("12345");
	// El usuario no tiene la opción de desconectarse
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Desconectarse')]"));
	assertTrue(list.size() == 0);
	// El usuario se autentifica
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();

	// El usuario tiene la opción de desconectarse
	list = driver.findElements(By.xpath("//*[contains(text(),'Desconectar')]"));
	assertTrue(list.size() > 0);
	// Usuario se deconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
	// La opción de desconectarse vuelve a no aparecer para el usuario
	list = driver.findElements(By.xpath("//*[contains(text(),'Desconectarse')]"));
	assertTrue(list.size() == 0);
    }

    @Test
    public void testP12() {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("admin@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	// El usuario se autentifica
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Gestionar usuarios')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Ver usuarios')]")).click();

	// Comprobamos que están todos los 5 usuarios
	// El usuario administrador no aparece en el listado
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'user1@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user2@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user3@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user4@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user5@email.com')]"));
	assertTrue(list.size() > 0);

	// El administrador se desconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
    }

    @Test
    public void testP13() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("admin@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	// El usuario se autentifica
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Gestionar usuarios')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Ver usuarios')]")).click();

	// Comprobamos que está el primer usuario
	// El usuario administrador no aparece en el listado
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'user1@email.com')]"));
	assertTrue(list.size() > 0);
	// Lo eliminamos
	driver.findElement(By.name("idsUser")).click();
	// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
	// normalize-space(.)='User5'])[2]/following::input[2]")).click();
	driver.findElement(By.name("delete")).click();

	// Comprobamos que el primer usuario ya no aparece
	list = driver.findElements(By.xpath("//*[contains(text(),'user1@email.com')]"));
	assertTrue(list.size() == 0);

	// Se muestra el mensaje de borrado correcto
	list = driver.findElements(By.xpath("//*[contains(text(),'Se eliminó correctamente el usuario')]"));
	assertTrue(list.size() > 0);

	// El administrador se desconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
    }

    @Test
    public void testP14() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("admin@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	// El usuario se autentifica
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Gestionar usuarios')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Ver usuarios')]")).click();

	// Comprobamos que está el último usuario
	// El usuario administrador no aparece en el listado
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'user5@email.com')]"));
	assertTrue(list.size() > 0);
	driver.findElement(
		By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='User5'])[2]/following::input[1]"))
		.click();
	driver.findElement(By.name("delete")).click();

	// Comprobamos que el primer usuario ya no aparece
	list = driver.findElements(By.xpath("//*[contains(text(),'user5@email.com')]"));
	assertTrue(list.size() == 0);

	// Se muestra el mensaje de borrado correcto
	list = driver.findElements(By.xpath("//*[contains(text(),'Se eliminó correctamente el usuario')]"));
	assertTrue(list.size() > 0);

	// El administrador se desconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
    }

    @Test
    public void testP15() throws Exception {
	driver.get("http://localhost:8090/login");
	driver.findElement(By.name("username")).click();
	driver.findElement(By.name("username")).clear();
	driver.findElement(By.name("username")).sendKeys("admin@email.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("admin");
	// El usuario se autentifica
	driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Gestionar usuarios')]")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Ver usuarios')]")).click();

	// Comprobamos que está los 3 usuarios que vamos a borrar
	// El usuario administrador no aparece en el listado
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'user1@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user2@email.com')]"));
	assertTrue(list.size() > 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user3@email.com')]"));
	assertTrue(list.size() > 0);

	// Eliminamos los tres usuarios
	driver.findElement(By.name("idsUser")).click();
	driver.findElement(
		By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='User2'])[2]/following::input[1]"))
		.click();
	driver.findElement(
		By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='User3'])[2]/following::input[1]"))
		.click();
	driver.findElement(By.name("delete")).click();

	// Comprobamos que ya no están los usuarios

	list = driver.findElements(By.xpath("//*[contains(text(),'user1@email.com')]"));
	assertTrue(list.size() == 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user2@email.com')]"));
	assertTrue(list.size() == 0);
	list = driver.findElements(By.xpath("//*[contains(text(),'user3@email.com')]"));
	assertTrue(list.size() == 0);

	// Se muestra el mensaje de borrado correcto
	list = driver.findElements(By.xpath("//*[contains(text(),'Se eliminó correctamente el usuario')]"));
	assertTrue(list.size() > 0);
	// El administrador se desconecta
	driver.findElement(By.xpath("//*[contains(text(),'Desconectar')]")).click();
    }

}
