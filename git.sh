#!/bin/bash
git add .
echo "***********start************"
commit=$*
if [ "$*" ];then
  echo "------$*------";
else
  commit="fix bug"
  echo "------fix bug-----"
fi
git commit -m "$commit"
git push 
echo "***********end************"

