package de.pizzabot.display

import de.pizzabot.basetypes.Preparable
import java.io.OutputStream
import java.io.PrintStream

class StreamDisplay(outputStream: OutputStream) : Display {
    val out = PrintStream(outputStream)

    override fun display(line: String) {
        out.println(line)
    }

    override fun displayMenuItem(name: String, price: Double) {
        val paddedName = String.format("%1$-20s", name)
        out.println("-- $paddedName | $price")
    }

    override fun displayPreparationStep(preparable: Preparable) {
        out.println("--> ${preparable.getPreparationStepDescription()}")
        Thread.sleep(500)
    }

    override fun displayBillLine(item: String, price: Double) {
        val paddedName = String.format("%1$-20s", item)
        out.println("## $paddedName | $price")
    }
}