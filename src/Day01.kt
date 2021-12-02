fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input.map{ s -> s.toInt()}
        val bigger = numbers.flatMapIndexed{ index, n -> if (index > 0 && n > numbers[index-1]) listOf(1) else listOf(0) }

        return bigger.sum()
    }

    fun getSums(input: List<String>): List<Int> {
        val numbers = input.map{ s -> s.toInt()}
        return numbers.flatMapIndexed{
            index, n ->
            if (
                    index > 0 && index < numbers.size - 1
            ) listOf(n + numbers[index - 1] + numbers[index + 1])
            else listOf()
        }
    }

    fun part2(input: List<String>): Int {
        val sums = getSums(input)
        val bigger = sums.flatMapIndexed{ index, n -> if (index > 0 && n > sums[index-1]) listOf(1) else listOf(0) }

        return bigger.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
