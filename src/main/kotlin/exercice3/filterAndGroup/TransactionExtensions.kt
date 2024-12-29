package exercice3.filterAndGroup

import java.math.BigDecimal
import java.math.RoundingMode

object TransactionExtensions {
    fun List<Transaction>.filterByAmount(amount: Double): List<Transaction> {
        return this.filter { it.amount > amount }
    }

    fun List<Transaction>.groupByCategory(): Map<Category, List<Transaction>> {
        return this.groupBy { it.category }
    }

    fun List<Transaction>.totalByCategory(): Map<Category, Double> {
        return this.groupBy { it.category }.mapValues { (_, transactions) ->
            transactions.sumOf { it.amount }
        }
    }
}

object AddStatistics {
    fun List<Transaction>.averageAmountByCategory(): Map<Category, Double> {
        return this.groupBy { it.category }.mapValues { (_, transactions)  ->
            BigDecimal(transactions.sumOf { it.amount } / transactions.size)
                .setScale(2, RoundingMode.HALF_EVEN)
                .toDouble()
        }
    }

    fun List<Transaction>.lowestAndHighestAmountByCategory(): Map<Category, Pair<Transaction?, Transaction?>> {
        return this.groupBy { it.category }.mapValues { (_, transactions) ->
            val lowest = transactions.minByOrNull { it.amount }
            val highest = transactions.maxByOrNull { it.amount }
            Pair(lowest, highest)
        }
    }

    fun List<Transaction>.numberOfTransactionsByCategory(): Map<Category, Int> {
        return this.groupBy { it.category }.mapValues { (_, transactions) ->
            transactions.count()
        }
    }
}

object OrderAndLimit {
    
}

