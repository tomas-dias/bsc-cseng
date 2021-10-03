# syntax=docker/dockerfile:1
FROM python:3
ENV PYTHONUNBUFFERED=1
WORKDIR /home/scraper
COPY requirements.txt /home/scraper/
RUN pip install -r requirements.txt
COPY . /home/scraper/