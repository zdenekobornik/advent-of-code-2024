import kotlin.math.abs

fun main() {
    fun prepareData(input: List<String>): Pair<List<Int>, List<Int>> {
        val firstList = input.map { it.substringBefore("   ").toInt() }.sorted()
        val secondList = input.map { it.substringAfter("   ").toInt() }.sorted()
        return Pair(firstList, secondList)
    }

    fun part1(input: List<String>): Int {
        val (firstList, secondList) = prepareData(input)
        return firstList.zip(secondList) { first, second -> abs(first - second) }.sum()
    }

    fun part2(input: List<String>): Int {
        val (firstList, secondList) = prepareData(input)
        return firstList.sumOf { num -> num * secondList.count { it == num } }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
