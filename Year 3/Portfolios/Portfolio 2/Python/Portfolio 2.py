class TextFrequency:

    def __init__(self, file_name):
        self.file_name = file_name
        file_name = self.file_name
        text = list() 
        text = self.getText()
        self.text = text

    def getText(self):
        file = open(self.file_name, 'r') #open the file
        text = file.read() #store in the empty list
        text = text.replace('\n', ' ')
        file.close() #close the file
        return text

    def letterFreq(self): #iterate of the contents of the item in text.. print out the count of the number of times each letter occurs 
        text = self.text
        freq = {}
        for letter in text:
            if letter in text:
                freq[letter] = freq.get(letter, 0) + 1  
        for letter in freq:
            print(letter, freq[letter])
        return freq

    def wordFreq(self): #iterate of the contents of the item in text.. print out the count of the number of times each word occurs 
        text = self.text
        freq = {}    
        text = text.split()        
        for word in text:
            if word in text:
                freq[word] = freq.get(word, 0) + 1      
        for word in freq:
            print(word, '%s' %freq[word])         
        return freq

    def toLower(self):
        print('Converting input to lower...')
        text = self.text.lower()  
        self.text = text
        return self

class HistogramPrinter(TextFrequency):

    def __init__(self, file_name):
        super().__init__(file_name)

    def printLetterHist(self):
        text = self.text
        freq = {}
        for letter in text:
            if letter in text:
                freq[letter] = freq.get(letter, '') + '*'                
        for letter in freq:
            print(letter, freq[letter])
        return freq

    def printWordHist(self):
        text = self.text
        freq = {}    
        text = text.split()        
        for word in text:
            if word in text:
                freq[word] = freq.get(word,'') + '*'
        for word in freq:
            print(word, '%s' %freq[word])      
        return freq

labtxt = HistogramPrinter('lyrics.txt')
print('\n')
labtxt.toLower()
labtxt.printWordHist()
#labtxt.printLetterHist()
