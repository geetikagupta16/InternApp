# --- !Ups
CREATE TABLE "intern" ("id" SERIAL PRIMARY KEY ,"name" varchar(200) , "email" varchar(200) , "password" varchar(200));
INSERT INTO "intern" values (1,'john', 'john@gmail.com','abcdef');

INSERT INTO "intern" values (2,'admin', 'admin@gmail.com','admin');

CREATE TABLE "assignment"("sno" SERIAL, "name" varchar(200),"date" varchar(200),"marks" int,"remarks" varchar(200),"internid" int not null,PRIMARY KEY(sno,internid),FOREIGN KEY(internid) references intern(id));


CREATE TABLE "language"("sno" SERIAL, "known" varchar(200),"fluency" varchar(200),"internid" int not null,PRIMARY KEY(sno,internid),FOREIGN KEY(internid) references intern(id));

CREATE TABLE "award"("id" SERIAL, "name" varchar(200),"details" varchar(200),"internid" int not null,PRIMARY KEY(id,internid),FOREIGN KEY(internid) references intern(id));

CREATE TABLE "proglanguage"("sno" SERIAL, "known" varchar(200),"fluency" varchar(200),"internid" int not null,PRIMARY KEY(sno,internid),FOREIGN KEY(internid) references intern(id));

INSERT INTO "language" values(1,'french','bad',1);
INSERT INTO "language" values(2,'Spanish','good',1);


INSERT INTO "award" values(1,'IT Quiz','Quiz',1);
INSERT INTO "award" values(2,'IT tambola','tambola',1);

INSERT INTO "proglanguage" values(1,'HTML','good',1);


INSERT INTO "assignment" values(1,'Play assignment','2016-03-03','6','average',1);
INSERT INTO "assignment" values(2,'Jquery assignment','2016-01-02','7','good',1);


# --- !Downs

DROP TABLE "intern";

DROP TABLE "award";

DROP TABLE "proglanguage";

DROP TABLE "language";

DROP TABLE "assignment";
