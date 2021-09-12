
from crypto.tricemus_tables import TricemusEncryptor
from crypto.simple_tables import  SimpleEncryptor


def testTricemus():
    encryptor = TricemusEncryptor("префектура", (5, 7))
    result = encryptor.encrypt("АББАТ ТРИСЕМУС, ТАБЛИЦА.")
    print("Encrypted text: " + result)
    result = encryptor.decrypt(result)
    print("Decrypted text: " + result)

def testSimple():
    encryptor = SimpleEncryptor("префектура", (3, 5))
    result = encryptor.encrypt("АББАТ ТРИСЕМУС, ТАБЛИЦА.")
    print("Encrypted text: " + result)
    result = encryptor.decrypt(result)
    print("Encrypted text: " + result)



if __name__ == '__main__':
    #testTricemus()
    testSimple()
