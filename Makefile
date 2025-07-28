default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

build: clean
	./gradlew build -xtest

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.14.3 --distribution-type=bin
