import pymysql

def scan(db1, medid):
    cursor = db1.cursor()
    scan = "SELECT ID FROM hospitaltable WHERE medical_id==%s " %medid
    cursor.execute(scan)
    result = cursor.fetchall()
    return result

def create(db1):
    cursor = db1.cursor()
    create = ""


if __name__ == "__main__":

    db = pymysql.connect(
        host="127.0.0.1",
        port=3306,
        user="hospitaldb",
        password="123456",
        charset="utf8mb4"
    )

    medical_id = input("please input id of medical appliance")


