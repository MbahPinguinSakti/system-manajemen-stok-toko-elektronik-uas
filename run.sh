#!/bin/bash
javac -cp "lib/gson-2.13.2.jar" -sourcepath src -d out src/com/toko/*.java
java -cp "out:lib/gson-2.13.2.jar" com.toko.Main