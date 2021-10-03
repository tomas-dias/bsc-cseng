# syntax=docker/dockerfile:1
FROM python:3
ENV PYTHONUNBUFFERED=1
WORKDIR /home/apirest
COPY requirements.txt /home/apirest/
RUN pip install -r requirements.txt
COPY . /home/apirest/