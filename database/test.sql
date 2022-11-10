CREATE TABLE hospital(
patient_id char(8) PRIMARY KEY COMMENT '患者id，为8位数字',
hospital_addr BINARY(160) COMMENT '医院账户地址，以二进制存储'
);

CREATE TABLE medical(
medid char(4) NOT NULL COMMENT '药械id',
remain_time tinyint NOT NULL COMMENT '如不为0，则代表离特定时间点得时间',
patient_id CHAR(8) COMMENT '患者id，为8位数字',
FOREIGN KEY (patient_id) REFERENCES hospital(patient_id)
);

INSERT INTO hospitaldb.hospital VALUES ('20220102','1101010101001111110110101011101010000100111101000111001011111101010101101101011001011110110010011101101000110101111110000100011100010000111001111100100010000000');
INSERT INTO hospitaldb.medical VALUES ('28','02','20220102'); 

CREATE SCHEMA hospitaldb;

SELECT patient_id FROM hospital;

SELECT hospital.patient_id,hospital_addr
FROM hospital,medical
WHERE hospital.patient_id=medical.patient_id
AND medid = '01'
AND remain_time = '0';
