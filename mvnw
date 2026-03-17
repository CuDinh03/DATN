#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements. See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied. See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
# JAVA_HOME - location of a JDK home dir
# MVNW_REPOURL - repo url base for downloading maven distribution
# MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
# MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

set_java_home() {
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"
    fi
    if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
      echo "JAVA_HOME is set to \"$JAVA_HOME\" but java/javac not found." >&2
      return 1
    fi
  else
    JAVACMD="$(command -v java 2>/dev/null)" || :
    JAVACCMD="$(command -v javac 2>/dev/null)" || :
    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "java/javac not found in PATH and JAVA_HOME not set." >&2
      return 1
    fi
  fi
}

hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verbose() { :; }
[ "${MVNW_VERBOSE-}" = true ] && verbose() { printf %s\\n "${1-}"; }

die() { printf %s\\n "$1" >&2; exit 1; }

trim() {
  printf "%s" "${1}" | tr -d '[:space:]'
}

scriptDir="$(dirname "$0")"
scriptName="$(basename "$0")"

while IFS="=" read -r key value; do
  case "${key-}" in
    distributionUrl) distributionUrl=$(trim "${value-}") ;;
    distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"$scriptDir/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distributionUrl-}" ] || die "Cannot read distributionUrl from .mvn/wrapper/maven-wrapper.properties"

case "${distributionUrl##*/}" in
  *-bin.zip) ;;
  *) die "distributionUrl must be *-bin.zip, got: ${distributionUrl-}" ;;
esac

distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL 2>/dev/null || true
  exec "$MAVEN_HOME/bin/mvn" "$@" || die "Cannot exec $MAVEN_HOME/bin/mvn"
}

if [ -d "$MAVEN_HOME" ] && [ -x "$MAVEN_HOME/bin/mvn" ]; then
  exec_maven "$@"
fi

TMP_DOWNLOAD_DIR="$(mktemp -d)" || die "Cannot create temp dir"
clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
trap clean HUP INT TERM EXIT

mkdir -p "${MAVEN_HOME%/*}"
verbose "Downloading Maven from: $distributionUrl"

__MVNW_QUIET_CURL=--silent
[ "${MVNW_VERBOSE-}" = true ] && __MVNW_QUIET_CURL=''

if command -v curl >/dev/null; then
  curl -f -L ${__MVNW_QUIET_CURL} -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "curl failed to fetch $distributionUrl"
elif set_java_home 2>/dev/null; then
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  cat >"$javaSource" <<'END'
import java.io.*;
import java.net.*;
public class Downloader {
  public static void main(String[] args) throws Exception {
    URL u = new URL(args[0]);
    try (InputStream in = u.openStream(); FileOutputStream out = new FileOutputStream(args[1])) {
      byte[] b = new byte[65536]; int n;
      while ((n = in.read(b)) != -1) out.write(b, 0, n);
    }
  }
}
END
  "$JAVACCMD" "$javaSource" -d "$TMP_DOWNLOAD_DIR" || die "Failed to compile Downloader"
  "$JAVACMD" -cp "$TMP_DOWNLOAD_DIR" Downloader "$distributionUrl" "$TMP_DOWNLOAD_DIR/$distributionUrlName" || die "Failed to download"
else
  die "Need curl or java to download Maven"
fi

if command -v unzip >/dev/null; then
  unzip -q "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "Failed to unzip"
else
  die "Need unzip to extract Maven"
fi

# Find directory that contains bin/mvn (set -f disables globbing, so use find)
mvnPath=$(find "$TMP_DOWNLOAD_DIR" -maxdepth 3 -type f -name mvn 2>/dev/null | head -1)
[ -n "$mvnPath" ] && [ -x "$mvnPath" ] || die "Could not find mvn in extracted archive"
actualDir=$(dirname "$(dirname "$mvnPath")")
mv "$actualDir" "$MAVEN_HOME" || die "Failed to move Maven to $MAVEN_HOME"
clean || true
exec_maven "$@"
