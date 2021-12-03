fun main() {
    fun getInput(input: List<String>): List<Pair<String, Int>> {
        val strings = input.map {s -> s.split(' ')}
        return strings.map { s -> Pair<String, Int>(s[0], s[1].toInt()) }
    }

    fun instructionToCoordinates(instr: Pair<String, Int>): Pair<Int, Int> {
        return when (instr.first.lowercase()) {
            "forward"   -> Pair(instr.second, 0)
            "up"        -> Pair(0, -instr.second)
            "down"      -> Pair(0, instr.second)
            else        -> Pair(0, 0)
        }
    }

    fun part1(input: List<String>): Int {
        val instructions = getInput(input)
        val coordinates = instructions.map { p -> instructionToCoordinates(p) }

        val total = coordinates.reduce { acc, p -> Pair(acc.first + p.first, acc.second + p.second) }

        return total.first * total.second
    }

    fun part2(input: List<String>): Int {
        val instructions = getInput(input)
        val coordinates = instructions.map { p -> instructionToCoordinates(p) }
        val coordinatesWithAim = coordinates.map { p -> Triple(p.first, p.second, 0) }

        val total = coordinatesWithAim.reduce {
            acc, p -> Triple(
                acc.first + p.first,
                acc.second + if (p.first > 0) p.first * acc.third else 0,
                acc.third + p.second
            )
        }

        return total.first * total.second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
