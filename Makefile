default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

build: clean
	./gradlew build -x test

upgrade-wrapper:
	./gradlew wrapper --gradle-version=9.3.1 --distribution-type=bin
