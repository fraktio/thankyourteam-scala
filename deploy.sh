#!/bin/bash

REMOTE=www@stable.fraktio.fi
REMOTE_APP=/wwwroot/thankyourteam-scala/

activator clean compile dist || exit 1;
ssh $REMOTE "cd $REMOTE_APP; ./stop.sh";
scp target/universal/thankyourteam-1.0-SNAPSHOT.zip $REMOTE:$REMOTE_APP
ssh $REMOTE "cd $REMOTE_APP; unzip -o thankyourteam-1.0-SNAPSHOT.zip";
ssh $REMOTE "cd $REMOTE_APP; ./start.sh";