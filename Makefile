default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

upgrade-wrapper:
	./gradlew wrapper --gradle-version=7.3.3 --distribution-type=bin
