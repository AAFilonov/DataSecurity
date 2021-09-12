# алгоритм шифрующих таблиц Трисемуса;
from crypto.encryptor import Encryptor

KEY_INDEX = 0
VALUE_INDEX = 1


class TricemusEncryptor(Encryptor):
    keyword = ""
    size = (5, 7)
    encryptionTable = []
    def __init__(self, keyword, size):
        self.keyword = self.removeDuplicates(keyword)
        self.size = size
        self.intEncryptionTable()
        print("Tricemus table inititalized:")
        self.printEncryptionTable()

    def getRowsCount(self):
        return self.size[0]

    def getColumnsCount(self):
        return self.size[1]

    def intEncryptionTable(self):
        varied_alphabet = self.removeDuplicates(self.keyword + self.baseAlphabet)
        for i in range(0, self.getRowsCount()):
            for j in range(0, self.getColumnsCount()):
                self.encryptionTable.append(
                    self.generateKeyValuePair(varied_alphabet, i, j))

    def generateKeyValuePair(self, varied_alphabet, i, j):
        key_position = i * self.getColumnsCount() + j
        value_position = ((i + 1) % self.getRowsCount()) * self.getColumnsCount() + j
        return [varied_alphabet[key_position], varied_alphabet[value_position]]

    def encrypt(self, param):
        param = str.lower(param)
        print("Tricemus did encryption on " + param)
        output = ""
        for i in param:
            output += self.encryptChar(i)
        return output

    def decrypt(self, param):
        param = str.lower(param)
        print("Tricemus did decryption on " + param)
        output = ""
        for i in param:
            output += self.decryptChar(i)
        return output

    def encryptChar(self, param):
        for item in self.encryptionTable:
            if param == item[KEY_INDEX]:
                return item[VALUE_INDEX]
        raise ValueError('No such letter in alphabet: ' + param)

    def decryptChar(self, param):
        for item in self.encryptionTable:
            if param == item[VALUE_INDEX]:
                return item[KEY_INDEX]
        raise ValueError('No such letter in alphabet: ' + param)

    def printEncryptionTable(self):
        for i in range(0, self.getRowsCount()):
            row = ""
            for j in range(0, self.getColumnsCount()):
                row += "[" + str(self.encryptionTable[i * self.getColumnsCount() + j][KEY_INDEX]) + " ],"
            print(row)
