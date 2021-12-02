fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input.map{ s -> s.toInt()}
        val bigger = numbers.flatMapIndexed{ index, n -> if (index > 0 && n > numbers[index-1]) listOf(1) else listOf(0) }

        return bigger.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
//    println(part2(input))
}
