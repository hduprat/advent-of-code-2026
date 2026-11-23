package aoc2026

import java.io.File

/**
 * Utility object for reading Advent of Code input files.
 * Reads from the current day's directory or a specified path.
 * Supports both regular input files and example input files.
 */
object FileReader {
    private fun getFileName(useExample: Boolean): String = if (useExample) "input.example.txt" else "input.txt"
    
    /**
     * Reads all lines from the input file for a specific day.
     * 
     * @param day The day number (1-25)
     * @param useExample If true, reads from input.example.txt instead of input.txt
     * @return List of lines as strings
     */
    fun readLines(day: Int, useExample: Boolean = false): List<String> {
        val file = File("day$day/${getFileName(useExample)}")
        return file.readLines()
    }
    
    /**
     * Reads all lines from a custom input file path.
     * 
     * @param filePath The path to the input file
     * @return List of lines as strings
     */
    fun readLines(filePath: String): List<String> = File(filePath).readLines()
    
    /**
     * Reads the entire input file as a single string.
     * 
     * @param day The day number (1-25)
     * @param useExample If true, reads from input.example.txt instead of input.txt
     * @return The entire file content as a string
     */
    fun readText(day: Int, useExample: Boolean = false): String {
        val file = File("day$day/${getFileName(useExample)}")
        return file.readText()
    }
    
    /**
     * Reads the entire input file from a custom path as a single string.
     * 
     * @param filePath The path to the input file
     * @return The entire file content as a string
     */
    fun readText(filePath: String): String = File(filePath).readText()
    
    /**
     * Reads lines as a stream (lazy sequence).
     * Useful for large input files or when you want to process lines lazily.
     * 
     * @param day The day number (1-25)
     * @param useExample If true, reads from input.example.txt instead of input.txt
     * @return Sequence of lines
     */
    fun readLinesAsSequence(day: Int, useExample: Boolean = false): Sequence<String> {
        val file = File("day$day/${getFileName(useExample)}")
        return file.useLines { it }
    }
    
    /**
     * Reads lines as a stream from a custom path.
     * 
     * @param filePath The path to the input file
     * @return Sequence of lines
     */
    fun readLinesAsSequence(filePath: String): Sequence<String> = File(filePath).useLines { it }
}
