# алгоритм простых шифрующих таблицы;
from crypto.encryptor import Encryptor

ROWS_INDEX = 0
COLUMNS_INDEX = 1


class SimpleEncryptor(Encryptor):
    encryptionTable = []

    def __init__(self, keyword, size):
        self.keyword = self.removeDuplicates(keyword)
        self.size = size

    def encrypt(self, param):
        param = str.lower(param)
        print("SimpleEncryptor did encryption on " + param)
        output = ""
        self.encryptionTable = [[0 for j in range(self.size[COLUMNS_INDEX])] for i in range(self.size[ROWS_INDEX])]
        blocksize = self.size[ROWS_INDEX] * self.size[COLUMNS_INDEX]
        blocksCount = int(len(param) / blocksize)
        for i in range(0, blocksCount):
            block = param[blocksize * i:blocksize * (i + 1)]
            encrypted_block = self.encryptBlock(block)
            print(encrypted_block)
            output += encrypted_block

        block = param[blocksize * blocksCount:]
        encrypted_block = self.encryptBlock(block)
        print(encrypted_block)
        output += encrypted_block

        return output

    def decrypt(self, param):
        param = str.lower(param)
        print("SimpleEncryptor did encryption on " + param)
        oldSize = self.size;
        self.size = [oldSize[1], oldSize[0]]
        output = self.encrypt(param)
        self.size = oldSize
        return output

    def encryptBlock(self, block):
        self.fillTable(block)
        self.printEncryptionTable(self.size[ROWS_INDEX], self.size[COLUMNS_INDEX])
        return self.createBlock(len(block))

    def createBlock(self, length):
        output = ""
        for i in range(0, self.size[ROWS_INDEX]):
            for j in range(0, self.size[COLUMNS_INDEX]):
                output += str(self.encryptionTable[i][j])
                self.encryptionTable[i][j] = '_'

        return output

    def fillTable(self, param):
        for index in range(0, min(len(param), self.size[ROWS_INDEX] * self.size[COLUMNS_INDEX])):
            i = index % self.size[ROWS_INDEX]
            j = int(index / self.size[ROWS_INDEX])
            self.encryptionTable[i][j] = param[index]

    def printEncryptionTable(self, rowsCount, columnsCount):
        for i in range(0, rowsCount):
            row = ""
            for j in range(0, columnsCount):
                row += "[" + str(self.encryptionTable[i][j]) + "],"
            print(row)
