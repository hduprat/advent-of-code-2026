package aoc2026

/**
 * Template interface that each day's main.kt should implement.
 * This provides a standardized way for the runner to execute day solutions.
 * 
 * To use this template:
 * 1. Copy this file to dayN/main.kt
 * 2. Rename the class to DayN (e.g., Day1, Day2, etc.)
 * 3. Implement the part1() and part2() functions
 * 4. Optionally override dayNumber if you want explicit control
 */

interface Day {
    val dayNumber: Int
    
    /**
     * Solves part 1 of the day's puzzle.
     * 
     * @param input The input lines as a list of strings
     * @return The result as a string (will be printed by the runner)
     */
    fun part1(input: List<String>): String
    
    /**
     * Solves part 2 of the day's puzzle.
     * 
     * @param input The input lines as a list of strings
     * @return The result as a string (will be printed by the runner)
     */
    fun part2(input: List<String>): String
}

/**
 * Example implementation template. Copy and adapt for each day.
 * 
 * class Day1 : Day {
 *     override val dayNumber: Int = 1
 *     
 *     override fun part1(input: List<String>): String {
 *         // Your part 1 solution here
 *         return "Result for part 1"
 *     }
 *     
 *     override fun part2(input: List<String>): String {
 *         // Your part 2 solution here
 *         return "Result for part 2"
 *     }
 * }
 */
