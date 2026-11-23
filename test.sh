#!/bin/bash

# Color codes
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Advent of Code 2026 - Test Runner Script
# Runs tests for a specific day
# Usage: ./test.sh <day>

set -e

# Check if argument is provided
if [ $# -ne 1 ]; then
    echo -e "${RED}Usage: $0 <day>${NC}"
    echo "  day: Day number (1-25)"
    exit 1
fi

DAY=$1

# Check if day is valid
if ! [[ "$DAY" =~ ^[0-9]+$ ]] || [ "$DAY" -lt 1 ] || [ "$DAY" -gt 25 ]; then
    echo -e "${RED}Error: Day must be a number between 1 and 25${NC}"
    exit 1
fi

# Get the directory of this script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Compile all Kotlin files including tests
KOTLIN_FILES=(
    "runners/Colors.kt"
    "runners/FileReader.kt"
    "templates/DayTemplate.kt"
    "runners/TestRunner.kt"
    "templates/TestTemplate.kt"
)

# Add all day main.kt and test files that exist
for i in {1..25}; do
    if [ -f "day$i/main.kt" ]; then
        KOTLIN_FILES+=("day$i/main.kt")
    fi
    if [ -f "day$i/TestDay$i.kt" ]; then
        KOTLIN_FILES+=("day$i/TestDay$i.kt")
    fi
done

# Check if there's anything to compile
if [ ${#KOTLIN_FILES[@]} -eq 0 ]; then
    echo -e "${RED}Error: No Kotlin files found${NC}"
    exit 1
fi

echo -e "${CYAN}Compiling test files...${NC}"
kotlinc -d tests.jar "${KOTLIN_FILES[@]}" -include-runtime

echo -e "${CYAN}Running tests for Day $DAY...${NC}"
java -jar tests.jar "$DAY"
