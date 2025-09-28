# Assignment 2 — Pair 1 (Student B): Selection Sort (optimized)

**Цели:** реализовать Selection Sort с оптимизациями, собрать метрики, прогнать бенчмарки, сделать анализ и peer‑review.

## Быстрый старт
```bash
mvn -q test
mvn -q -DskipTests package

java -cp target/assignment2-selection-1.0-SNAPSHOT.jar cli.BenchmarkRunner \
--twoway true \
--n 100 1000 10000 100000 \
--dist random sorted reverse nearly-sorted \
--seed 42 > docs/performance-plots/selection.csv