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

    fun readListToAgesMap(input: List<String>): Map<Int, Long?> {
        var ages = mutableListOf<Int>()

        for (line in input) {
            ages.addAll(line.split(",").map { i -> i.toInt()})
        }

        return ages.groupBy { i -> i }.mapValues { (_, v) -> v.size.toLong() }
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

    fun propagateManyFishForDays(ages: MutableList<Int>, days: Int): MutableList<MutableList<Int>> {
        var allAges = MutableList<MutableList<Int>>(1){ ages }

            for (d in 1..days) {
                for (i in 0 until allAges.size) {
                    allAges[i] = propagateFish(allAges[i])
                    val ageSize = allAges[i].size

                    if (ageSize > 1E6) {
                        val sizeHalf = ageSize / 2
                        val halfList = allAges[i].slice(0 until sizeHalf).toMutableList()
                        allAges.add(allAges[i].slice(sizeHalf until ageSize).toMutableList())
                        allAges[i] = halfList
                    }
                }
            }

        return allAges
    }

    fun propagateManyFishForDaysDict(ages: Map<Int, Long?>, days: Int): Map<Int, Long?> {
        var agesCopy = ages.toMutableMap()

        for (d in 1..days) {
            var newAges = agesCopy.mapKeys { (k, _) -> k - 1 }.toMutableMap()
            val newFish = newAges[-1]
            newAges[restartAge] = (newAges[restartAge] ?: 0) + (newFish ?: 0)
            newAges[8] = newFish
            newAges.remove(-1)
            agesCopy = newAges
        }

        return agesCopy
    }

    fun part1(input: List<String>, days: Int): Int {
        var ages = readListToAges(input)

        ages = propagateFishForDays(ages, days)

        return ages.size
    }

    fun part2(input: List<String>, days: Int): Long {
        var ages = readListToAgesMap(input)

        val allAges = propagateManyFishForDaysDict(ages, days)

        return allAges.values
            .filterNotNull()
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput, 18) == 26)
    check(part1(testInput, 18) == 26)
    check(part1(testInput, 80) == 5934)
    check(part2(testInput, 18) == 26.toLong())
    check(part2(testInput, 80) == 5934.toLong())
    check(part2(testInput, 256) == 26984457539.toLong())

    val input = readInput("Day06")
    println("Part 1: " + part1(input, 80))
    println("Part 2: " + part2(input, 256))
}
