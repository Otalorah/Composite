#!/usr/bin/env bash
# ---------------------------------------------------------------
#  build-run.sh  —  Compile and run the RPG Character System
#  Requirements: JDK 17+  (java / javac must be on PATH)
#  Usage:  chmod +x build-run.sh && ./build-run.sh
# ---------------------------------------------------------------

set -e

echo "[1/2] Compiling..."
mkdir -p out

find src -name "*.java" | xargs javac -d out -sourcepath src -encoding UTF-8

echo "[2/2] Running..."
echo ""
java -cp out Main
