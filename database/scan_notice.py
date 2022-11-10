import pymysql

# 接收到药械到货信息后扫描数据库寻找匹配患者
def scan(db1, medid):
    cursor = db1.cursor()
    scan = "SELECT ID FROM medical WHERE medid==%s AND remain_time==0" %medid
    cursor.execute(scan)
    result = cursor.fetchall()
    return result

def store(result):
    

# 创建患者数据库
def create(db1):
    cursor = db1.cursor()
    create1 = "CREATE TABLE 'hospital'( \
	            patient_id char(8) NOT NULL PRIMARY KEY COMMENT '患者id，为8位数字', \
	            hospital_addr BINARY(160) NOT NULL COMMENT '医院账户地址，以二进制存储' \
                )"

    create2 = "CREATE TABLE 'medical'( \
	            FOREIGN KEY(patient_id) REFERENCES hospital(patient_id), \
	            medid char(4) NOT NULL COMMENT '药械id', \
	            remain_time tinyint NOT NULL COMMENT '如不为0，则代表离特定时间点得时间' \
                )"


if __name__ == "__main__":

    db = pymysql.connect(
        host="127.0.0.1",
        port=3306,
        user="hospitaldb",
        password="123456",
        charset="utf8mb4"
    )

    medical_id = input("please input id of medical appliance")
    cursor = db.cursor()
    cursor.execute(medical_id)
    result = cursor.fetchall()

