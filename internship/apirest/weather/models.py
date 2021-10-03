from django.db import models
from django.utils import timezone

# Create your models here.

class Sensor(models.Model):
    type = models.CharField(max_length=50)
    localization = models.CharField(max_length=50)
    unit = models.CharField(max_length=50)

class Measurement(models.Model):
    value = models.CharField(max_length=50)
    date = models.DateTimeField(primary_key=True, default=timezone.now)
    sensor = models.ForeignKey(Sensor, on_delete=models.CASCADE)
