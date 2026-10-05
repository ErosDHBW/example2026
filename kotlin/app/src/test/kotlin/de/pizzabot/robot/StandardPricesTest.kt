package de.pizzabot.robot

import de.pizzabot.basetypes.Priceable
import de.pizzabot.ingredients.Pineapple
import de.pizzabot.pizzas.CheeseCrust
import de.pizzabot.pizzas.Margerita
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/*
 * Exercise: work through the tests from top to bottom.
 * Run them with ./gradlew test (on Windows: gradlew.bat test).
 */
class StandardPricesTest {

    // Step 1: a complete example. Run it, then change 8.29 to 8.30 and watch it fail.
    @Test
    fun `Get correct price for a Margerita Pizza`() {
        // arrange
        val pizza = Margerita()

        // act
        val price = StandardPrices.getPriceFor(pizza)

        // assert
        assertThat(price, equalTo(8.29))
    }

    // Step 2: arrange and act are done. Write the assertion: a cheese crust costs 2.59.
    @Test
    fun `Get correct price for a Cheese Crust`() {
        // arrange
        val dough = CheeseCrust()

        // act
        val price = StandardPrices.getPriceFor(dough)

        // assert
        TODO("Check that the price is 2.59")
    }

    // Step 3: only the structure is given. Pineapple costs 99.99.
    @Test
    fun `Get correct price for Pineapple`() {
        // arrange

        // act

        // assert
        TODO("Write this test")
    }

    // Step 4: things that are not a pizza, a dough or an ingredient have no price.
    // Hint: object : Priceable {} creates such a thing.
    // Hint: assertThrows<IllegalArgumentException> { ... } checks that the code inside throws that error.
    @Test
    fun `Unknown things have no price`() {
        TODO("Write this test")
    }
}
