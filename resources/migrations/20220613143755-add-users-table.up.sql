CREATE TABLE IF NOT EXISTS customers (
  invoiceNo INT,
  stockCode VARCHAR(10),
  description VARCHAR(35),
  quantity INT,
  invoiceDate VARCHAR(40),
  unitPrice FLOAT,
  customerID INT PRIMARY KEY,
  country VARCHAR(20)
);
