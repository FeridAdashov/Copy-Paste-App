import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QLabel, QPlainTextEdit, QPushButton
from Crypto.Cipher import AES

from firebase import Firebase


config = {
  "apiKey": "your firebase database api key",
  "databaseURL": "your firebase database url",
}

firebase = Firebase(config)
db = firebase.database()
db_data = db.child("admin/CopyPasteAppText").get()

class Example(QMainWindow):
    
    def __init__(self):
        super().__init__()
                
        self.lineEntry = QPlainTextEdit(self)
        self.lineEntry.setStyleSheet("color: #6200EE; font-weight: bold")
        self.lineEntry.move(16, 16)
        self.lineEntry.resize(270, 100)

        self.copy = QPushButton("Copy", self)
        self.copy.setStyleSheet("background-color : #6200EE; color: white; font-weight: bold")
        self.copy.move(16, 150)
        self.copy.clicked.connect(self.copy_button_pressed)

        self.refresh = QPushButton("Refresh", self)
        self.refresh.setStyleSheet("background-color : #F56F27; color: white; font-weight: bold")
        self.refresh.move(185, 150)
        self.refresh.clicked.connect(self.refresh_button_pressed)
        
        
        self.setGeometry(100,100,300,200)
        self.setWindowTitle("Copy Paste App")
        self.show()

    def copy_button_pressed(self):
        print('Button pressed')

    def refresh_button_pressed(self):
        print('Button dfd')
        

stylesheet = """
    Example {
        background-color: #787778;
    }
"""

if __name__ == '__main__':
    app = QApplication(sys.argv)
    app.setStyleSheet(stylesheet)
    ex = Example()
    sys.exit(app.exec_())