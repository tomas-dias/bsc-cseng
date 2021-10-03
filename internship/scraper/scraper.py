import requests
import json
import hashlib
import schedule
import time
import pytz
from datetime import datetime
from bs4 import BeautifulSoup
from elasticsearch import Elasticsearch


class Scraper:

    def __init__(self, station):

        self.url = f'http://www.clima.ict.uevora.pt/index1.php?action={station}&lang=EN'


    # Recolhe o conteúdo da página do url especificado sendo o parâmetro station correspondente à estação
    # Retorna a variável soup
    def getData(self):

        response = requests.get(self.url)

        if response.ok:
            soup = BeautifulSoup(response.text, 'html.parser')
            return soup
        else:
            return -1


    # Procura na página os dados pretendidos guardando-os num dicionário
    # Tem o parâmetro soup
    @staticmethod
    def parse(soup):

        results = soup.find('div', class_='row equal', style='padding-top:10px;')
        now = results.find_all('div', class_='row atual linha')
        # max = results.find_all('div', class_='row alto linha')
        # min = results.find_all('div', class_='row baixo linha')
        # dates = results.find_all('div', class_=['row pequeno linha', 'pequeno'])

        data = {
            'temperature': float(now[0].find('b').text),
            'wind-speed': float(now[1].find('b').text),
            'wind-direction-degrees': float(now[2].find('b').text),
            'wind-direction-cardinal': results.find_all('div', class_=['row linha'])[3].find('b').text,
            'rainfall': float(now[3].find('b').text),
            'humidity': float(now[4].find('b').text)
            # 'date': soup.find('div', class_='alert alert-success').find('b').text + ' UTC',
            # 'temp-max': float(max[0].find_all('b')[1].text),
            # 'date-temp-max': (dates[0].text)[15:],
            # 'temp-min': float(min[0].find_all('b')[1].text),
            # 'date-temp-min': (dates[1].text)[15:],
            # 'max-wind-speed': float(max[1].find_all('b')[1].text),
            # 'date-max-wind-speed': (dates[2].text)[15:],
            # 'rainfall-accumulated': float((results.find_all('div', class_='row linha')[6].text).split('m')[0]),
            # 'max-humidity': float(max[2].find_all('b')[1].text),
            # 'date-max-humidity': (dates[3].text)[15:],
            # 'min-humidity': float(min[1].find_all('b')[1].text),
            # 'date-min-humidity': (dates[4].text)[15:]
        }

        return data


def weather_scraping():

    elastic_client = Elasticsearch([{'host':'scraperdb','port':9200}])

    for station in ['verney', 'mitra', 'portel']:

        # Dicionário que guarda os logs a serem enviados para a base de dados Elasticsearch
        logs = {}

        # Objeto Scraper
        scraper = Scraper(station)

        # Invocação do método getData()
        data = scraper.getData()

        if data == -1:
            logs.update({'message': 'The scraping has failed',
                        'timestamp': datetime.now(pytz.timezone('GMT')).strftime('%Y-%m-%dT%H:%M:%S.%f'),
                        'url': scraper.url})
        else:
            logs.update({'message': 'The scraping has succeeded',
                        'timestamp': datetime.now(pytz.timezone('GMT')).strftime('%Y-%m-%dT%H:%M:%S.%f'),
                        'url': scraper.url})
        
        # Envio de logs para a base de dados Elasticsearch

        logs = json.dumps(logs)
        loaded_logs = json.loads(logs)
        elastic_client.index(index='scraper-logs',body=loaded_logs)

        # Guardar os dados recebidos
        weather_data = scraper.parse(data)

        # Envia dados para a api rest
        for param, value in weather_data.items():
            key = bytes(station + param + str(value) + 'scraper', encoding='utf-8')
            response = requests.post('http://apirest:8000/sensors/' + station + '/' + param + '/measurements', 
                                     json=
                                     {"value": value, 
                                      "key": hashlib.md5(key).hexdigest()})
            print(response.text)
        
        # Criação do ficheiro JSON que irá ser enviado (apenas para verificar se os dados são os corretos)
        # with open('scraper/weather_data_' + station + '.json', 'w') as outfile:
        #     json.dump(weather_data, outfile, indent=4)


if __name__ == '__main__':

    schedule.every(1).minutes.do(weather_scraping)

    while True:
        schedule.run_pending()
        time.sleep(1)