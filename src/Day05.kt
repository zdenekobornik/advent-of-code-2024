fun main() {
    fun prepareData(input: String): Pair<List<Pair<String, String>>, List<List<String>>> {
        val (rulesStr, pagesStr) = input.split("\n\n")
        val rules = rulesStr.lines().map { it.split('|').let { it[0] to it[1] } }
        val updates = pagesStr.lines().map { it.split(',') }
        return rules to updates
    }

    fun part1(input: String): Int {
        val (rules, updates) = prepareData(input)
        return updates.sumOf { update ->
            val invalidPages = mutableSetOf<String>()
            for (page in update) {
                if (page in invalidPages) return@sumOf 0
                invalidPages += rules.filter { it.second == page }.map { it.first }
            }
            update[update.size / 2].toInt()
        }
    }

    fun part2(input: String): Int {
        val (rules, updates) = prepareData(input)
        return updates
            .map { it.toMutableList() }
            .sumOf { update ->
                var swapped = false
                for (i in update.indices) {
                    for (j in i + 1 until update.size) {
                        if ((update[j] to update[i]) in rules) {
                            val temp = update[i]
                            update[i] = update[j]
                            update[j] = temp
                            swapped = true
                        }
                    }
                }
                if (swapped) update[update.size / 2].toInt() else 0
            }
    }

    val testInput = readRawInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readRawInput("Day05")
    part1(input).println()
    part2(input).println()
}
