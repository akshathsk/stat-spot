# dml-sql-generator

## Description

This tool is used to generate SQL Insert statements used by the StatSpot database.

The input is sourced from 
1. https://www.kaggle.com/datasets/davidcariboo/player-scores/versions/223?resource=download&select=club_games.csv
2. https://www.kaggle.com/datasets/arjunprasadsarkhel/2021-olympics-in-tokyo

## Running the jar

Copy all csv files from the above 2 kaggle datasets to the `src/main/resources` folder

```shell
mvn clean package
java -jar target/dml-sql-generator-1.0-SNAPSHOT-jar-with-dependencies.jar
```

All the sql files will be in the `target/classes` folder