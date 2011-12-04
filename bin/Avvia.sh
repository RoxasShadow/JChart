#!/bin/bash
#####################################
#/Avvia.sh
#(C) Giovanni Capuano 2011
#####################################

# Esegue il jar.
java -jar JChart.jar
read -p "Your choice? " -e input
java -jar JChart.jar $input
