# Generated by Django 3.2.3 on 2021-05-31 07:23

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('weather', '0003_auto_20210531_0715'),
    ]

    operations = [
        migrations.AlterField(
            model_name='measurement',
            name='value',
            field=models.CharField(max_length=50),
        ),
    ]