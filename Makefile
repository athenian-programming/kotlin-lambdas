default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.2.1 --distribution-type=bin
