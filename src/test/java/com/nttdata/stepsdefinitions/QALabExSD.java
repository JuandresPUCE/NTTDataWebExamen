package com.nttdata.stepsdefinitions;

import com.nttdata.page.QALabExPage;
import com.nttdata.steps.LoginQALabEXSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QALabExSD {
    private WebDriver driver;


    @Given("dado la página de QALab store")
    public void dadoLaPáginaDeQALabStore() {
        driver = getDriver();
        //indicar pagina
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion");
        screenShot();
    }



    @Then("se muestra la pagina bienvenida")
    public void seMuestraLaPaginaBienvenida() {
    }

    @When("ingreso usuario {string} y clave {string}")
    public void ingresoUsuarioYClave(String user, String password) {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        loginQALabEXSteps.escribirUsuario(user);
        loginQALabEXSteps.escribirClave(password);
        loginQALabEXSteps.clickIngresar();
        screenShot();
    }

    /*
    @And("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String mensajeEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // t segundos
        WebElement mensajeElemento;

        try {
            // error identificado
            mensajeElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class, 'alert alert-danger') and text()='" + mensajeEsperado + "']")));

            // mensaje log in correcto
            String mensajeActual = mensajeElemento.getText().trim().toLowerCase(); // Convertir a minúsculas
            if (!mensajeActual.equals(mensajeEsperado.toLowerCase())) {
                throw new AssertionError("El mensaje de error actual '" + mensajeActual + "' no coincide con el esperado '" + mensajeEsperado + "'");
            }
        } catch (TimeoutException e) {

            try {
                mensajeElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), '" + mensajeEsperado + "')]")));

                // Obtener y normalizar el texto del elemento
                String mensajeActual = mensajeElemento.getText().trim().toLowerCase(); // Convertir a minúsculas
                if (!mensajeActual.equals(mensajeEsperado.toLowerCase())) {
                    throw new AssertionError("El mensaje de éxito actual '" + mensajeActual + "' no coincide con el esperado '" + mensajeEsperado + "'");
                }
            } catch (TimeoutException ex) {
                throw new AssertionError("No se encontró el mensaje esperado: '" + mensajeEsperado + "'");
            }
        }
    }
    */
    /*
    @And("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String mensajeEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Espera de 15 segundos
        String mensajeActual = null;

        // Intentar encontrar el mensaje de error
        try {
            mensajeActual = buscarMensaje(wait, By.xpath("//li[contains(@class, 'alert alert-danger')]"));
        } catch (TimeoutException e) {
            // Intentar encontrar el mensaje de éxito
            try {
                mensajeActual = buscarMensaje(wait, By.xpath("//h2[contains(text(), '" + mensajeEsperado + "')]"));
            } catch (TimeoutException ex) {
                // Intentar buscar el mensaje en un div o span (o cualquier otro contenedor)
                try {
                    mensajeActual = buscarMensaje(wait, By.xpath("//*[contains(text(), '" + mensajeEsperado + "')]")); // Buscar en cualquier lugar
                } catch (TimeoutException innerEx) {
                    throw new AssertionError("No se encontró el mensaje esperado: '" + mensajeEsperado + "'");
                }
            }
        }

        // Normalizar y comparar el mensaje
        if (mensajeActual != null && !mensajeActual.equals(mensajeEsperado.toLowerCase())) {
            throw new AssertionError("El mensaje actual '" + mensajeActual + "' no coincide con el esperado '" + mensajeEsperado + "'");
        }
    }
    */

    @And("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String mensajeEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Espera de 15 segundos
        String mensajeActual = null;

        // Intentar encontrar el mensaje de error
        try {
            mensajeActual = buscarMensaje(wait, By.xpath("//li[contains(@class, 'alert alert-danger')]"));
        } catch (TimeoutException e) {
            // Intentar encontrar el mensaje de éxito
            try {
                mensajeActual = buscarMensaje(wait, By.xpath("//h2[contains(text(), '" + mensajeEsperado + "')]"));
            } catch (TimeoutException ex) {
                // Intentar buscar el mensaje en un div o span (o cualquier otro contenedor)
                try {
                    mensajeActual = buscarMensaje(wait, By.xpath("//*[contains(text(), '" + mensajeEsperado + "')]")); // Buscar en cualquier lugar
                } catch (TimeoutException innerEx) {
                    // Nuevo caso para buscar "Carrito"
                    try {
                        mensajeActual = buscarMensaje(wait, By.xpath("//h1[contains(text(), 'Carrito')]"));
                    } catch (TimeoutException finalEx) {
                        throw new AssertionError("No se encontró el mensaje esperado: '" + mensajeEsperado + "' o 'Carrito'");
                    }
                }
            }
        }

        // Normalizar y comparar el mensaje
        if (mensajeActual != null && !mensajeActual.equals(mensajeEsperado.toLowerCase())) {
            throw new AssertionError("El mensaje actual '" + mensajeActual + "' no coincide con el esperado '" + mensajeEsperado + "'");
        }
    }

    private String buscarMensaje(WebDriverWait wait, By locator) {
        WebElement mensajeElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return mensajeElemento.getText().trim().toLowerCase(); // Convertir a minúsculas
    }



    @And("busco la categoría {string}")
    public void buscoLaCategoría(String busqueda) {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        loginQALabEXSteps.escribirBusqueda(busqueda);
        screenShot();
    }

    @And("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Encuentra y despliega la categoría "Clothes"
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.clothesCategory));
        actions.moveToElement(categoryElement).perform();

        // Espera a que la subcategoría "Men" sea visible y haz clic en ella
        WebElement subCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.menSubCategory));
        subCategoryElement.click();


    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        loginQALabEXSteps.agregarPrimerProductoAlCarrito();
        loginQALabEXSteps.establecerCantidad(unidades);
        loginQALabEXSteps.enviarAlCarrito();
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        loginQALabEXSteps.validarPopupConfirmacionProducto();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        LoginQALabEXSteps steps = new LoginQALabEXSteps(driver);
        double[] valores = steps.obtenerValoresDelPopup();
        int quantity = (int) valores[0];
        double price = valores[1];
        double subtotal = valores[2];

        // Calcular el subtotal
        double calculatedSubtotal = quantity * price;

        // Comparar el subtotal calculado con el subtotal mostrado
        assertEquals("El subtotal calculado no coincide con el subtotal mostrado.", calculatedSubtotal, subtotal, 0.01);
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);
        loginQALabEXSteps.finalizarCompra();
        screenShot();

    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        seMuestraElMensaje("Carrito");
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        /*
        LoginQALabEXSteps loginQALabEXSteps = new LoginQALabEXSteps(driver);

        double[] valores = loginQALabEXSteps.obtenerValoresDelCarrito();
        int quantity = (int) valores[0];
        double price = valores[1];
        double subtotal = valores[2];


        double calculatedSubtotal = quantity * price;


        assertEquals("El subtotal calculado no coincide con el subtotal mostrado.", calculatedSubtotal, subtotal, 0.01);
        */

    }
}