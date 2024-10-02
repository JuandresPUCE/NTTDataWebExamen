@examen
Feature: Login QALab
    @loginQALab-test
    Scenario Outline: Login QALab con usuario y contraseña
        # ambos escenarios el invalido y el válido
        Given dado la página de QALab store
        When ingreso usuario "<usuario>" y clave "<clave>"
        Then se muestra la pagina bienvenida
        # validar codigo de estado
        And se muestra el mensaje "<mensaje>"


        Examples:
            | usuario | clave | mensaje |
            | usuarioincorrecto@wasa.com | clave incorrecta | Error de autenticación.|
            | jaaviles@puce.edu.ec | 9KRtSMwe.TzW.z! | Productos Destacados |



    @categoria-inexistente-test
    Scenario: Buscar una categoría inexistente
        Given dado la página de QALab store
        When ingreso usuario "jaaviles@puce.edu.ec" y clave "9KRtSMwe.TzW.z!"
        Then se muestra la pagina bienvenida
        And busco la categoría "Autos"
        And se muestra el mensaje "No se han encontrado coincidencias con tu búsqueda"



    @categoria-existente-test
    Scenario: Validación del precio de un producto
        Given dado la página de QALab store
        When ingreso usuario "jaaviles@puce.edu.ec" y clave "9KRtSMwe.TzW.z!"
        # navegar
        And navego a la categoria "Clothes" y subcategoria "Men"
        # agregar al carrito
        And agrego 2 unidades del primer producto al carrito
        #validar
        Then valido en el popup la confirmación del producto agregado
        And valido en el popup que el monto total sea calculado correctamente
        When finalizo la compra
        Then valido el titulo de la pagina del carrito
        And vuelvo a validar el calculo de precios en el carrito




