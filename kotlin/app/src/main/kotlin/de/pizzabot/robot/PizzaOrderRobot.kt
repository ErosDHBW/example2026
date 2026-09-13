package de.pizzabot.robot

import de.pizzabot.basetypes.Named
import de.pizzabot.basetypes.Order
import de.pizzabot.basetypes.Prices
import de.pizzabot.display.Display
import de.pizzabot.ingredients.Ingredient
import de.pizzabot.ingredients.Ingredients
import de.pizzabot.input.Choice
import de.pizzabot.input.Input
import de.pizzabot.pizzas.Dough
import de.pizzabot.pizzas.Doughs
import de.pizzabot.pizzas.Pizza
import de.pizzabot.pizzas.Pizzas

class PizzaOrderRobot(val display: Display, val input: Input, val prices: Prices) : MenuDisplay,
    OrderReveicer, PizzaBaker {
    val pizzaChoices = createPizzaChoices()
    val doughChoices = createDoughChoices()
    val extrasChoices = createExtrasChoices()

    override fun displayMenu() {
        display.display("Menu:")
        display.display("================")
        Pizzas.ALL.forEach { pizza ->
            display.displayMenuItem(pizza.getName())
        }
    }

    override fun collectOrders(): Collection<Order> {
        val pizzaChoice = input.prompt("Choose Pizza: ", pizzaChoices) ?: return listOf()
        val doughChoice = input.prompt("  Choose Dough: ", doughChoices) ?: return listOf()
        val extrasChoices = collectExtras()

        return listOf(Order(pizzaChoice.item, doughChoice.item, extrasChoices))
    }

    override fun bakePizza(pizzaOrder: Order) {
        val (pizza, dough, extras) = pizzaOrder
        display.display("Baking Pizza: ${pizza.getName()}")
        display.displayPreparationStep(dough)
        extras.forEach(display::displayPreparationStep)
    }

    fun displayBill(pizzaOrder: Order) {
        val (pizza, dough, extras) = pizzaOrder
        val total = calculateTotal(pizza, dough, extras)
        display.display("Time to pay:")
        display.display("================")
        display.displayBillLine(pizza.getName(), prices.getPriceFor(pizza))
        display.displayBillLine("  ${dough.getName()}", prices.getPriceFor(dough))
        extras.forEach { extra ->
            display.displayBillLine("  ${extra.getName()}", prices.getPriceFor(extra))
        }
        display.displayBillLine("-- TOTAL:", total)
    }

    private fun calculateTotal(pizza: Pizza, dough: Dough, extras: Collection<Ingredient>) =
        prices.getPriceFor(pizza) + prices.getPriceFor(dough) + extras.fold(0.0) { acc, e ->
            acc + prices.getPriceFor(e)
        }

    private fun collectExtras(): Collection<Ingredient> =
        collectMultiple("Choose an extra topping: ", extrasChoices)

    private fun <I> collectMultiple(prompt: String, choices: Collection<Choice<I>>): Collection<I> {
        val selection = mutableListOf<I>()
        var choice: Choice<I>?

        do {
            choice = input.prompt(prompt, choices)
            choice?.also { selection.add(it.item) }
        } while (choice != null)

        return selection.toList()
    }

    private fun createPizzaChoices(): Collection<Choice<Pizza>> =
        createChoicesCollection(Pizzas.ALL)

    private fun createExtrasChoices(): Collection<Choice<Ingredient>> =
        createChoicesCollection(Ingredients.ALL)

    private fun createDoughChoices(): Collection<Choice<Dough>> =
        createChoicesCollection(Doughs.ALL)

    private fun <I : Named> createChoicesCollection(items: Collection<I>): Collection<Choice<I>> {
        var choiceId = 1;
        return items.map { item ->
            Choice(
                id = "${choiceId++}",
                description = item.getName(),
                item = item
            )
        }
    }
}