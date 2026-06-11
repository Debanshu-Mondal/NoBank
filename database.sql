CREATE TABLE users (
    form_no SERIAL PRIMARY KEY,
    honorific VARCHAR(10),
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(20),
    dob DATE,
    email_id VARCHAR(100),
    phone_no VARCHAR(15),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(10)
);

CREATE TABLE accounts(
  acc_no BIGINT PRIMARY KEY,
  form_no INT NOT NULL REFERENCES users(form_no),
  pin INT NOT NULL
);

CREATE TABLE transactions (
    txn_id SERIAL PRIMARY KEY,
    acc_no BIGINT NOT NULL REFERENCES accounts(acc_no),
    txn_type VARCHAR(20) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    txn_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
