fun main() {
    fun part1(input: String): Int {
        return "mul\\((\\d+),(\\d+)\\)".toRegex()
            .findAll(input)
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    fun part2(input: String): Int {
        var enabled = true
        return "mul\\((\\d+),(\\d+)\\)|don\'t\\(\\)|do\\(\\)".toRegex()
            .findAll(input)
            .filter {
                enabled = when (it.value) {
                    "don't()" -> false
                    "do()" -> true
                    else -> return@filter enabled
                }
                return@filter false
            }
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    val testInput = readInput("Day03_test").joinToString(separator = "")
    val testInput2 = readInput("Day03_test2").joinToString(separator = "")
    check(part1(testInput) == 161)
    check(part2(testInput2) == 48)

    val input = readInput("Day03").joinToString(separator = "")
    part1(input).println()
    part2(input).println()
}
