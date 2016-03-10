# --- !Ups
CREATE TABLE "intern" ("id" SERIAL PRIMARY KEY ,"name" varchar(200) , "email" varchar(200) , "password" varchar(200));
INSERT INTO "intern" values (1,'john', 'john@gmail.com','abcdef');

INSERT INTO "intern" values (2,'admin', 'admin@gmail.com','admin');


CREATE TABLE "language"("sno" SERIAL, "known" varchar(200),"fluency" varchar(200),"internid" int not null,PRIMARY KEY(sno,internid),FOREIGN KEY(internid) references intern(id));

CREATE TABLE "award"("id" SERIAL, "name" varchar(200),"details" varchar(200),"internid" int not null,PRIMARY KEY(id,internid),FOREIGN KEY(internid) references intern(id));

CREATE TABLE "proglanguage"("sno" SERIAL, "known" varchar(200),"fluency" varchar(200),"internid" int not null,PRIMARY KEY(sno,internid),FOREIGN KEY(internid) references intern(id));


# --- !Downs

DROP TABLE "intern";

DROP TABLE "award";

DROP TABLE "proglanguage";
