-- :name create-customer-table! :! :n
-- :doc deletes a user record given the id
CREATE TABLE IF NOT EXISTS customers (
  invoiceNo INT PRIMARY KEY,
  stockCode VARCHAR(10),
  description VARCHAR(35),
  quantity INT,
  invoiceDate VARCHAR(40),
  unitPrice FLOAT,
  customerID INT,
  country VARCHAR(20)
)

-- :name insert-customer! :i! :n
-- :doc creates a new customer record
INSERT INTO customers
(invoiceNo, stockCode, description, quantity, invoiceDate, unitPrice, customerID, country)
VALUES (:invoiceNo, :stockCode, :description, :quantity, :invoiceDate, :unitPrice, :customerID, :country)

-- :name update-customer! :! :n
-- :doc updates an existing customer record
UPDATE customers
SET invoiceNo = :invoiceNo,
    stockCode = :stockCode,
    description = :description,
    quantity = :quantity,
    invoiceDate = :invoiceDate,
    unitPrice = :unitPrice,
    country = :country
WHERE invoiceNo = :invoiceNo

-- :name get-customer :? :1
-- :doc retrieves a customer record given the id
SELECT * FROM customers
WHERE invoiceNo = :invoiceNo

-- :name delete-customer! :! :n
-- :doc deletes a customer record given the id
DELETE FROM customers
WHERE invoiceNo = :invoiceNo
