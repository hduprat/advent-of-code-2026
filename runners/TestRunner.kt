package aoc2026

/**
 * Test Runner for Advent of Code solutions.
 * 
 * Usage:
 *   kotlinc TestRunner.kt FileReader.kt DayTemplate.kt -include-runtime -d tests.jar
 *   java -jar tests.jar <day>
 * 
 * Or use the test.sh script:
 *   ./test.sh 1  # Runs tests for day 1
 */

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: TestRunner <day>")
        println("  day: Day number (1-25)")
        println()
        println("Runs the test file for the specified day.")
        println("Expected test file: day<day>/TestDay<day>.kt")
        return
    }
    
    val dayNumber = try {
        args[0].toInt()
    } catch (e: NumberFormatException) {
        println("Error: Day must be a number")
        return
    }
    
    // Validate day range
    require(dayNumber in 1..25) { "Day must be between 1 and 25" }
    
    // Try to load and run the test class
    val testClassName = "aoc2026.TestDay$dayNumber"
    val testClass = try {
        Class.forName(testClassName)
    } catch (e: ClassNotFoundException) {
        println("No test class found for Day $dayNumber")
        println("Expected: $testClassName in day$dayNumber/TestDay$dayNumber.kt")
        return
    }
    
    // Get the test method
    val testMethod = try {
        testClass.getMethod("test")
    } catch (e: NoSuchMethodException) {
        println("Error: Test class $testClassName must have a test() method")
        return
    }
    
    try {
        // Run the test
        val testInstance = testClass.getDeclaredConstructor().newInstance()
        println("Running tests for Day $dayNumber...")
        testMethod.invoke(testInstance)
        println("${Colors.GREEN}All tests passed!${Colors.NC}")
    } catch (e: Exception) {
        println("${Colors.RED}Test failed: ${e.message}${Colors.NC}")
        e.printStackTrace()
    }
}
