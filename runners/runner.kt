package aoc2026

/**
 * Advent of Code 2026 Runner
 * 
 * Usage:
 *   kotlinc runner.kt -include-runtime -d runner.jar
 *   java -jar runner.jar <day> [part] [--example]
 * 
 * Or use the provided run.sh script:
 *   ./run.sh 1              # Runs both parts of day 1 with main input
 *   ./run.sh 1 1            # Runs only part 1 of day 1 with main input
 *   ./run.sh 1 2            # Runs only part 2 of day 1 with main input
 *   ./run.sh 1 --example    # Runs both parts of day 1 with example input
 *   ./run.sh 1 1 --example  # Runs only part 1 of day 1 with example input
 * 
 * Arguments:
 *   <day>      : The day number to run (1-25)
 *   [part]     : Optional. Specify 1 for part 1 only, 2 for part 2 only.
 *                If omitted, runs both parts.
 *   [--example] : Optional. Use input.example.txt instead of input.txt.
 *                Can be placed before or after the part argument.
 */

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("${Colors.CYAN}Usage: runner <day> [part] [--example]${Colors.NC}")
        println("  day       : Day number (1-25)")
        println("  part      : Optional. 1 for part 1 only, 2 for part 2 only.")
        println("  --example : Optional. Use input.example.txt instead of input.txt.")
        println()
        println("Examples:")
        println("  runner 1              # Runs both parts of day 1 with main input")
        println("  runner 1 1            # Runs only part 1 of day 1 with main input")
        println("  runner 1 --example    # Runs both parts of day 1 with example input")
        println("  runner 1 1 --example  # Runs only part 1 of day 1 with example input")
        return
    }
    
    val dayNumber: Int
    var partFilter: String? = null
    var useExample = false
    
    try {
        // Parse first argument as day
        dayNumber = args[0].toInt()
        
        // Parse remaining arguments
        for (i in 1 until args.size) {
            when (args[i]) {
                "--example" -> useExample = true
                else -> partFilter = args[i]
            }
        }
        
        // Validate day range
        require(dayNumber in 1..25) { "Day must be between 1 and 25" }
        
        // Load the day's class dynamically
        val dayClass: Class<*> = try {
            Class.forName("aoc2026.Day$dayNumber")
        } catch (e: ClassNotFoundException) {
            Class.forName("day$dayNumber.Day$dayNumber")
        }
        
        // Check if it implements the Day interface
        val dayInterface = Class.forName("aoc2026.Day")
        require(dayInterface.isAssignableFrom(dayClass)) { "Day$dayNumber does not implement the Day interface" }
        
        // Create instance and run
        @Suppress("UNCHECKED_CAST")
        val dayInstance = dayClass.getDeclaredConstructor().newInstance() as aoc2026.Day
        
        // Read input
        val input = FileReader.readLines(dayNumber, useExample)
        
        // Run the requested parts
        when (partFilter) {
            "1" -> runPart(dayInstance, dayNumber, 1, input, useExample)
            "2" -> runPart(dayInstance, dayNumber, 2, input, useExample)
            null -> {
                runPart(dayInstance, dayNumber, 1, input, useExample)
                println()
                runPart(dayInstance, dayNumber, 2, input, useExample)
            }
            else -> throw IllegalArgumentException("Part must be 1 or 2")
        }
        
    } catch (e: NumberFormatException) {
        println("${Colors.RED}Error: Day must be a number${Colors.NC}")
    } catch (e: IllegalArgumentException) {
        println("${Colors.RED}Error: ${e.message}${Colors.NC}")
    } catch (e: ClassNotFoundException) {
        println("${Colors.RED}Error: Could not find Day${args[0]} class${Colors.NC}")
        println("Make sure day${args[0]}/main.kt exists and implements the Day interface")
    } catch (e: Exception) {
        println("${Colors.RED}Error running day: ${e.message}${Colors.NC}")
        e.printStackTrace()
    }
}

private fun runPart(day: aoc2026.Day, dayNumber: Int, part: Int, input: List<String>, useExample: Boolean) {
    val inputLabel = if (useExample) "Example" else "Main"
    val startTime = System.currentTimeMillis()
    val result = when (part) {
        1 -> day.part1(input)
        2 -> day.part2(input)
        else -> throw IllegalArgumentException("Invalid part number")
    }
    val elapsed = System.currentTimeMillis() - startTime
    
    println("${Colors.GREEN}Day $dayNumber, Part $part [$inputLabel]:${Colors.NC} $result")
    println("  ${Colors.YELLOW}Time:${Colors.NC} ${elapsed}ms")
}
