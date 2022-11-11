import pymysql

# 接收到药械到货信息后扫描数据库寻找匹配患者
def scan(db1, medid):
    cursor = db1.cursor()
    scan = "select * from tb_case where medicalNeed like'%%s%%'" %medid
    cursor.execute(scan)
    result = cursor.fetchall()
    return result

# 将查询结果编码为字符串
def encode(query):
    result = ""
    for row in query:
        sub_result = ""
        for item in row:
            sub_result = sub_result + ',' + str(item)
        result = result + ':' + sub_result
    return result

# 将查询结果存储于~/data文件中
def store(result):
    file = open(r'~/data','w')
    file.write(result)
    file.close()

# 创建患者数据库
def create(db1):
    cursor = db1.cursor()
    create1 = "CREATE TABLE hospital( " \
              "patient_id char(8) NOT NULL PRIMARY KEY COMMENT '患者id，为8位数字', " \
              "hospital_addr BINARY(160) NOT NULL COMMENT '医院账户地址，以二进制存储' " \
              ")"

    create2 = "CREATE TABLE medical(" \
              "medid char(4) NOT NULL COMMENT '药械id', " \
              "remain_time tinyint NOT NULL COMMENT '如不为0，则代表离特定时间点得时间'" \
              "patient_id CHAR(8) COMMENT '患者id，为8位数字', " \
              "FOREIGN KEY(patient_id) REFERENCES hospital(patient_id), " \
              ")"


if __name__ == "__main__":

    db = pymysql.connect(
        host="42.194.183.103",
        port=3306,
        database='hospitaldb',
        user="root",
        password="password",
        charset="utf8mb4"
    )

    medical_id = input("please input id of medical appliance\n")
    local_hospital_id = input("please input hospital that have the medical appliance\n")
    result1 = scan(db, medical_id)
    result2 = encode(result1)
    result3 = medical_id + ':' + local_hospital_id + result2
    store(result3)


