#!/bin/bash

dir_full=`pwd`
dir=`basename $dir_full`
commit=$1
passwd=$2

/usr/bin/expect << EOF

set timeout 20
spawn git add .
spawn git commit -m"$commit"
spawn git push https://github.com/wujiaming123/${dir}.git master
expect "'https://github.com':"
send "wujiaming123\n"
expect "'https://wujiaming123@github.com':"
send "${passwd}\n"
expect eof
EOF
