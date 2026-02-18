CREATE TABLE members (
    user_no NUMBER PRIMARY KEY,        
    email VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(100) NOT NULL,    
    name VARCHAR2(50) NOT NULL,       
    reg_date DATE DEFAULT SYSDATE      
);


CREATE SEQUENCE mem_seq START WITH 1 INCREMENT BY 1;