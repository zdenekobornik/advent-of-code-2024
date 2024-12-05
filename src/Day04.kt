fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.toList() }
        val possibleMoves = listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1,
        )
        val neededWord = listOf('X', 'M', 'A', 'S')
        var numberOfWords = 0

        for (row in grid.indices) {
            for (column in grid[row].indices) {
                moves@ for ((x, y) in possibleMoves) {
                    var currentXDelta = 0
                    var currentYDelta = 0
                    for (character in neededWord) {
                        if (row + currentYDelta !in grid.indices || column + currentXDelta !in grid[row].indices) {
                            continue@moves
                        }

                        if (grid[row + currentYDelta][column + currentXDelta] != character) {
                            continue@moves
                        }
                        currentYDelta += y
                        currentXDelta += x
                    }
                    numberOfWords++
                }
            }
        }

        return numberOfWords
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toList() }
        val codeToCheck = 'M'.code + 'S'.code

        return grid.indices.sumOf { row ->
            grid[row].indices.count { column ->
                if (grid[row][column] != 'A') {
                    return@count false
                }

                val topLeft = grid.getOrNull(row - 1)?.getOrNull(column - 1) ?: return@count false
                val topRight = grid.getOrNull(row - 1)?.getOrNull(column + 1) ?: return@count false
                val bottomRight = grid.getOrNull(row + 1)?.getOrNull(column + 1) ?: return@count false
                val bottomLeft = grid.getOrNull(row + 1)?.getOrNull(column - 1) ?: return@count false

                return@count topLeft.code + bottomRight.code == codeToCheck &&
                        topRight.code + bottomLeft.code == codeToCheck
            }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    val testInput2 = readInput("Day04_test2")
    println(part2(testInput2))
    check(part1(testInput) == 18)
    check(part2(testInput2) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
