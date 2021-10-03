from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ("weather", "0001_initial"),
    ]

    operations = [
        migrations.RunSQL("SELECT create_hypertable('weather_measurement', 'date');"),
    ]
