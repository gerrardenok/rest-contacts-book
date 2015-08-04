#!/usr/bin/env bash

FOLDER=/util

echo "Installing Docker"

apt-get update
apt-get -y install docker.io
ln -sf /usr/bin/docker.io /usr/local/bin/docker
sed -i '$acomplete -F _docker docker' /etc/bash_completion.d/docker.io
update-rc.d docker.io defaults

echo "Installing Docker-Compose"

if [ ! -f "${FOLDER}/docker-compose" ]; then
    mkdir $FOLDER
	chmod 777 $FOLDER
	curl -L https://github.com/docker/compose/releases/download/1.2.0/docker-compose-`uname -s`-`uname -m` > $FOLDER/docker-compose
	chmod +x $FOLDER/docker-compose
fi

