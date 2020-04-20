#!/bin/bash

SCRIPT=$(readlink -f $0)
SCRIPTPATH=`dirname $SCRIPT`
PROJECT_NAME="babelfish-cdr babelfish-oidc babelfish-spring babelfish-util"

cd $SCRIPTPATH/..

for PROJECT in $PROJECT_NAME;
do
	`which watchman` -- trigger $PROJECT/src "$PROJECT-build" -- $SCRIPTPATH/compile-src.sh
done

