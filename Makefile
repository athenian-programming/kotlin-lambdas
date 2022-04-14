default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

upgrade-wrapper:
	./gradlew wrapper --gradle-version=7.4.2 --distribution-type=bin
