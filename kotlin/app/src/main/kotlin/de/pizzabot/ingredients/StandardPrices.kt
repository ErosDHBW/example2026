package de.pizzabot.ingredients

import de.pizzabot.basetypes.Priceable
import de.pizzabot.basetypes.Prices
import de.pizzabot.basetypes.UnknownPriceableException

object StandardPrices : Prices {
    override fun getPriceFor(priceable: Priceable): Double {
        return if (priceable is Ingredient) {
            getPriceForIngredient(priceable)
        } else {
            throw UnknownPriceableException()
        }
    }

    private fun getPriceForIngredient(ingredient: Ingredient): Double = when (ingredient) {
        is Cheese -> 0.59
        is Ham -> 0.79
        is Mozzarella -> 0.79
        is Pineapple -> 99.99
        is Salami -> 0.69
        is TomatoSauce -> 0.29
        is Tuna -> 1.09
        else -> throw UnknownPriceableException()
    }
}