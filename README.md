# EDAF75, project report

This is the report for

 + Sofi Flink, `bmp13sfl@student.lu.se`
 + Simon Tenggren, `si6187te-s@student.lu.se`

We solved this project on our own, except for:

 + The Peer-review meeting
 + Stackoverflow
 + Some code re-used from laboration 3.


## ER-design

The model is in the file [`Krusty Kookie AB UMLv3.png`](https://gitlab.com/Taff3r/ETAF75-KrustyKookies/raw/master/Krusty%20Kookie%20AB%20UMLv3.png):

<center>
    <img src="https://gitlab.com/Taff3r/ETAF75-KrustyKookies/raw/master/Krusty%20Kookie%20AB%20UMLv3.png" width="100%">
</center>

## Relations

The ER-model above gives the following relations (neither
[Markdown](https://docs.gitlab.com/ee/user/markdown.html)
nor [HTML5](https://en.wikipedia.org/wiki/HTML5) handles
underlining withtout resorting to
[CSS](https://en.wikipedia.org/wiki/Cascading_Style_Sheets),
so we use bold face for primary keys, italicized face for
foreign keys, and bold italicized face for attributes which
are both primary keys and foreign keys):

+ recipes(**rec_name**)
+ ingredients(**ing_name**, unit, amount, date_of_deliv, size_of_deliv)
+ rec_ing(***rec_name***, ***ing_name***, amount)
+ customers(**c_name**, address, phn_nbr)
+ orders(**order_id**, delivered_by, *c_name*)
+ pallets(**pallet_id**, status, prod_date, *rec_name*, *order_id*)
+ deliveries(**delivery_id**, delivery_date)
+ delivery_info(***delivery_id***, ***order_id***, amount_delivered)
+ blocked(***rec_name***, start_date, end_date)
+ order_info(***rec_ name***, ***order_id***, no_pallets)

## Scripts to set up database

The scripts used to set up and populate the database are in:

 + [`create-schema.sql`](create-schema.sql) (defines the tables), and
 + [`initial-data.sql`](initial-data.sql) (inserts data).

So, to create and initialize the database, we run:

```shell
sqlite3 krusty-db.sqlite < create-schema.sql
sqlite3 krusty-db.sqlite < initial-data.sql
```

## How to compile and run the program

Before compiling and running the program make sure you have the latest version of openJDK and openJFX installed.
To run the program run the following commands after downloading the repositry and navigating to the folder ETAF75-KrustyKookies:

```shell
javac -d bin/ -cp src Kookie/src/application/Main.java 
java -cp bin:Kookie/sqlite-jdbc-3.21.0.jar application.Main 

```

