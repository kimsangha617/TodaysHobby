#!/bin/sh

# Setting Version
VERSION='1.0.0'

cd ..
./gradle clean build -x test

ROOT_PATH=`pwd`
echo ROOT_PATH

echo 'api Docker image build...'
cd $ROOT_PATH/api && docker build -t api:VERSION .
echo 'api Docker image build Done'
