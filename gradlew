#!/bin/sh

GRADLE_APP_NAME="Gradle"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-${HOME}/.gradle}"

cd "$(dirname "$0")"

exec gradle --gradle-user-home "${GRADLE_USER_HOME}" "$@"
