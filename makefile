# Liad Cohen
# 313362394

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java
run:
	java -cp biuoop-1.4.jar:bin:resources Ass7Game 
jar:
	jar cfm ass7game.jar Manifest.mf -C bin . -C resources .
bin:
	mkdir bin