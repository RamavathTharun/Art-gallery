CREATE TABLE if not exists artist(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    genre varchar(255)
);


CREATE TABLE if not exists art(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title varchar(255),
    theme varchar(255),
    artistId INT,
    FOREIGN KEY(artistId) REFERENCES artist(id)
);

CREATE TABLE if not exists gallery(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    location varchar(255)
);

CREATE TABLE if not exists artist_gallery(
    artistId INT,
    galleryId INT,
    PRIMARY KEY(artistId, galleryId),
    FOREIGN KEY(artistId) REFERENCES artist(id),
    FOREIGN KEY(galleryId) REFERENCES gallery(id)

);
