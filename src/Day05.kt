fun main() {
    fun part1(input: String): Int {
        val (rulesStr, pagesStr) = input.split("\n\n")
        val rules = rulesStr.lines().map { it.split('|').let { it[0] to it[1] } }
        val updates = pagesStr.lines().map { it.split(',') }
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
        return -1
    }

    val testInput = readRawInput("Day05_test")
    check(part1(testInput) == 143)
    //check(part2(testInput) == -1)

    val input = readRawInput("Day05")
    part1(input).println()
    //part2(input).println()
}
