fun main() {
    val startAge = 9
    val restartAge = 6

    fun readListToAges(input: List<String>): MutableList<Int> {
        var ages = mutableListOf<Int>()

        for (line in input) {
            ages.addAll(line.split(",").map { i -> i.toInt()})
        }
        return ages
    }

    fun propagateFish(ages: List<Int>): MutableList<Int> {
        val newAges = MutableList(ages.filter { i -> i == 0 }.size) { startAge }
        val updatedAges = ages.plus(newAges).map { i -> if (i == 0) restartAge else i - 1  }
        return updatedAges.toMutableList()
    }

    fun propagateFishForDays(ages: MutableList<Int>, days: Int): MutableList<Int> {
        var ages = ages

        for (i in 1..days) {
            ages = propagateFish(ages)
        }

        return ages
    }

    fun part1(input: List<String>, days: Int): Int {
        var ages = readListToAges(input)

        ages = propagateFishForDays(ages, days)

        return ages.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput, 18) == 26)
    check(part1(testInput, 18) == 26)
    check(part1(testInput, 80) == 5934)

    val input = readInput("Day06")
    println("Part 1: " + part1(input, 80))
//    println("Part 2: " + part2(input))
}
