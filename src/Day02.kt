import kotlin.math.abs

fun main() {
    fun checkDifference(diff: List<List<Int>>) = diff.all { (x, y) -> abs(x - y) in 1..3 } &&
            (diff.all { (x, y) -> x > y } || diff.all { (x, y) -> x < y })

    fun part1(input: List<String>): Int {
        return input.count { numbers ->
            val diff = numbers
                .split(" ")
                .map { it.toInt() }
                .windowed(2)

            checkDifference(diff)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { numbers ->
            val differences = numbers
                .split(" ")
                .map { it.toInt() }

            differences.indices.any { wrongIndex ->
                val diff = differences.toMutableList()
                    .apply { removeAt(wrongIndex) }
                    .windowed(2)
                checkDifference(diff)
            }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
