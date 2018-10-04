#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Below is an example of what might be found in this file if your program was written in Python
#
#python ./src/pharmacy_counting.py ./input/itcont.txt ./output/top_cost_drug.txt

export _JAVA_OPTIONS="-Xms8000m -Xmx8000m"
cd src;
mvn package;
java -cp target/pharmacy-counting-1.0.jar com.insight.app.App; 