#!/bin/bash

help()
{
cat << HELP
	USAGE:b2d [-h] binarynum
	EXAMPLE:b2d 110
	will return 6
HELP
	exit 0
}

error()
{
	echo "$1"
	exit 1
}

lastchar()
{
	if [ -z "$1" ];then
		rval=""
		return
	fi

	numofchar=$(echo -n "$1" | wc -c | sed 's/ //g')
	rval=$(echo -n "$1" | cut -b "$numofchar")
}

chop()
{
	if [ -z "$1" ];then
		rval=""
		return
	fi

	numofchar=$(echo -n "$1" | wc -c | sed 's/ //g')
	if [ "$numofchar" = "1" ];then
		rval=""
		return
	fi

	numofcharminus1=$(expr "$numofchar" - 1)
	rval=$(echo -n "$1" | cut -b 1-${numofcharminus1})
}




while [ -n "$1" ]; do
	case $1 in
		-h) help; shift 1;;
		-*) error "error:no such option $1. -h for help"; break;;
		*) break;;
	esac
done

sum=0
weight=1

[ -z "$1" ] && help

binnum="$1"

while [ -n "$binnum" ];do
	lastchar "$binnum"
	if [ "$rval" = "1" ]; then
		sum=$(expr "$weight" + "$sum")
	fi

	chop "$binnum"
	binnum="$rval"
	weight=$(expr "$weight" \* 2)
done

echo "$sum"