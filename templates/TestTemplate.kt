package aoc2026

/**
 * Template for test files.
 * Each day should have a TestDayN.kt file in its folder.
 * 
 * To use this template:
 * 1. Copy this file to dayN/TestDayN.kt
 * 2. Rename the class to TestDayN (e.g., TestDay1, TestDay2, etc.)
 * 3. Implement the test() method with your assertions
 * 4. Use FileReader.readLines(day, useExample=true) to read example input
 * 
 * Example:
 * 
 * class TestDay1 : DayTest() {
 *     override fun test() {
 *         // Test part 1 with example input
 *         testPart1(1, "42")
 *         
 *         // Test part 2 with example input
 *         testPart2(1, "123")
 *         
 *         // Or test with custom input
 *         val customInput = listOf("abc", "def")
 *         val day = Day1()
 *         assertEquals("expected", day.part1(customInput))
 *     }
 * }
 */

interface DayTest {
    fun test()
    
    /**
     * Helper to test part 1 with expected result.
     */
    fun testPart1(dayNumber: Int, expected: String) {
        val input = FileReader.readLines(dayNumber, useExample = true)
        val dayClass = Class.forName("aoc2026.Day$dayNumber")
        val dayInstance = dayClass.getDeclaredConstructor().newInstance() as aoc2026.Day
        
        val result = dayInstance.part1(input)
        if (result != expected) {
            throw AssertionError("Day $dayNumber, Part 1 failed. Expected: $expected, Got: $result")
        }
        println("✓ Day $dayNumber, Part 1 passed")
    }
    
    /**
     * Helper to test part 2 with expected result.
     */
    fun testPart2(dayNumber: Int, expected: String) {
        val input = FileReader.readLines(dayNumber, useExample = true)
        val dayClass = Class.forName("aoc2026.Day$dayNumber")
        val dayInstance = dayClass.getDeclaredConstructor().newInstance() as aoc2026.Day
        
        val result = dayInstance.part2(input)
        if (result != expected) {
            throw AssertionError("Day $dayNumber, Part 2 failed. Expected: $expected, Got: $result")
        }
        println("✓ Day $dayNumber, Part 2 passed")
    }
}
