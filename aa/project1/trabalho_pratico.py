from sklearn.model_selection import train_test_split
import numpy as np
from tplib import DecisionTreeREPrune

# Exemplo dos slides

# data = [
#         ["length", "gills", "beak", "teeth", "class"],
#         [3, "no", "yes", "many",  "+"],
#         [4, "no", "yes", "many",  "+"],
#         [3, "no", "yes", "few",   "+"],
#         [5, "no", "yes", "many",  "+"],
#         [5, "no", "yes", "few",   "+"],
#         [5, "yes", "yes", "many", "-"],
#         [4, "yes", "yes", "many", "-"],
#         [5, "yes", "no", "many",  "-"],
#         [4, "yes", "no", "many",  "-"],
#         [4, "no", "yes", "few",   "-"],
#        ]

# data = np.array(data)

file_path = "path/to/file/weather.nominal.csv"
# file_path = "path/to/file/contact-lenses.csv"
# file_path = "path/to/file/vote.csv"
# file_path = "path/to/file/soybean.csv"

data = np.genfromtxt(file_path, delimiter=",", dtype=None, encoding=None)
atributos = data[0, 0:]
# print(atributos)
xdata = data[1:,0:-1] # dados: da segunda à ultima linha, da primeira à penúltima coluna
ydata = data[1:,-1] # classe: da segunda à ultima linha, só última coluna
x_train, x_test, y_train, y_test = train_test_split(xdata, ydata, train_size=0.75, random_state=0)
classifier = DecisionTreeREPrune(atributos)
classifier.fit(x_train, y_train)
classifier.score(x_test, y_test)
# classifier.fit(xdata, ydata)
result = classifier.score(x_test, y_test)
print("Percentagem de casos corretamente classificados {:2.2%}".format(result))
print("\n")