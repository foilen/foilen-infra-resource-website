#!/bin/bash

set -e 

RUN_PATH="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $RUN_PATH

# Prepare folders
FOLDER_PLUGINS_JARS=$(pwd)/_plugins-jars
mkdir -p $FOLDER_PLUGINS_JARS

# Download plugins
USER_ID=$(id -u)
docker run -ti \
  --rm \
  --env PLUGINS_JARS=/plugins \
  --user $USER_ID \
  --volume $FOLDER_PLUGINS_JARS:/plugins \
  foilen/foilen-infra-system-app-test-docker \
  download-latest-plugins \
  /plugins application dns domain machine unixuser webcertificate

# Create release
./create-local-release.sh
cp build/libs/foilen-infra-resource-website-master-SNAPSHOT.jar $FOLDER_PLUGINS_JARS

# Start webapp
docker run -ti \
  --rm \
  --env PLUGINS_JARS=/plugins \
  --user $USER_ID \
  --volume $FOLDER_PLUGINS_JARS:/plugins \
  --publish 8080:8080 \
  foilen/foilen-infra-system-app-test-docker \
  web --debug

