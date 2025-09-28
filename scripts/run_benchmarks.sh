#!/usr/bin/env bash
set -euo pipefail

# Build project
mvn -q clean package -DskipTests

# Run benchmarks
java -cp target/assignment2-selection-1.0-SNAPSHOT.jar cli.BenchmarkRunner \
  --twoway true \
  --n 100 1000 10000 100000 \
  --dist random sorted reverse nearly-sorted \
  --seed 42 > docs/performance-plots/selection.csv

echo "✅ Benchmark finished: results saved to docs/performance-plots/selection.csv"
