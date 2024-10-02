package com.nttdata.page;

import org.openqa.selenium.By;

public class QALabExPage {
    public static By userInput = By.id("field-email");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");

    public static By searchInput = By.className("ui-autocomplete-input");

    /*public static final By clothesCategory = By.linkText("Clothes");
    public static final By menSubCategory = By.linkText("Men");*/
    public static final By clothesCategory = By.cssSelector("a.dropdown-item[href='https://qalab.bensg.com/store/pe/3-clothes']");
    public static final By menSubCategory = By.cssSelector("a.dropdown-item[href='https://qalab.bensg.com/store/pe/4-men']");

    public static final By primerProducto = By.cssSelector(".products .js-product:first-child");

    public static final By cantidadInput = By.id("quantity_wanted");
    public static final By agregarCarritoButton = By.cssSelector("button.add-to-cart");

    public static final By modalTitle = By.cssSelector("h4.modal-title");

    public static final By modalBody = By.cssSelector("div.modal-body");
    public static final By productQuantity = By.cssSelector("span.product-quantity strong");
    public static final By productPrice = By.cssSelector("p.product-price");
    public static final By subtotal = By.cssSelector("span.subtotal.value");

    /*public static final By finalizarCompraButton = By.cssSelector("a.btn.btn-primary");*/
    public static final By finalizarCompraButton = By.xpath("//a[contains(@class, 'btn btn-primary') and contains(., 'Finalizar compra')]");



}

