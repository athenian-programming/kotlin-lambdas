default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

build: clean
	./gradlew build -xtest

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.11.1 --distribution-type=bin
