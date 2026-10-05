package de.pizzabot.robot

import de.pizzabot.basetypes.Order
import de.pizzabot.display.StreamDisplay
import de.pizzabot.ingredients.Mozzarella
import de.pizzabot.input.Input
import de.pizzabot.pizzas.CheeseCrust
import de.pizzabot.pizzas.Salami
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import java.io.ByteArrayOutputStream

class BillTest {

    // Step 6 (stretch): the bill for this order shows 12.969999999999999.
    // Write a test that expects "12.97" in the bill. Does it pass?
    // Hint: PizzaOrderRobotIntegrationTest shows how to capture what the robot prints.
    @Test
    fun `Bill for a Salami with Cheese Crust and Mozzarella has a total with two decimals`() {
        TODO("Write this test")
    }
}
