from weather.models import Sensor, Measurement
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from weather.serializers import SensorSerializer, MeasurementSerializer
import hashlib

# Create your views here.


@api_view(['GET'])
def sensor_list(request):
    """
    List all code snippets.
    """
    if request.method == 'GET':
        sensors = Sensor.objects.all()

        if not sensors.exists():
            return Response({"message": "no sensors found"}, status=status.HTTP_404_NOT_FOUND)

        else:
            serializer = SensorSerializer(sensors, many=True)
            return Response({"message": "sensors found", "data": serializer.data}, status=status.HTTP_200_OK)

    # elif request.method == 'POST':
    #     serializer = SensorSerializer(data=request.data)

    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response({"message": "sensor saved successfully", "data": serializer.data}, status=status.HTTP_201_CREATED)

    #     return Response({"message": "sensor wasn't saved", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
def sensor_detail(request, localization, type):
    """
    Retrieve a code snippet.
    """
    try:
        sensor = Sensor.objects.get(localization=localization, type=type)
    except Sensor.DoesNotExist:
        return Response({"message": type + " sensor from " + localization + " doesn't exist"}, status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = SensorSerializer(sensor)
        return Response({"message": "sensor found", "data": serializer.data}, status=status.HTTP_200_OK)

    # elif request.method == 'DELETE':
    #     sensor.delete()
    #     return Response({"message": type + " sensor from " + localization + " deleted successfully"}, status=status.HTTP_200_OK)


@api_view(['GET', 'POST'])
def measurement_list(request, localization, type):
    """
    List all code snippets, or create a new snippet.
    """
    try:
        sensor = Sensor.objects.get(localization=localization, type=type)
    except Sensor.DoesNotExist:
        return Response({"message": type + " sensor from " + localization + " doesn't exist"}, status=status.HTTP_404_NOT_FOUND)
    
    if request.method == 'GET':

        measurements = Measurement.objects.filter(sensor=sensor.id)

        if not measurements.exists():
            return Response({"message": type + " sensor measurements from " + localization + " not found"}, status=status.HTTP_404_NOT_FOUND)
        
        else:
            serializer = MeasurementSerializer(measurements, many=True)
            return Response({"message": type + " sensor measurements from " + localization + " found", "data": serializer.data}, status=status.HTTP_200_OK)

    elif request.method == 'POST':

        key = bytes(localization + type + str(request.data['value']) + 'scraper', encoding='utf-8')

        if(hashlib.md5(key).hexdigest() == request.data['key']):
            new_data = {}
            new_data.update({"value": request.data['value'], "sensor": sensor.id})

            serializer = MeasurementSerializer(data=new_data)

            if serializer.is_valid():
                serializer.save()
                return Response({"message": type + " sensor measurement from " + localization + " saved successfully", "data": serializer.data}, status=status.HTTP_201_CREATED)

            return Response({"message": "measurement wasn't saved", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)
        
        else:
            return Response({"message": "authentication failed"}, status=status.HTTP_401_UNAUTHORIZED)


@api_view(['GET'])
def localization_list(request):
    """
    List all code snippets.
    """
    if request.method == 'GET':
        localizations = Sensor.objects.values('localization').distinct()

        if not localizations.exists():
            return Response({"message": "no localizations found"}, status=status.HTTP_404_NOT_FOUND)

        else:
            return Response({"message": "localizations found", "data": localizations}, status=status.HTTP_200_OK)


@api_view(['GET'])
def sensor_list_by_localization(request, localization):
    """
    Retrieve, update or delete a code snippet.
    """
    if request.method == 'GET':
        sensors = Sensor.objects.filter(localization=localization)

        if not sensors.exists():
            return Response({"message": "no sensors found"}, status=status.HTTP_404_NOT_FOUND)

        else:
            serializer = SensorSerializer(sensors, many=True)
            return Response({"message": "sensors from " + localization + " found", "data": serializer.data}, status=status.HTTP_200_OK)