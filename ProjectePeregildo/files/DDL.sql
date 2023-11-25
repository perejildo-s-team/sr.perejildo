USE perejildo;

CREATE TABLE status (
    id_plant varchar(10),
    time_stamp timestamp,
    light int,
    soil_humidity int,
    air_humidity int,
    temperature int,
    PRIMARY KEY(id_plant, time_stamp)
);