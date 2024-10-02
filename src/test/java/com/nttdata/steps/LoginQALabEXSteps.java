package com.nttdata.steps;

import com.nttdata.page.QALabExPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginQALabEXSteps {
    WebDriver driver;
    private int codigoRespuesta;

    public LoginQALabEXSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void escribirUsuario(String user) {
        WebElement usuarioInput = driver.findElement(QALabExPage.userInput);
        usuarioInput.sendKeys(user);
    }

    public void escribirClave(String password) {
        WebElement claveInput = driver.findElement(QALabExPage.passInput);
        claveInput.sendKeys(password);
    }

    public void clickIngresar() {
        WebElement ingresarButton = driver.findElement(QALabExPage.loginButton);
        ingresarButton.click();
    }

    public void escribirBusqueda(String busqueda) {
        WebElement busquedaInput = driver.findElement(QALabExPage.searchInput);
        busquedaInput.sendKeys(busqueda);
        busquedaInput.sendKeys(Keys.ENTER);
    }

    public void seleccionarSubCategoria(String subcategoria) {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement categoryElement = driver.findElement(QALabExPage.clothesCategory);
        actions.moveToElement(categoryElement).perform();


        WebElement subCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(subcategoria)));

        subCategoryElement.click();
    }

    public void agregarPrimerProductoAlCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement primerProductoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.primerProducto));
        primerProductoElement.click();

    }

    public void establecerCantidad(int cantidad) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement cantidadInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.cantidadInput));
        cantidadInputElement.clear();
        cantidadInputElement.sendKeys(String.valueOf(cantidad));
    }

    public void enviarAlCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement agregarCarritoButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.agregarCarritoButton));
        agregarCarritoButtonElement.click();
    }

    public void validarPopupConfirmacionProducto() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.modalTitle));


        String expectedTitle = "Producto añadido correctamente a su carrito de compra";
        String actualTitle = driver.findElement(QALabExPage.modalTitle).getAttribute("innerText").trim();
        assertTrue(actualTitle.contains("Producto añadido correctamente a su carrito de compra"));
    }

       public double[] obtenerValoresDelPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        try {
            Thread.sleep(2000); // Espera que el modal se abra (quitar en producción)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Esperar a que el elemento de cantidad sea visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.productQuantity));

        // Obtener la cantidad del producto
        String quantityText = driver.findElement(QALabExPage.productQuantity).getText();
        int quantity = Integer.parseInt(quantityText.replaceAll("[^0-9]", "")); // Extraer solo el número

        // Obtener el precio unitario
        String priceText = driver.findElement(QALabExPage.productPrice).getText();
        double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", "")); // Extraer solo el número

        // Obtener el subtotal del modal
        String subtotalText = driver.findElement(QALabExPage.subtotal).getText();
        double subtotal = Double.parseDouble(subtotalText.replaceAll("[^0-9.]", "")); // Extraer solo el número

        return new double[]{quantity, price, subtotal};
    }

    public void finalizarCompra() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));





        wait.until(ExpectedConditions.visibilityOfElementLocated(QALabExPage.finalizarCompraButton));

        try {

            driver.findElement(QALabExPage.finalizarCompraButton).click();
        } catch (TimeoutException e) {
            System.out.println("El botón 'Finalizar compra' no se pudo encontrar: " + e.getMessage());

        }
    }

    public double[] obtenerValoresDelCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Aumentar el tiempo de espera

        // Esperar a que el elemento de cantidad sea visible
        WebElement cantidadElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.js-cart-line-product-quantity")));
        int quantity = Integer.parseInt(cantidadElemento.getAttribute("value")); // Obtener la cantidad

        // Obtener el precio unitario
        WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.price")));
        String priceText = precioElemento.getText().replace("S/", "").replace(",", ".").trim(); // Eliminar "S/" y normalizar el texto del precio
        double price = Double.parseDouble(priceText); // Convertir a double

        // Esperar un poco antes de buscar el subtotal
        try {
            Thread.sleep(2000); // Espera que cualquier animación se complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Obtener el subtotal mostrado
        WebElement subtotalElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cart-line-info span.subtotal"))); // Cambiar a CSS Selector
        String subtotalText = subtotalElemento.getText().replace("S/", "").replace(",", ".").trim(); // Eliminar "S/" y normalizar el texto del subtotal
        double subtotal = Double.parseDouble(subtotalText); // Convertir a double

        return new double[]{quantity, price, subtotal}; // Retornar un arreglo con los valores
    }















}
