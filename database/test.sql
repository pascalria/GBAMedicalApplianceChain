CREATE TABLE 'hospital'(
patient_id char(8) NOT NULL PRIMARY KEY COMMENT '患者id，为8位数字',
hospital_addr BINARY(160) NOT NULL COMMENT '医院账户地址，以二进制存储'
)

CREATE TABLE 'medical'(
FOREIGN KEY(patient_id) REFERENCES hospital(patient_id),
medid char(4) NOT NULL COMMENT '药械id',
remain_time tinyint NOT NULL COMMENT '如不为0，则代表离特定时间点得时间'
)
