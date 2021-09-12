class Encryptor:
    baseAlphabet = list(['а', 'б', 'в', 'г', 'д', 'е', 'ж',
                         'з', 'и', 'й', 'к', 'л', 'м', 'н',
                         'о', 'п', 'р', 'с', 'т', 'у', 'ф',
                         'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
                         'ь', 'э', 'ю', 'я', ' ', '.', ','])



    def encrypt(self, param):
        return param

    def decrypt(self, param):
        return param



    def removeDuplicates(self, seq):
        seen = set()
        seen_add = seen.add
        return [x for x in seq if not (x in seen or seen_add(x))]
