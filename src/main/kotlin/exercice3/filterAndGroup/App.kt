package exercice3.filterAndGroup

import exercice3.filterAndGroup.AddStatistics.averageAmountByCategory
import exercice3.filterAndGroup.AddStatistics.lowestAndHighestAmountByCategory
import exercice3.filterAndGroup.AddStatistics.numberOfTransactionsByCategory
import exercice3.filterAndGroup.TransactionExtensions.filterByAmount
import exercice3.filterAndGroup.TransactionExtensions.totalByCategory

fun main() {
    val transactionList: List<Transaction> = listOf(
        Transaction(1, 500.0, Category.ALIMENTACION),
        Transaction(2, 1500.0, Category.ENTRETENIMIENTO),
        Transaction(3, 2000.0, Category.ALQUILER),
        Transaction(4, 750.0, Category.TRANSPORTE),
        Transaction(5, 3000.0, Category.ALQUILER),
        Transaction(6, 1200.0, Category.EDUCACION),
        Transaction(7, 250.0, Category.TRANSPORTE),
        Transaction(8, 400.0, Category.SALUD),
        Transaction(9, 800.0, Category.ALIMENTACION),
        Transaction(10, 1300.0, Category.ENTRETENIMIENTO),
        Transaction(11, 200.0, Category.SALUD),
        Transaction(12, 4500.0, Category.VIAJES),
        Transaction(13, 100.0, Category.REGALOS),
        Transaction(14, 2200.0, Category.EDUCACION),
        Transaction(15, 50.0, Category.REGALOS),
        Transaction(16, 1800.0, Category.VIAJES),
        Transaction(17, 1700.0, Category.ALIMENTACION),
        Transaction(18, 900.0, Category.TRANSPORTE),
        Transaction(19, 700.0, Category.SALUD),
        Transaction(20, 3400.0, Category.ALQUILER),
        Transaction(21, 1100.0, Category.ENTRETENIMIENTO),
        Transaction(22, 5000.0, Category.VIAJES),
        Transaction(23, 120.0, Category.REGALOS),
        Transaction(24, 2100.0, Category.EDUCACION),
        Transaction(25, 600.0, Category.ALIMENTACION)
    )

    println("Filtered by amount")
    println("------------------")
    transactionList.filterByAmount(3000.0).forEach {
        println(it)
    }

    println("\n\nTotal per category")
    println("--------------------")
    transactionList.totalByCategory().forEach {
        println(it)
    }

    println("\n\nAverage per category")
    println("----------------------")
    transactionList.averageAmountByCategory().forEach {
        println(it)
    }

    println("\n\nLowest and highest amount per category")
    println("------------------------------------------")
    transactionList.lowestAndHighestAmountByCategory().forEach { (category, transactions) ->
       println("Category: $category, Lowest: ${transactions.first?.amount}, Highest: ${transactions.second?.amount}")
    }

    println("\n\nNumber of transactions per category")
    println("---------------------------------------")
    transactionList.numberOfTransactionsByCategory().forEach { (category, numOfTransactions) ->
        println("Category: $category --- Total transactions: $numOfTransactions")
    }
}



