CREATE TABLE PRODUCT(
productID INTEGER PRIMARY KEY NOT NULL,
productName TEXT,
productUnit TEXT,
productPrice TEXT,
displayOrStore TEXT
);

CREATE TABLE CUSTOMERS(
customerID INTEGER PRIMARY KEY NOT NULL,
customerName TEXT,
customerPhone TEXT,
customerAge TEXT,
customerType TEXT,
imageSave BLOB
);
CREATE TABLE DISPLAYORNOT(
displayOrStores TEXT
);



