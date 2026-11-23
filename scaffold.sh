#!/bin/bash

# Color codes
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

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

# Check if day folder already exists
if [ -d "day$DAY" ]; then
    echo -e "${RED}Error: day$DAY already exists${NC}"
    exit 1
fi

# Create day folder
mkdir -p "day$DAY"

# Create input.txt (empty)
touch "day$DAY/input.txt"

# Create input.example.txt (empty)
touch "day$DAY/input.example.txt"

# Create main.kt with template
cat > "day$DAY/main.kt" << EOF
package aoc2026

class Day$DAY : Day {
    override val dayNumber: Int = $DAY
    
    override fun part1(input: List<String>): String {
        return "Not implemented"
    }
    
    override fun part2(input: List<String>): String {
        return "Not implemented"
    }
}
EOF

# Create test file with template
cat > "day$DAY/TestDay$DAY.kt" << EOF
package aoc2026

class TestDay$DAY : DayTest {
    override fun test() {
    }
}
EOF

echo -e "${CYAN}Created day$DAY with scaffold files:${NC}"
echo "  - day$DAY/main.kt"
echo "  - day$DAY/TestDay$DAY.kt"
echo "  - day$DAY/input.txt"
echo "  - day$DAY/input.example.txt"
