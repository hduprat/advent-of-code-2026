# advent-of-code-2026

Advent of Code 2026 - Kotlin again! Only use of AI will be for the tools (file reader, runner).

## Project Structure

```
advent-of-code-2026/
├── run.sh                # Run day solutions
├── test.sh               # Run tests
├── scaffold.sh           # Create new day scaffold
├── README.md
├── templates/
│   ├── DayTemplate.kt    # Day interface
│   └── TestTemplate.kt   # Test interface
├── runners/
│   ├── FileReader.kt     # File reading utility
│   ├── runner.kt         # Main runner program
│   └── TestRunner.kt     # Test runner program
├── day1/
│   ├── main.kt           # Day 1 implementation
│   ├── TestDay1.kt       # Day 1 tests
│   ├── input.txt         # Main input
│   └── input.example.txt # Example input
└── .gitignore
```

## How to Use

### Creating a new day

```bash
./scaffold.sh 2
```

Creates `day2/` with:
- `main.kt` - Kotlin file implementing the Day interface
- `TestDay2.kt` - Test file implementing the DayTest interface
- `input.txt` - For your puzzle input
- `input.example.txt` - For your example input

### Running solutions

```bash
./run.sh 1              # Runs both parts of day 1
./run.sh 1 1            # Runs only part 1 of day 1
./run.sh 1 2            # Runs only part 2 of day 1
./run.sh 1 --example    # Runs with example input
./run.sh 1 1 --example  # Runs part 1 with example input
```

### Running tests (TDD)

```bash
./test.sh 1  # Runs tests for day 1
```

In `day1/TestDay1.kt`, implement your tests:

```kotlin
class TestDay1 : DayTest {
    override fun test() {
        testPart1(1, "42")
        testPart2(1, "123")
        
        val customInput = listOf("line1", "line2", "line3")
        val day = Day1()
        val result = day.part1(customInput)
        if (result != "expected") {
            throw AssertionError("Custom test failed")
        }
    }
}
```

The `testPart1()` and `testPart2()` helpers automatically read from `input.example.txt` and compare against expected results.
