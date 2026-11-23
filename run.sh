#!/bin/bash

# Color codes
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Advent of Code 2026 Runner Script
# Usage: ./run.sh <day> [part] [--example]
#   day      : Day number (1-25)
#   part     : Optional. 1 for part 1 only, 2 for part 2 only.
#   --example : Optional. Use input.example.txt instead of input.txt.
#
# Examples:
#   ./run.sh 1              # Runs both parts of day 1 with main input
#   ./run.sh 1 1            # Runs only part 1 of day 1 with main input
#   ./run.sh 1 --example    # Runs both parts of day 1 with example input
#   ./run.sh 1 1 --example  # Runs only part 1 of day 1 with example input

set -e

# Check if arguments are provided
if [ $# -lt 1 ]; then
    echo -e "${RED}Usage: $0 <day> [part] [--example]${NC}"
    echo "  day      : Day number (1-25)"
    echo "  part     : Optional. 1 for part 1 only, 2 for part 2 only."
    echo "  --example : Optional. Use input.example.txt instead of input.txt."
    exit 1
fi

# Parse arguments
DAY=""
PART=""
EXAMPLE=false

for arg in "$@"; do
    case "$arg" in
        --example)
            EXAMPLE=true
            ;;
        *)
            if [ -z "$DAY" ]; then
                DAY="$arg"
            elif [ -z "$PART" ]; then
                PART="$arg"
            fi
            ;;
    esac
done

# Check if day is valid
if ! [[ "$DAY" =~ ^[0-9]+$ ]] || [ "$DAY" -lt 1 ] || [ "$DAY" -gt 25 ]; then
    echo -e "${RED}Error: Day must be a number between 1 and 25${NC}"
    exit 1
fi

# Check if part is valid (if provided)
if [ -n "$PART" ] && ! [[ "$PART" =~ ^[12]$ ]]; then
    echo -e "${RED}Error: Part must be 1 or 2${NC}"
    exit 1
fi

# Get the directory of this script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Compile all Kotlin files
KOTLIN_FILES=(
    "runners/Colors.kt"
    "runners/FileReader.kt"
    "templates/DayTemplate.kt"
    "runners/runner.kt"
)

# Add all day main.kt files that exist
for i in {1..25}; do
    if [ -f "day$i/main.kt" ]; then
        KOTLIN_FILES+=("day$i/main.kt")
    fi
done

# Check if there's anything to compile
if [ ${#KOTLIN_FILES[@]} -eq 0 ]; then
    echo -e "${RED}Error: No Kotlin files found${NC}"
    exit 1
fi

echo -e "${CYAN}Compiling Kotlin files...${NC}"
kotlinc -d aoc.jar "${KOTLIN_FILES[@]}" -include-runtime

# Build command line arguments for java
JAVA_ARGS=("$DAY")
if [ -n "$PART" ]; then
    JAVA_ARGS+=("$PART")
fi
if [ "$EXAMPLE" = true ]; then
    JAVA_ARGS+=("--example")
fi

echo -e "${CYAN}Running Day $DAY...${NC}"
java -jar aoc.jar "${JAVA_ARGS[@]}"
