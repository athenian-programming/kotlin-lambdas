default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

upgrade-wrapper:
	./gradlew wrapper --gradle-version=6.8.3 --distribution-type=bin
